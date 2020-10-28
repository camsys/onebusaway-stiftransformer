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



import org.onebusaway.onebusaway_stif_transformer_impl.model.*;

import java.io.*;
import java.util.*;


import java.io.File;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifPrinter {

    private static Logger _log = LoggerFactory.getLogger(StifPrinter.class);
    private static String ARG_NON_HOLIDAY = "non_holiday";
    private static String ARG_FILE_DELIMETER = "/";

    public void printToDirectoryBoroughsFormat(StifSupport support, File outputPath){
        if (support.getStifFileRecordsForFileId().entrySet().size() == 0){
            return;
        }
        StifBoroughCategorizor stifBoroughCategorizor = new StifBoroughCategorizor();
        stifBoroughCategorizor.initialize(support);
        String stifBorough = stifBoroughCategorizor.categorizeByBorough();
        String stifHolidayStatus = stifBoroughCategorizor.categorizeByHolidayStatus();

        String stifOutputPath = outputPath.getAbsolutePath() + ARG_FILE_DELIMETER + stifBorough +  ARG_FILE_DELIMETER + stifHolidayStatus;
        File stifOutputFolder = new File(stifOutputPath);
        stifOutputFolder.mkdirs();

        for (Map.Entry<String,ArrayList<StifRecord>> stifRecordEntry: support.getStifFileRecordsForFileId().entrySet()) {
            File stifPath = support.getStifFilePathForFileName(stifRecordEntry.getKey());
            printStifRecords(stifOutputFolder, stifRecordEntry.getKey(), stifRecordEntry.getValue());
        }
    }

    public void printToDirectoryOriginalFormat(StifSupport support, File sourcePath, File outputPath){
        if (outputPath.isDirectory()) {
            Map<String,ArrayList<StifRecord>> mapOfStifRecords = support.getStifFileRecordsForFileId();
            for (Map.Entry<String,ArrayList<StifRecord>> stifRecordEntry:mapOfStifRecords.entrySet()) {
                printStifRecords(outputPath,stifRecordEntry.getKey(),stifRecordEntry.getValue());
            }
        }
        else {
            _log.error("file is not directory" + outputPath.getAbsolutePath());
        }
    }

    public void printBoroughsAndRoutes(StifSupport support, File outputPath,String fileName){
        if (outputPath.isDirectory()) {
            Set<String> boroughsAndRoutes = new HashSet<String>();
            Map<String,TimetableRecord> mapOfTimeTableRefordsForFileId = support.getTimetableRecordForFileId();
            for (Map.Entry<String,TimetableRecord> timetableRecordEntry:mapOfTimeTableRefordsForFileId.entrySet()) {
                    TimetableRecord timetableRecord = timetableRecordEntry.getValue();
                    boroughsAndRoutes.add(timetableRecord.getBoroughCode() + timetableRecord.getRouteIdentifier());
            }
        printStrings(outputPath,"BoroughsAndRoutesFor_"+fileName+".txt", boroughsAndRoutes);
        }
        else {
            _log.error("file is not directory");
        }
    }

    public void printStifRecords(File directory, String fileName, ArrayList<StifRecord> stifRecords){
        try {
            File stifFile = new File(directory,fileName);
            stifFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(stifFile.getAbsolutePath()));
            for(StifRecord record : stifRecords) {
                writer.write(record.toString());
                writer.write("\r\n");
            }
            writer.close();
        }
        catch (IOException exception){
            _log.error("Error in writing STIF File: " +fileName, exception);
        }
    }

    public void printStrings(File directory, String fileName, Set<String> strings){
        try {
            File stifFile = new File(directory,fileName);
            stifFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(stifFile.getAbsolutePath()));
            for(String string : strings) {
                writer.write(string.toString());
                writer.write("\r\n");
            }
            writer.close();
        }
        catch (IOException exception){
            _log.error("Error in writing STIF File: " +fileName,exception);
        }
    }

    private File updateOutputLocationForDirectoryStructure(File outputPath, File startPath, File endPath){
        if (!startPath.getAbsolutePath().equals(endPath.getAbsolutePath())){
            updateOutputLocationForDirectoryStructure(outputPath, startPath,endPath.getParentFile());
            File childDir = new File(outputPath.getAbsolutePath() + "/"+ endPath.getName());
            childDir.mkdir();
            return childDir;
        }
        return startPath;
    }
}
