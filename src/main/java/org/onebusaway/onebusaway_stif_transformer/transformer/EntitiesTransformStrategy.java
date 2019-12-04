package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;

public interface EntitiesTransformStrategy {
    public void run(TransformContext context, StifSupport support,
                    Object entity);

    void addModification(TypedEntityMatch match, StringModificationStrategy mod);
}