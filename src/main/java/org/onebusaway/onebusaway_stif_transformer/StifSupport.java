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
package org.onebusaway.onebusaway_stif_transformer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.onebusaway.onebusaway_stif_transformer.model.*;


public class StifSupport {


  private Map<String, String> stopIdsByLocation = new HashMap<String, String>();

  private int _totalTripCount;

  private ArrayList<StifRecord> recordsByLineNumber = new ArrayList<StifRecord>();


  private Boolean _excludeNonRevenue = true;


  private TimetableRecord timetableRecord;
  private Map<String, TripRecord> tripRecordForTripPrimaryRunNumber = new HashMap<String, TripRecord>();
  private Map<String, ArrayList<EventRecord>> eventRecordForTripPrimaryRunNumber = new HashMap<String, ArrayList<EventRecord>>();
  private Map<String, SignCodeRecord> signCodeRecordForSignCode =  new HashMap<String, SignCodeRecord>();
  private ControlRecord controlRecord;

  public static ServiceCode scheduleIdForGtfsDayCode(String dayCode) {
    return ServiceCode.getServiceCodeForId(dayCode);
  }

  public void addRecord(StifRecord record){
    recordsByLineNumber.add(record);
  }

  public int getTotalTripCount() {
    return _totalTripCount;
  }

  public void putStopIdForLocation(String location, String stopId) {
    stopIdsByLocation.put(location, stopId);
  }

  public void putTimeTableRecord(TimetableRecord timetableRecord){
    this.timetableRecord = timetableRecord;
  }

  public SignCodeRecord getSignCodeRecordForSignCode(String signCode) {
    return signCodeRecordForSignCode.get(signCode);
  }

  public void putSignCodeRecordForSignCode(String signCode, SignCodeRecord signCodeRecord) {
    signCodeRecordForSignCode.put(signCode, signCodeRecord);
  }

  public void putTripRecordForTripPrimaryRunNumber(String primaryRunNumber, TripRecord tripRecord){
    tripRecordForTripPrimaryRunNumber.put(primaryRunNumber,tripRecord);
  }

  public void putEventRecordForTripPrimaryRunNumber(String primaryRunNumber, EventRecord eventRecord){
    if (eventRecordForTripPrimaryRunNumber.get(primaryRunNumber) == null){
      eventRecordForTripPrimaryRunNumber.put(primaryRunNumber,new ArrayList<EventRecord>());
    }
    eventRecordForTripPrimaryRunNumber.get(primaryRunNumber).add(eventRecord);
  }

  public void putControlRecord(ControlRecord controlRecord){
    this.controlRecord = controlRecord;
  }

  /****
   * Private Methods
   ****/

  public String getStopIdForLocation(String originLocation) {
    return stopIdsByLocation.get(originLocation);
  }


    public Boolean getExcludeNonRevenue() {
      return _excludeNonRevenue;
    }

    public void setExcludeNonRevenue(Boolean excludeNonRevenue) {
       _excludeNonRevenue = excludeNonRevenue;
    }

  }
