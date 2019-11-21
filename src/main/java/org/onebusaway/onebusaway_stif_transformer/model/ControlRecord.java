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
    private String unknownField;
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
    public void setUnknownField(String unkownField){
        this.unknownField = unkownField;
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
    public String setUnknownField(){return this.unknownField; }
    public String getNumberOfRevenueTrips(){
        return this.numberOfRevenueTrips;
    }
    public String getNumberOfSigncodeRecords(){
        return this.numberOfSigncodeRecords;
    }


    String fileName = null;
    public void addFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(String fileName){
        return this.fileName;
    }


    @Override
    public String toString() {
        String out = "";
        int n = 3 - 1;
        String formatter = "%-" + n + "s";
        out += String.format(formatter, this.getRecordType());

        n = 9-3;
        formatter = "%-" + n + "s";
        out += String.format(formatter, this.getNumberOfGeographyRecords());

        n = 15-9;
        formatter = "%-" + n + "s";
        out += String.format(formatter, this.getNumberOfEventRecords());

        n = 21-15;
        formatter = "%-" + n + "s";
        out += String.format(formatter, this.setUnknownField());

        n = 27-21;
        formatter = "%-" + n + "s";
        out += String.format(formatter, this.getNumberOfRevenueTrips());

        n = 33-27;
        formatter = "%-" + n + "s";
        out += String.format(formatter, this.getNumberOfSigncodeRecords());

        return out;
    }
}
