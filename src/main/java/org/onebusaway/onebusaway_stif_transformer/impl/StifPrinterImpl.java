package org.onebusaway.onebusaway_stif_transformer.impl;

import org.onebusaway.onebusaway_stif_transformer.*;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;

import java.io.File;
import java.util.*;


import java.io.File;
        import java.util.List;
        import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer.StifTrip;
        import org.onebusaway.onebusaway_stif_transformer.StifFileLoader;
        import org.onebusaway.onebusaway_stif_transformer.StifSupport;
        import org.onebusaway.onebusaway_stif_transformer.model.GeographyRecord;
        import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;



public class StifPrinterImpl {




    // for unit tests
    private String homePath = null;
    private StifPrinter printer = new StifPrinter();
    private static int ARG_NON_HOLIDAY_PATH = 0;

    private HashMap<String,StifSupport> _supportsByFile;

    public void setAddress(String homePath) {
        this.homePath = homePath;
    }

    public void addSupportByFile(String file, StifSupport support){
        _supportsByFile.put(file,support);
    }

    public void setSupportsByFile(HashMap<String, StifSupport> supportsByFile) {
        _supportsByFile = supportsByFile;
    }

    public void print() {
        File outputPath = new File(homePath);
        Boolean madeHomeDir = outputPath.mkdir();
        if (madeHomeDir || true) {
            for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()){
                File sourcePath = new File(entry.getKey());
                StifSupport support = entry.getValue();
                printer.printToDirectory(entry.getValue(), sourcePath, outputPath);
            }
        }
        else{
            System.out.println("Could not make home directory");
        }
    }

    public void printFormatForBoroughs(String[] inputPaths){
        File outputPath = new File(homePath);
        Boolean madeHomeDir = outputPath.mkdir();
        if (madeHomeDir || true) {
            for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()){
                String path = entry.getKey();
                StifSupport stifSupport = entry.getValue();
                printer.printToDirectoryBoroughsFormat(stifSupport, outputPath);
            }
        }
        else{
            System.out.println("Could not make home directory");
        }
    }

    public void printListOfBoroughsAndRoutes() {
        File outputPath = new File(homePath);
        Boolean madeHomeDir = outputPath.mkdir();
        if (madeHomeDir || true) {
            for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()){
                File sourcePath = new File(entry.getKey());
                StifSupport support = entry.getValue();
                printer.printBoroughsAndRoutes(entry.getValue(), outputPath, sourcePath.getName());
            }
        }
        else{
            System.out.println("Could not make home directory");
        }
    }

    private String getHolidayFolderNameSufix(String[] inputPaths){
        /*for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()) {
            String[] key = entry.getKey().split("/");
        }*/
        return "";
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
