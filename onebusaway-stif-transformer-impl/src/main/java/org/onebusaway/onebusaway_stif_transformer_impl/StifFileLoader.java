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
package org.onebusaway.onebusaway_stif_transformer_impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;


import org.onebusaway.onebusaway_stif_transformer_impl.model.EventRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.GeographyRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.ServiceCode;
import org.onebusaway.onebusaway_stif_transformer_impl.model.SignCodeRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.StifRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.TimetableRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.TripRecord;
import org.onebusaway.onebusaway_stif_transformer_impl.model.ControlRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifFileLoader {

  private static Logger _log = LoggerFactory.getLogger(StifFileLoader.class);


  private StifSupport support = new StifSupport();

  private int _tripsCount = 0;

  private Map<String, GeographyRecord> geographyRecordsByBoxId = new HashMap<String, GeographyRecord>(20000);

  private Map<ServiceCode, List<StifTrip>> rawData = new HashMap<ServiceCode, List<StifTrip>>(1024);

  public void setExcludeNonRevenue(Boolean excludeNonRevenue) {
	 support.setExcludeNonRevenue(excludeNonRevenue);
  }

  public Map<String, GeographyRecord> getGeographyRecordsByBoxId() {
    return geographyRecordsByBoxId;
  }
  
  public int getTripsCount() {
    return _tripsCount;
  }


  public void run(File path) {
    try {
      _log.info("Path is: " + path.toString());
      InputStream in = new FileInputStream(path);
      if (path.getName().endsWith(".gz"))
        in = new GZIPInputStream(in);
      run(in, path);
    } catch (Exception e) {
      _log.error("Caught exception reading file in stif-transformer, stifFileLoader",e);
      throw new RuntimeException("Error loading " + path, e);
    }
  }

  public void run(InputStream stream, File path) {
    _log.info("Path is: " + path.toString());
    StifRecordReader reader;
    String[] tmp = path.toString().split("/");
    String fileName = tmp[tmp.length-1];
    support.putStifFilePathsForFileNames(fileName, path);
    int lineNumber = 0;
    TripRecord tripRecord = null;
    try {
      reader = new StifRecordReader(stream);
      while (true) {
        StifRecord record = reader.read();
        lineNumber++;

        if (record == null) {
          break;
        }

        support.addRecord(record);
        record.addFileName(fileName);

        if (record instanceof TimetableRecord) {
          TimetableRecord timetableRecord = (TimetableRecord) record;
          timetableRecord.addFileName(fileName);
          support.putTimeTableRecordForFileId(fileName,timetableRecord);
          continue;
        }
        if (record instanceof GeographyRecord) {
          GeographyRecord geographyRecord = ((GeographyRecord) record);
          geographyRecord.addFileName(fileName);
          support.putGeographyRecordForBoxId(geographyRecord.getBoxID(),
                  geographyRecord);
          continue;
        }
        if (record instanceof TripRecord) {
          tripRecord = (TripRecord) record;
          tripRecord.addFileName(fileName);
          support.putTripRecordForTripId(tripRecord.getPrimaryRunRoute()+tripRecord.getPrimaryRunNumber(),tripRecord);
        }

        if (record instanceof EventRecord) {
          EventRecord eventRecord = ((EventRecord) record);
          eventRecord.addFileName(fileName);
          support.putEventRecordForTripId(tripRecord.getPrimaryRunRoute()+tripRecord.getPrimaryRunNumber(),eventRecord);
        }
        if (record instanceof SignCodeRecord) {
          SignCodeRecord signCodeRecord = ((SignCodeRecord) record);
          signCodeRecord.addFileName(fileName);
          support.putSignCodeRecordForSignCode(signCodeRecord.getSignCode(), signCodeRecord);
        }
        if (record instanceof ControlRecord) {
          ControlRecord controlRecord = ((ControlRecord) record);
          controlRecord.addFileName(fileName);
          support.putControlRecordForFileId(fileName,controlRecord);
        }
      }
      support.processFile(fileName);
    } catch (IOException e) {
      _log.error("Caught exception loading file in stif-transformer, stifFileLoader",e);
      throw new RuntimeException(e);
    }
  }



  public Map<ServiceCode, List<StifTrip>> getRawStifData() {
    return rawData;
  }
  public StifSupport getSupport() {
    return support;
  }

}
