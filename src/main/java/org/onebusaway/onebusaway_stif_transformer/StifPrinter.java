package org.onebusaway.onebusaway_stif_transformer;


import org.omg.CORBA.TIMEOUT;
import org.onebusaway.onebusaway_stif_transformer.*;
import org.onebusaway.onebusaway_stif_transformer.model.*;

import java.io.*;
import java.util.*;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer.StifTrip;
import org.onebusaway.onebusaway_stif_transformer.StifFileLoader;
import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifPrinter {

    private static Logger _log = LoggerFactory.getLogger(StifPrinter.class);
    private static String ARG_NON_HOLIDAY = "non_holiday";
    private static String ARG_FILE_DELIMETER = "/";

    public void printToDirectoryBoroughsFormat(StifSupport support, File outputPath){
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

    public void printToDirectory(StifSupport support, File sourcePath, File directory){
        if (directory.isDirectory()) {
            Map<String,ArrayList<StifRecord>> mapOfStifRecords = support.getStifFileRecordsForFileId();
            for (Map.Entry<String,ArrayList<StifRecord>> stifRecordEntry:mapOfStifRecords.entrySet()) {
                File stifPath = support.getStifFilePathForFileName(stifRecordEntry.getKey());
                File outputPath = updateOutputLocationForDirectoryStructure(directory, sourcePath,stifPath.getParentFile());
                printStifRecords(outputPath,stifRecordEntry.getKey(),stifRecordEntry.getValue());
            }
        }
        else {
            _log.error("file is not directory" + directory.getAbsolutePath());
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
