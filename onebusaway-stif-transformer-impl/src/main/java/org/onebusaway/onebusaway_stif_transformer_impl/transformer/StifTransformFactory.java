package org.onebusaway.onebusaway_stif_transformer_impl.transformer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class StifTransformFactory {


    private static final String ARG_OP = "op";
    private static final String ARG_UPDATE = "update";
    private static final String ARG_MATCH = "match";
    private static final String ARG_CLASS = "class";
    private final StifTransformer _transformer;
    private List<String> _entityPackages = new ArrayList<String>();


    public StifTransformFactory(StifTransformer transformer) {
        _transformer = transformer;
        addEntityPackage("org.onebusaway.onebusaway_stif_transformer_impl.model");
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

    private void handleUpdateOperation(String line, JSONObject json) throws TransformSpecificationException, JSONException{
        JSONObject jsonSubsection = json.getJSONObject(ARG_MATCH);
        Class<?> entityType = getEntityClassFromJsonSpec(line,jsonSubsection);
        List<TypedEntityMatch> matches = getMatches(line,json, ARG_MATCH,entityType);
        List<TypedEntityMatch> changes = getMatches(line,json, ARG_UPDATE,entityType);
        UpdateStrategy update = new UpdateStrategy(matches,changes);
        _transformer.addTransform(update);
    }

    private List<TypedEntityMatch> getMatches(String line,JSONObject json, String actionKey, Class<?> entityType) throws TransformSpecificationException, JSONException {
        JSONObject jsonSubsection = json.getJSONObject(actionKey);
        ArrayList<TypedEntityMatch> matches = new ArrayList<TypedEntityMatch>();
        Map<String,Method> methodsByName = getMethodsByName(entityType);
        // for key in json run getMatch
        for(Iterator<String> keys = jsonSubsection.sortedKeys(); keys.hasNext();){
            String key = keys.next();
            if (!key.equals(ARG_CLASS)) {
                matches.add(getMatch(entityType, jsonSubsection, key, methodsByName, line, actionKey));
            }
        }
        return matches;
    }

    private Map<String,Method> getMethodsByName(Class<?> entityType){
        if (entityType==null){
            return null;
        }
        Collection<Method> methods = Arrays.asList(entityType.getMethods());
        Map<String,Method> methodsByName = new HashMap<>();
        for (Method method : methods){
            methodsByName.put(method.getName(),method);
        }
        return methodsByName;
        //Collection methodsNames = methods.stream().map(Method::getName).collect(Collectors.toSet());
    }


    private TypedEntityMatch getMatch(Class<?> entityType, JSONObject jsonSubsection, String key, Map<String,Method> methodsByName, String line, String action)
            throws JSONException, TransformSpecificationException {
        String property = jsonSubsection.getString(key);
        //capitalize first letter of property
        String uppercaseKey = key.substring(0,1).toUpperCase() + key.substring(1);
        Method getMethod = methodsByName.get("get"+uppercaseKey);
        Method setMethod = methodsByName.get("set"+uppercaseKey);
        if(getMethod != null && action==ARG_MATCH){
            return new TypedEntityMatch(entityType, getMethod,property);
        }
        else if(setMethod !=null && action==ARG_UPDATE){
            return new TypedEntityUpdate(entityType, getMethod, setMethod,property);
        }
        else{
            throw new TransformSpecificationException("unknown class property combination: " + entityType.getName() + "/" + key, line);
        }


    }



    private Class<?> getEntityClassFromJsonSpec(String line, JSONObject objectSpec)
            throws JSONException, TransformSpecificationException,
            TransformSpecificationMissingArgumentException {
        if (objectSpec.has(ARG_CLASS)) {
            return getEntityTypeForName(line, objectSpec.getString(ARG_CLASS));
        }
        return null;
    }

    //tries to return a class with an object of name: name. It looks inside of model for that class.
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
