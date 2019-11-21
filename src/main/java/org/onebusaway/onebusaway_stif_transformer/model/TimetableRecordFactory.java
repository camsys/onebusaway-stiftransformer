/**
 * Copyright (c) 2011 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.onebusaway.onebusaway_stif_transformer.model;

abstract class TimetableFieldSetter extends StifFieldSetter<TimetableRecord> {}
public class TimetableRecordFactory extends StifRecordFactory<TimetableRecord> {
	static class FieldDef extends StifFieldDefinition <TimetableRecord>{
		public FieldDef(int length, String name,
				StifFieldSetter<TimetableRecord> setter) {
			super(length, name, setter);
		}};

	@SuppressWarnings("rawtypes")
	private static StifFieldDefinition[] fields = {
			new FieldDef(3 - 1, "record type", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setRecordType(getStringData());
				}
			}),
			new FieldDef(7 - 3, "depot code", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setDepotCode(getStringData());
				}
			}),
			new FieldDef(9 - 7, "borough code", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setBoroughCode(getStringData());
				}
			}),
			new FieldDef(15 - 9, "route identifier", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setRouteIdentifier(getStringData());
				}
			}),
			new FieldDef(17 - 15, "service code", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setServiceCode(getStringData());
				}
			}),
			new FieldDef(41 - 17, "depot description", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setDepotDescription(getStringData());
				}
			}),
			new FieldDef(65 - 41, "route description", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setRouteDescription(getStringData());
				}
			}),
			new FieldDef(73 - 65, "schedule number", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setScheduleNumber(getStringData());
				}
			}),
			new FieldDef(77 - 73, "version number", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setVersionNumber(getStringData());
				}
			}),
			new FieldDef(78 - 77, "empty", null),
			new FieldDef(79 - 78, "stif type code", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setStifTypeCode(getStringData());
				}
			}),
			new FieldDef(80 - 79, "empty", null),
			new FieldDef(82 - 80, "organization", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setOrganization(getStringData());
				}
			}),
			new FieldDef(84 - 82, "empty", null),
			new FieldDef(92 - 84, "generation date", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setGenerationDate(getStringData());
				}
			}),
			new FieldDef(93 - 92, "empty", null),
			new FieldDef(95 - 93, "additional depot code 1", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotCode1(getStringData());
				}
			}),
			new FieldDef(96 - 95, "empty", null),
			new FieldDef(98 - 96, "additional depot code 2", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotCode2(getStringData());
				}
			}),
			new FieldDef(99 - 98, "empty", null),
			new FieldDef(101 - 99, "additional depot code 3", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotCode3(getStringData());
				}
			}),
			new FieldDef(102 - 101, "empty", null),
			new FieldDef(104 - 102, "additional depot code 4", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotCode4(getStringData());
				}
			}),
			new FieldDef(105 - 104, "empty", null),
			new FieldDef(111 - 105, "additional schedule number 1", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotScheduleNumber1(getStringData());
				}
			}),
			new FieldDef(112 - 111, "empty", null),
			new FieldDef(118 - 112, "additional schedule number 2", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotScheduleNumber2(getStringData());
				}
			}),
			new FieldDef(119 - 118, "empty", null),
			new FieldDef(125 - 119, "additional schedule number 3", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotScheduleNumber3(getStringData());
				}
			}),
			new FieldDef(126 - 125, "empty", null),
			new FieldDef(132 - 126, "additional schedule number 4", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setAdditionalDepotScheduleNumber4(getStringData());
				}
			}),
			new FieldDef(133 - 132, "empty", null),
			new FieldDef(139 - 133, "curtain route", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setCurtainRoute(getStringData());
				}
			}),
			new FieldDef(146 - 139, "statistical route", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setStatisticalRoute(getStringData());
				}
			}),
			new FieldDef(154 - 146, "holiday code", new TimetableFieldSetter() {
				public void setField(TimetableRecord record) {
					record.setHolidayCode(getStringData());
				}
			})
		};

	@Override
	public TimetableRecord createEmptyRecord() {
		return new TimetableRecord();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StifFieldDefinition<TimetableRecord>[] getFields() {
		return fields;
	}

}
