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
package org.onebusaway.onebusaway_stif_transformer_impl.model;

abstract class ControlFieldSetter extends StifFieldSetter<ControlRecord> {}

public class ControlRecordFactory extends StifRecordFactory<ControlRecord> {
    static class FieldDef extends StifFieldDefinition <ControlRecord>{
        public FieldDef(int length, String name,
                StifFieldSetter<ControlRecord> setter) {
            super(length, name, setter);
        }};

    @SuppressWarnings("rawtypes")
    private static StifFieldDefinition[] fields;
    static {
        fields = new StifFieldDefinition[] {
            new FieldDef(3-1, "record type", new ControlFieldSetter() {
                public void setField(ControlRecord record) {record.setRecordType(getStringData());}}),
            new FieldDef(9-3, "Number of Geography Records", new ControlFieldSetter() {
                public void setField(ControlRecord record) {record.setNumberOfGeographyRecords(getStringData());}}),
            new FieldDef(15-9, "Number of Event Records", new ControlFieldSetter() {
                public void setField(ControlRecord record) {record.setNumberOfEventRecords(getStringData());}}),
            new FieldDef(21-15, "Unknown Field", new ControlFieldSetter() {
                public void setField(ControlRecord record) {record.setUnknownField(getStringData());}}),
            new FieldDef(27-21, "Number of Revenue Records", new ControlFieldSetter() {
                public void setField(ControlRecord record) {record.setNumberOfRevenueTrips(getStringData());}}),
            new FieldDef(33-27, "Number of Sign Code Records", new ControlFieldSetter() {
                    public void setField(ControlRecord record) {record.setNumberOfSigncodeRecords(getStringData());}})
        };
    }
    @Override
    public ControlRecord createEmptyRecord() {
        return new ControlRecord();
    }

    @SuppressWarnings("unchecked")
    @Override
    public StifFieldDefinition<ControlRecord>[] getFields() {
        return fields;
    }

}
