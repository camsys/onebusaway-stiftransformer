package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;

public interface StifTransformStrategy {
    public String getName();
    public void run(TransformContext context, StifSupport support);
}