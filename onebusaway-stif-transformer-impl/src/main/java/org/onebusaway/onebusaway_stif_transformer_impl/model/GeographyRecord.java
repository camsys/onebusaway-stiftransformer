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

import java.io.Serializable;

public class GeographyRecord implements StifRecord, Serializable {
	private static final long serialVersionUID = 1L;

	private String recordType = "";
	private String identifier = "";
	private String locationDescription = "";
	private String intersectionDescription = "";
	private String geocode = "";
	private String boroughCode = "";
	private String timepointIdentifier = "";
	private String locationType = "";
	private String latitude = "";
	private String longitude = "";
	private String boxID = "";


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;		
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;		
	}
	public String getLongitude() {
		return longitude;
	}

	public void setBoxID(String boxID) {
		this.boxID = boxID;
	}
	
	public String getBoxID() {
		return boxID;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getBoroughCode() {
		return boroughCode;
	}

	public void setBoroughCode(String boroughCode) {
		this.boroughCode = boroughCode;
	}

	public String getGeocode() {
		return geocode;
	}

	public String getIntersectionDescription() {
		return intersectionDescription;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getTimepointIdentifier() {
		return timepointIdentifier;
	}

	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}

	public void setIntersectionDescription(String intersectionDescription) {
		this.intersectionDescription = intersectionDescription;
	}


	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setTimepointIdentifier(String timepointIdentifier) {
		this.timepointIdentifier = timepointIdentifier;
	}

	String fileName = null;

	public void addFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	@Override
	public String toString(){
		String tmp = "";
		String out = "";
		int n = 3 - 1;
		String formater = "%-"+ n + "s";
		out += String.format(formater,this.getRecordType());

		n = 7 - 3;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getIdentifier());

		n = 29 - 7;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getLocationDescription());

		n = 51 - 29;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getIntersectionDescription());

		n = 63 - 51;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getGeocode());

		n = 65 - 63;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getBoroughCode());

		n = 66 - 65;
		formater = "%-"+ n + "s";
		out += String.format(formater,"");

		n = 74 - 66;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getTimepointIdentifier());

		n = 75 - 74;
		formater = "%-"+ n + "s";
		out += String.format(formater,"");

		n = 77 - 75;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getLocationType());

		n = 78 - 77;
		formater = "%-"+ n + "s";
		out += String.format(formater,"");

		n = 88 - 78;
		formater = "%"+ n + "s";
		tmp =  String.format(formater,this.getLatitude());
		tmp = tmp.replaceAll("\\.", "");
		tmp =  String.format(formater,tmp);

		out += tmp;

		n = 98 - 88;
		formater = "%"+ n + "s";
		tmp =  String.format(formater,this.getLongitude());
		tmp = tmp.replaceAll("\\.", "");
		tmp =  String.format(formater,tmp);
		out += tmp;

		n = 99 - 98;
		formater = "%-"+ n + "s";
		out += String.format(formater,"");

		n = 105 - 99;
		formater = "%-"+ n + "s";
		out += String.format(formater,this.getBoxID());
		return out;
	}

}
