package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class StifTransformFactory {


    private static final String ARG_OP = "op";
    private static final String ARG_UPDATE = "update";
    private static final String ARG_MATCH = "match";
    private static final String ARG_CLASS = "class";
    private final StifTransformer _transformer;
    private List<String> _entityPackages = new ArrayList<String>();


    public StifTransformFactory(StifTransformer transformer) {
        _transformer = transformer;
        addEntityPackage("org.onebusaway.gtfs.model");
    }

    public void addEntityPackage(String entityPackage) {
        _entityPackages.add(entityPackage);
    }

    public void addModificationsFromFile(File path) throws IOException,
            TransformSpecificationException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        addModificationsFromReader(reader);
    }

    public void addModificationsFromString(String value) throws IOException,
            TransformSpecificationException {
        addModificationsFromReader(new BufferedReader(new StringReader(value)));
    }

    public void addModificationsFromUrl(URL url) throws IOException,
            TransformSpecificationException {
        InputStream in = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        addModificationsFromReader(reader);
    }


    public void addModificationsFromReader(BufferedReader reader)
            throws IOException, TransformSpecificationException {

        String line = null;

        while ((line = reader.readLine()) != null) {

            try {

                line = line.trim();

                if (line.length() == 0 || line.startsWith("#") || line.equals("{{{")
                        || line.equals("}}}"))
                    continue;

                JSONObject json = new JSONObject(line);

                if (!json.has(ARG_OP)) {
                    throw new TransformSpecificationMissingArgumentException(line, ARG_OP);
                }
                String opType = json.getString(ARG_OP);

                if (opType.equals(ARG_UPDATE) || opType.equals("change")
                        || opType.equals("modify")) {
                    handleUpdateOperation(line, json);
                }  else {
                    throw new TransformSpecificationException("unknown transform op \""
                            + opType + "\"", line);
                }

            } catch (JSONException ex) {
                throw new TransformSpecificationException("error parsing json", ex,
                        line);
            }
        }
    }


    private void handleUpdateOperation(String line, JSONObject json)
            throws JSONException, TransformSpecificationException {

        EntitiesTransformStrategy strategy = getStrategy(EntitiesTransformStrategy.class);

        TypedEntityMatch match = getMatch(line, json);

        if (json.has("factory")) {
            String factoryType = json.getString("factory");
            try {
                Class<?> clazz = Class.forName(factoryType);
                Object factoryObj = clazz.newInstance();
                if (!(factoryObj instanceof EntityTransformStrategy)) {
                    throw new TransformSpecificationException(
                            "factory object is not an instance of EntityTransformStrategy: "
                                    + clazz.getName(), line);
                }
                strategy.addModification(match, (EntityTransformStrategy) factoryObj);
            } catch (Throwable ex) {
                throw new TransformSpecificationException(
                        "error creating factory ModificationStrategy instance", ex, line);
            }
            return;
        }

        if (json.has(ARG_UPDATE)) {

            JSONObject update = json.getJSONObject(ARG_UPDATE);

            EntityTransformStrategy mod = getUpdateEntityTransformStrategy(line,
                    match, update);

            strategy.addModification(match, mod);
        }

        if (json.has("strings")) {

            JSONObject strings = json.getJSONObject("strings");

            Map<String, Pair<String>> replacements = getEntityPropertiesAndStringReplacementsFromJsonObject(
                    match.getType(), strings);
            StringModificationStrategy mod = new StringModificationStrategy(
                    replacements);

            strategy.addModification(match, mod);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends StifTransformStrategy> T getStrategy(
            Class<T> transformerType) {

        StifTransformStrategy lastTransform = _transformer.getLastTransform();

        if (lastTransform != null
                && transformerType.isAssignableFrom(lastTransform.getClass()))
            return (T) lastTransform;

        T strategy = (T) instantiate(transformerType);
        _transformer.addTransform(strategy);
        return strategy;
    }

    private Object instantiate(Class<?> entityClass) {
        try {
            return entityClass.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("error instantiating type: "
                    + entityClass.getName());
        }
    }


    private TypedEntityMatch getMatch(String line, JSONObject json)
            throws JSONException, TransformSpecificationException {

        if (!json.has(ARG_MATCH)) {
            throw new TransformSpecificationMissingArgumentException(line, ARG_MATCH);
        }
        JSONObject match = json.getJSONObject(ARG_MATCH);

        Class<?> entityType = getEntityClassFromJsonSpec(line, match);
        if (entityType == null) {
            throw new TransformSpecificationMissingArgumentException(line);
        }

        Map<String, DeferredValueMatcher> propertyMatches = getPropertyValueMatchersFromJsonObject(
                match, _excludeForMatchSpec);

        List<EntityMatch> matches = new ArrayList<EntityMatch>();

        for (Map.Entry<String, DeferredValueMatcher> entry : propertyMatches.entrySet()) {
            String property = entry.getKey();
            Matcher m = _anyMatcher.matcher(property);
            if (m.matches()) {
                PropertyPathCollectionExpression expression = new PropertyPathCollectionExpression(
                        m.group(1));
                expression.setPropertyMethodResolver(_propertyMethodResolver);
                matches.add(new PropertyAnyValueEntityMatch(expression,
                        entry.getValue()));
            } else {
                PropertyPathExpression expression = new PropertyPathExpression(property);
                expression.setPropertyMethodResolver(_propertyMethodResolver);
                matches.add(new PropertyValueEntityMatch(expression, entry.getValue()));
            }
        }

        return new TypedEntityMatch(entityType, new EntityMatchCollection(matches));
    }

    private Class<?> getEntityClassFromJsonSpec(String line, JSONObject objectSpec)
            throws JSONException, TransformSpecificationException,
            TransformSpecificationMissingArgumentException {
        if (objectSpec.has(ARG_CLASS)) {
            return getEntityTypeForName(line, objectSpec.getString(ARG_CLASS));
        }
        return null;
    }

    private Class<?> getEntityTypeForName(String line, String name)
            throws TransformSpecificationException {

        Class<?> type = getClassForName(name);

        if (type != null)
            return type;

        for (String entityPackage : _entityPackages) {
            type = getClassForName(entityPackage + "." + name);
            if (type != null)
                return type;
        }

        throw new TransformSpecificationException("unknown class: " + name, line);
    }

    private Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
