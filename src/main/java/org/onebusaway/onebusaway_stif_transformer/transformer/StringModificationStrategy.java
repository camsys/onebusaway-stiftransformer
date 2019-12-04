package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;

import java.util.Map;

public class StringModificationStrategy implements EntityTransformStrategy {

    private Map<String, Pair<String>> _propertyUpdates;

    public StringModificationStrategy(Map<String, Pair<String>> propertyUpdates) {
        _propertyUpdates = propertyUpdates;
    }

    public void run(TransformContext context, StifSupport support,
                    Object entity) {
        BeanWrapper wrapper = BeanWrapperFactory.wrap(entity);
        for (Map.Entry<String, Pair<String>> entry : _propertyUpdates.entrySet()) {
            String property = entry.getKey();
            Pair<String> value = entry.getValue();
            Object propertyValue = wrapper.getPropertyValue(property);
            if (propertyValue != null) {
                String propertyStringValue = propertyValue.toString();
                propertyStringValue = propertyStringValue.replaceAll(value.getFirst(),
                        value.getSecond());
                wrapper.setPropertyValue(property, propertyStringValue);
            }
        }
    }
}
