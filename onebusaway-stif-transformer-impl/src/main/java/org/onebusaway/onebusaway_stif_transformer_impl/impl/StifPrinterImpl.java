package org.onebusaway.onebusaway_stif_transformer_impl.impl;


import java.io.File;
import java.util.*;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer_impl.StifPrinter;
import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifPrinterImpl {

    private static Logger _log = LoggerFactory.getLogger(StifPrinterImpl.class);


    // for unit tests
    private String homePath = null;
    private StifPrinter printer = new StifPrinter();
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
            _log.info("Could not make home directory");
        }
    }

    public void printFormatForBoroughs(){
        File outputPath = new File(homePath);
        Boolean madeHomeDir = outputPath.mkdir();
        if (madeHomeDir || true) {
            for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()){
                StifSupport stifSupport = entry.getValue();
                printer.printToDirectoryBoroughsFormat(stifSupport, outputPath);
            }
        }
        else{
            _log.info("Could not make home directory");
        }
    }

    public void printListOfBoroughsAndRoutes() {
        File outputPath = new File(homePath);
        Boolean madeHomeDir = outputPath.mkdir();
        if (madeHomeDir || true) {
            for(Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()){
                File sourcePath = new File(entry.getKey());
                StifSupport support = entry.getValue();
                printer.printBoroughsAndRoutes(entry.getValue(), outputPath, sourcePath.getParentFile().getName()+"_"+sourcePath.getName());
            }
        }
        else{
            _log.info("Could not make home directory");
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
