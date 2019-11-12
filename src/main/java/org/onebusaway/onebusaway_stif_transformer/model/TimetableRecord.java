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

/**
 * The header for a schedule for one bus line for one service period
 */
public class TimetableRecord implements StifRecord {

	private String recordType;
	private String depotCode;
  	private String agencyId;
  	private String boroughCode;
	private String routeIdentifier;
	private String serviceCode;
	private String depotDescription;
	private String routeDescription;
	private String scheduleNumber;
	private String versionNumber;
	private String stifTypeCode;
	private String organization;
	private String generationDate;
	private String additionalDepotCode1;
	private String additionalDepotCode2;
	private String additionalDepotCode3;
	private String additionalDepotCode4;
	private String additionalDepotScheduleNumber1;
	private String additionalDepotScheduleNumber2;
	private String additionalDepotScheduleNumber3;
	private String additionalDepotScheduleNumber4;
	private String curtainRoute;
	private String statisticalRoute;
	private String holidayCode;


	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRouteIdentifier(String routeIdentifier) {
		this.routeIdentifier = routeIdentifier;
	}
	
	public String getRouteIdentifier() {
		return routeIdentifier;
	}

	public void setServiceCode(String code) {
		this.serviceCode = code;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}

  	public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
  }

  	public String getAgencyId() {
    return agencyId;
  }

	public void setAdditionalDepotCode1(String additionalDepotCode1) {
		this.additionalDepotCode1 = additionalDepotCode1;
	}

	public void setAdditionalDepotCode2(String additionalDepotCode2) {
		this.additionalDepotCode2 = additionalDepotCode2;
	}

	public void setAdditionalDepotCode3(String additionalDepotCode3) {
		this.additionalDepotCode3 = additionalDepotCode3;
	}

	public void setAdditionalDepotCode4(String additionalDepotCode4) {
		this.additionalDepotCode4 = additionalDepotCode4;
	}
	public void setAdditionalDepotScheduleNumber1(String additionalDepotScheduleNumber1) {
		this.additionalDepotScheduleNumber1 = additionalDepotScheduleNumber1;
	}

	public void setAdditionalDepotScheduleNumber2(String additionalDepotScheduleNumber2) {
		this.additionalDepotScheduleNumber2 = additionalDepotScheduleNumber2;
	}

	public void setAdditionalDepotScheduleNumber3(String additionalDepotScheduleNumber3) {
		this.additionalDepotScheduleNumber3 = additionalDepotScheduleNumber3;
	}

	public void setAdditionalDepotScheduleNumber4(String additionalDepotScheduleNumber4) {
		this.additionalDepotScheduleNumber4 = additionalDepotScheduleNumber4;
	}

	public void setBoroughCode(String boroughCode) {
		this.boroughCode = boroughCode;
	}

	public void setCurtainRoute(String curtainRoute) {
		this.curtainRoute = curtainRoute;
	}

	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}

	public String getAdditionalDepotCode1() {
		return additionalDepotCode1;
	}

	public String getAdditionalDepotCode2() {
		return additionalDepotCode2;
	}

	public String getAdditionalDepotCode3() {
		return additionalDepotCode3;
	}

	public String getAdditionalDepotCode4() {
		return additionalDepotCode4;
	}

	public String getAdditionalDepotScheduleNumber1() {
		return additionalDepotScheduleNumber1;
	}

	public String getAdditionalDepotScheduleNumber2() {
		return additionalDepotScheduleNumber2;
	}

	public String getAdditionalDepotScheduleNumber3() {
		return additionalDepotScheduleNumber3;
	}

	public String getAdditionalDepotScheduleNumber4() {
		return additionalDepotScheduleNumber4;
	}

	public String getBoroughCode() {
		return boroughCode;
	}

	public String getCurtainRoute() {
		return curtainRoute;
	}

	public String getDepotCode() {
		return depotCode;
	}

	public String getHolidayCode() {
		return holidayCode;
	}

	public String getOrganization() {
		return organization;
	}

	public String getRouteDescription() {
		return routeDescription;
	}

	public String getScheduleNumber() {
		return scheduleNumber;
	}

	public String getStatisticalRoute() {
		return statisticalRoute;
	}

	public String getStifTypeCode() {
		return stifTypeCode;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setHolidayCode(String holidayCode) {
		this.holidayCode = holidayCode;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}

	public void setScheduleNumber(String scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public void setStatisticalRoute(String statisticalRoute) {
		this.statisticalRoute = statisticalRoute;
	}

	public void setStifTypeCode(String stifTypeCode) {
		this.stifTypeCode = stifTypeCode;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public void setGenerationDate(String generationDate) {
		this.generationDate = generationDate;
	}
	public String getGenerationDate() {
		return generationDate;
	}

	public String getDepotDescription() {
		return depotDescription;
	}

	public void setDepotDescription(String depotDescription) {
		this.depotDescription = depotDescription;
	}
}

