package org.onebusaway.onebusaway_stif_transformer;


import org.onebusaway.onebusaway_stif_transformer.*;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer.StifTrip;
import org.onebusaway.onebusaway_stif_transformer.StifFileLoader;
import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.model.GeographyRecord;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;
import org.onebusaway.onebusaway_stif_transformer.model.StifRecord;


public class StifPrinter {
    public void printToDirectory(StifSupport support, File directory){
        if (directory.isDirectory()) {
            Map<String,ArrayList<StifRecord>> mapOfStifRecords = support.getStifFileRecordsForFileId();
            for (Map.Entry<String,ArrayList<StifRecord>> stifRecordEntry:mapOfStifRecords.entrySet()) {
                printStifRecords(directory,stifRecordEntry.getKey(),stifRecordEntry.getValue());
            }
        }
        else {
            System.out.print("file is not directory");
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
        catch (Exception e){

        }
    }
}
