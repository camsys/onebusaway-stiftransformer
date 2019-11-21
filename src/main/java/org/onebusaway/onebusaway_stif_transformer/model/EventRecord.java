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

public class EventRecord implements StifRecord {


  private String recordType;
  private String location;
  private String eventTime;
  private String eventType;
  private String stopFlag;
  private String timepoint;
  private String locationTypeCode;
  private String boardingAlightingFlag;
  private String distanceFromStartOfTrip;
  private String locationBoxID;


  public String getRecordType(){
    return this.recordType;
  }
  public String getLocation(){
    return this.location;
  }
  public String getEventTime(){
    return this.eventTime;
  }
  public String getEventType(){
    return this.eventType;
  }
  public String getStopFlag(){
    return this.stopFlag;
  }
  public String getTimepoint(){
    return this.timepoint;
  }
  public String getLocationTypeCode(){
    return this.locationTypeCode;
  }
  public String getBoardingAlightingFlag(){
    return this.boardingAlightingFlag;
  }
  public String getDistanceFromStartOfTrip(){
    return this.distanceFromStartOfTrip;
  }
  public String getLocationBoxID(){
    return this.locationBoxID;
  }


  public void setRecordType (String recordType){
    this.recordType = recordType;
  }
  public void setLocation (String location){
    this.location = location;
  }
  public void setEventTime (String eventTime){
    this.eventTime = eventTime;
  }
  public void setEventType (String eventType){
    this.eventType = eventType;
  }
  public void setStopFlag (String stopFlag){
    this.stopFlag = stopFlag;
  }
  public void setTimepoint (String timepoint){
    this.timepoint = timepoint;
  }
  public void setLocationTypeCode (String locationTypeCode){
    this.locationTypeCode = locationTypeCode;
  }
  public void setBoardingAlightingFlag (String boardingAlightingFlag){
    this.boardingAlightingFlag = boardingAlightingFlag;
  }
  public void setDistanceFromStartOfTrip (String distanceFromStartOfTrip){
    this.distanceFromStartOfTrip = distanceFromStartOfTrip;
  }
  public void setLocationBoxID (String locationBoxID){
    this.locationBoxID = locationBoxID;
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
    n = 7 - 3;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getLocation());
    n = 15 - 7;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getEventTime());
    n = 17 - 15;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getEventType());
    n = 18 - 17;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getStopFlag());
    n = 19 - 18;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getTimepoint());
    n = 21 - 19;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getLocationTypeCode());
    n = 23 - 21;
    formatter = "%"+n+"s";
    out += String.format(formatter, this.getBoardingAlightingFlag());
    n = 24 - 23;
    formatter = "%-"+n+"s";
    out += String.format(formatter,"");
    n = 29 - 24;
    formatter = "%"+n+"s";
    out += String.format(formatter, this.getDistanceFromStartOfTrip());
    n = 30 - 29;
    formatter = "%-"+n+"s";
    out += String.format(formatter,"");
    n = 36 - 30;
    formatter = "%-"+n+"s";
    out += String.format(formatter, this.getLocationBoxID());
    return out;
  }
}
