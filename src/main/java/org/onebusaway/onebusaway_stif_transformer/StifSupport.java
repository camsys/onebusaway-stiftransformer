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

import java.io.File;
import java.sql.SQLException;
import java.util.*;


import org.onebusaway.onebusaway_stif_transformer.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifSupport {

  private static Logger _log = LoggerFactory.getLogger(StifSupport.class);


  private int _totalTripCount;

  private ArrayList<StifRecord> recordsByLineNumber = new ArrayList<StifRecord>();


  private Boolean _excludeNonRevenue = true;

  private Map<String, GeographyRecord> geographyRecordForBoxId = new HashMap<String, GeographyRecord>();
  private Map<String,ArrayList<StifRecord>> stifFileRecordsForFileId = new HashMap<String,ArrayList<StifRecord>>();
  private Map<String, TimetableRecord> timetableRecordForFileId = new HashMap<String, TimetableRecord>();
  private Map<String, TripRecord> tripRecordForTripId = new HashMap<String, TripRecord>();
  private Map<String, ArrayList<EventRecord>> eventRecordForTripId = new HashMap<String, ArrayList<EventRecord>>();
  private Map<String, SignCodeRecord> signCodeRecordForSignCode =  new HashMap<String, SignCodeRecord>();
  private Map<String, ControlRecord> controlRecordForFileId = new HashMap<String, ControlRecord>();
  private ArrayList<File> paths = new ArrayList<>();
  private Map<String, File> stifFilePathsForFileNames = new HashMap<String, File>();
  private Set<File> stifFilePathsParents = new HashSet<>();

  public static ServiceCode scheduleIdForGtfsDayCode(String dayCode) {
    return ServiceCode.getServiceCodeForId(dayCode);
  }

  public void processFile(String fileId){
    stifFileRecordsForFileId.put(fileId,recordsByLineNumber);
    recordsByLineNumber = new ArrayList<StifRecord>();
  }

  /*public Collection<ArrayList<StifRecord>> getAllStifRecords() {

  }*/

  public Map<String,ArrayList<StifRecord>> getStifFileRecordsForFileId(){
    return stifFileRecordsForFileId;
  }

  public void addRecord(StifRecord record){
    recordsByLineNumber.add(record);
  }

  public int getTotalTripCount() {
    return _totalTripCount;
  }

  public void putGeographyRecordForBoxId(String boxId, GeographyRecord geographyRecord) {
    printDuplicate(geographyRecordForBoxId.get(geographyRecord));
    geographyRecordForBoxId.put(boxId,geographyRecord);
  }

  public void putTimeTableRecordForFileId(String fileId, TimetableRecord timetableRecord){
    printDuplicate(timetableRecordForFileId.get(fileId));
    timetableRecordForFileId.put(fileId,timetableRecord);
  }

  public Map<String, TimetableRecord> getTimetableRecordForFileId() {
    return timetableRecordForFileId;
  }

  public SignCodeRecord getSignCodeRecordForSignCode(String signCode) {
    return signCodeRecordForSignCode.get(signCode);
  }

  public void putSignCodeRecordForSignCode(String signCode, SignCodeRecord signCodeRecord) {
    printDuplicate(signCodeRecordForSignCode.get(signCode));
    signCodeRecordForSignCode.put(signCode, signCodeRecord);
  }

  public void putTripRecordForTripId(String tripId, TripRecord tripRecord){
    printDuplicate(tripRecordForTripId.get(tripRecord));
    tripRecordForTripId.put(tripId,tripRecord);
  }

  public void putEventRecordForTripId(String tripId, EventRecord eventRecord){
    if (eventRecordForTripId.get(tripId) == null){
      eventRecordForTripId.put(tripId,new ArrayList<EventRecord>());
    }
    eventRecordForTripId.get(tripId).add(eventRecord);
  }

  public void putControlRecordForFileId(String fileId, ControlRecord controlRecord){
    printDuplicate(controlRecordForFileId.get(fileId));
    controlRecordForFileId.put(fileId,controlRecord);
  }

  public File getStifFilePathForFileName(String fileName) {
    return stifFilePathsForFileNames.get(fileName);
  }

  public void putStifFilePathsForFileNames(String fileName, File stifFilePath) {
    this.stifFilePathsForFileNames.put(fileName,stifFilePath);
    this.stifFilePathsParents.add(stifFilePath.getParentFile());
  }

  public Collection<File> getStifFilePaths(){
    return stifFilePathsForFileNames.values();
  }

  public Set<String> getStifFileNames(){
    return stifFilePathsForFileNames.keySet();
  }

  public Set<File> getStifFilePathsParents(){
    return stifFilePathsParents;
  }

  /****
   * Private Methods
   ****/

  public GeographyRecord getGeographyRecordForBoxId(String boxId) {
    return geographyRecordForBoxId.get(boxId);
  }


    public Boolean getExcludeNonRevenue() {
      return _excludeNonRevenue;
    }

    public void setExcludeNonRevenue(Boolean excludeNonRevenue) {
       _excludeNonRevenue = excludeNonRevenue;
    }

    private void printDuplicate(StifRecord record){
      if (record != null){
        _log.info("Duplicate found: ");
        _log.info("class = " + record.getClass());
        _log.info("value = " + record.toString());
      }
    }
  }
