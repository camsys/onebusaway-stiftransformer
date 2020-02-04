package org.onebusaway.onebusaway_stif_transformer_impl.transformer;

import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;

public interface EntityTransformStrategy {
    public void run(TransformContext context, StifSupport support,
                    Object entity);
}
