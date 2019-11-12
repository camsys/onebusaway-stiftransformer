/**
 *
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

abstract class SignCodeFieldSetter extends StifFieldSetter<SignCodeRecord> {}

public class SignCodeRecordFactory extends StifRecordFactory<SignCodeRecord> {
	static class FieldDef extends StifFieldDefinition <SignCodeRecord>{
		public FieldDef(int length, String name,
				StifFieldSetter<SignCodeRecord> setter) {
			super(length, name, setter);
		}};

	@SuppressWarnings("rawtypes")
	private static StifFieldDefinition[] fields;
	static {
		fields = new StifFieldDefinition[] {
		new FieldDef(3-1, "record type", new SignCodeFieldSetter() {
			public void setField(SignCodeRecord record) {record.setRecordType(getStringData());}}),
		new FieldDef(11-3, "sign code", new SignCodeFieldSetter() {
			public void setField(SignCodeRecord record) {record.setSignCode(getStringData());}}),
		new FieldDef(19-11, "sign route", new SignCodeFieldSetter() {
			public void setField(SignCodeRecord record) {record.setSignRoute(getStringData());}}),
		new FieldDef(21-19, "empty", null),			
		new FieldDef(121-21, "sign text", new SignCodeFieldSetter() {
			public void setField(SignCodeRecord record) {record.setSignText(getStringData());}}),
		};
	}
	@Override
	public SignCodeRecord createEmptyRecord() {
		return new SignCodeRecord();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StifFieldDefinition<SignCodeRecord>[] getFields() {
		return fields;
	}

}
