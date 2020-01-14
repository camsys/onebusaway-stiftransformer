package org.onebusaway.onebusaway_stif_transformer.transformer;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.model.StifRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateStrategy implements StifTransformStrategy{
    List<TypedEntityMatch> _matches;
    List<TypedEntityMatch> _updates;
    public UpdateStrategy(List<TypedEntityMatch> matches, List<TypedEntityMatch> updates) {
        _matches = matches;
        _updates = updates;
    }

    public List<TypedEntityMatch> get_matches() {
        return _matches;
    }

    public List<TypedEntityMatch> getChanges() {
        return _updates;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }


    public void run(TransformContext context, StifSupport dao) {
        Map<String, ArrayList<StifRecord>> stifFileRecordsForFileId = dao.getStifFileRecordsForFileId();
        for (Map.Entry<String, ArrayList<StifRecord>> entry : stifFileRecordsForFileId.entrySet()){
            ArrayList<StifRecord> stifFile = entry.getValue();
            matchingStifRecord: for(StifRecord record : stifFile){
                for (TypedEntityMatch match : _matches){
                    if(match.isApplicableToObject(record) == false){
                        continue matchingStifRecord;
                    }
                }
                for (TypedEntityMatch update : _updates){
                    update.isApplicableToObject(record);
                }
            }
        }

    }
}
