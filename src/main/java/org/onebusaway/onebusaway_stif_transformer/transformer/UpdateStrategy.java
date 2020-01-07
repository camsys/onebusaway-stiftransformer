package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;

import java.util.List;

public class UpdateStrategy implements StifTransformStrategy{
    List<TypedEntityMatch> _matches;
    List<TypedEntityMatch> changes;
    public UpdateStrategy(List<TypedEntityMatch> matches, List<TypedEntityMatch> changes) {
    }

    public List<TypedEntityMatch> get_matches() {
        return _matches;
    }

    public List<TypedEntityMatch> getChanges() {
        return changes;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }


    public void run(TransformContext context, StifSupport support) {

    }
}
