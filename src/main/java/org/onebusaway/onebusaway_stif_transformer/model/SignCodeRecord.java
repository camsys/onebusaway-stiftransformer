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

public class SignCodeRecord implements StifRecord {
	private String recordType;
	private String signCode;
	private String signRoute;
	private String signText;

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignRoute(String signRoute) {
		this.signRoute = signRoute;
	}

	public String getSignRoute() {
		return signRoute;
	}

	public void setSignText(String signText) {
		this.signText = signText;
	}

	public String getSignText() {
		return signText;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	String fileName = null;
	public void addFileName(String fileName){
		this.fileName = fileName;
	}
	public String getFileName(String fileName){
		return this.fileName;
	}

	@Override
	public String toString(){
		String out = "";
		int n = 3 - 1;
		String formatter = "%-"+n+"s";
		out += String.format(formatter, this.getRecordType());

		n = 11 - 3;
		formatter = "%-"+n+"s";
		out += String.format(formatter, this.getSignCode());

		n = 19 - 11;
		formatter = "%-"+n+"s";
		out += String.format(formatter, this.getSignRoute());


		n = 21 - 19;
		formatter = "%-"+n+"s";
		out += String.format(formatter, "");

		n = 121 - 21;
		formatter = "%-"+n+"s";
		out += String.format(formatter, this.getSignText());

		return out;
	}
}
