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

public class ControlRecord implements StifRecord {
    private String recordType;
    private String numberOfGeographyRecords;
    private String numberOfEventRecords;
    private String numberOfRevenueTrips;
    private String numberOfSigncodeRecords;


    public void setRecordType(String recordType){
        this.recordType = recordType;
    }
    public void setNumberOfGeographyRecords(String numberOfGeographyRecords){
        this.numberOfGeographyRecords = numberOfGeographyRecords;
    }
    public void setNumberOfEventRecords(String numberOfEventRecords){
        this.numberOfEventRecords = numberOfEventRecords;
    }
    public void setNumberOfRevenueTrips(String numberOfRevenueTrips){
        this.numberOfRevenueTrips = numberOfRevenueTrips;
    }
    public void setNumberOfSigncodeRecords(String numberOfSigncodeRecords){
        this.numberOfSigncodeRecords = numberOfSigncodeRecords;
    }
    public String getRecordType(){
        return this.recordType;
    }
    public String getNumberOfGeographyRecords(){
        return this.numberOfGeographyRecords;
    }
    public String getNumberOfEventRecords(){
        return this.numberOfEventRecords;
    }
    public String getNumberOfRevenueTrips(){
        return this.numberOfRevenueTrips;
    }
    public String getNumberOfSigncodeRecords(){
        return this.numberOfSigncodeRecords;
    }

}
