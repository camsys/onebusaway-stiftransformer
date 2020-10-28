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

abstract class EventFieldSetter extends StifFieldSetter<EventRecord> {
}

public class EventRecordFactory extends StifRecordFactory<EventRecord> {
  static class FieldDef extends StifFieldDefinition<EventRecord> {
    public FieldDef(int length, String name, StifFieldSetter<EventRecord> setter) {
      super(length, name, setter);
    }
  };

  @SuppressWarnings("rawtypes")
  private static StifFieldDefinition[] fields = {
    new FieldDef(3 - 1, "record type", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setRecordType(getStringData());
      }
    }),
    new FieldDef(7 - 3, "location", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setLocation(getStringData());
      }
    }),
    new FieldDef(15 - 7, "event time", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setEventTime(getStringData());
      }
    }),
    new FieldDef(17 - 15, "event type", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setEventType(getStringData());
      }
    }),
    new FieldDef(18 - 17, "stop flag", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setStopFlag(getStringData());
      }
    }),
    new FieldDef(19 - 18, "timepoint", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setTimepoint(getStringData());
      }
    }),
    new FieldDef(21 - 19, "location type code", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setLocationTypeCode(getStringData());
      }
    }),
    new FieldDef(23 - 21, "boarding / alighting flag", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setBoardingAlightingFlag(getStringData());
      }
    }),
    new FieldDef(24 - 23, "empty", null),
    new FieldDef(29 - 24, "distance from Start of Trip", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setDistanceFromStartOfTrip(getStringData());
      }
    }),
    new FieldDef(30 - 29, "empty", null),
    new FieldDef(36 - 30, "location box id", new EventFieldSetter() {
      public void setField(EventRecord record) {
        record.setLocationBoxID(getStringData());
      }
    })
  };
  @Override
  public EventRecord createEmptyRecord() {
    return new EventRecord();
  }

  @SuppressWarnings("unchecked")
  @Override
  public StifFieldDefinition<EventRecord>[] getFields() {
    return fields;
  }

}
