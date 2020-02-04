package org.onebusaway.onebusaway_stif_transformer_impl.transformer;

import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;

public interface StifTransformStrategy {
    public String getName();
    public void run(TransformContext context, StifSupport support);
}