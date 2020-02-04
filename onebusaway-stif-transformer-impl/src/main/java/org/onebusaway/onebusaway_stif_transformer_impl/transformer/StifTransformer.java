package org.onebusaway.onebusaway_stif_transformer_impl.transformer;


import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;

import java.util.ArrayList;

public class StifTransformer {

    ArrayList <StifTransformStrategy> transforms = new ArrayList<StifTransformStrategy>();
    StifSupport stifSupport;
    TransformContext context;


    public void addTransform(StifTransformStrategy strategy) {
        transforms.add(strategy);
    }

    public StifTransformStrategy getLastTransform() {
        return transforms.get(transforms.size()-1);
    }

    public void setStifSupport(StifSupport stifSupport) {
        this.stifSupport = stifSupport;
    }

    public void setContext(TransformContext context) {
        this.context = context;
    }

    public void run(){
        for ( StifTransformStrategy transform : transforms){
            transform.run(context,stifSupport);
        }
    }
}
