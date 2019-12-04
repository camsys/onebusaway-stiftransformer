package org.onebusaway.onebusaway_stif_transformer.transformer;


import java.util.ArrayList;

public class StifTransformer {

    ArrayList <StifTransformStrategy> transforms = new ArrayList<StifTransformStrategy>();


    public void addTransform(StifTransformStrategy strategy) {
        transforms.add(strategy);
    }

    public StifTransformStrategy getLastTransform() {
        return transforms.get(transforms.size()-1);
    }
}
