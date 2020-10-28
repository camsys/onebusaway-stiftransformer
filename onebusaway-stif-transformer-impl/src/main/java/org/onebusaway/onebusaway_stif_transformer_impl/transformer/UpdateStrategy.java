/**
 * Copyright (C) 2020 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.onebusaway_stif_transformer_impl.transformer;

import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;
import org.onebusaway.onebusaway_stif_transformer_impl.model.StifRecord;

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
