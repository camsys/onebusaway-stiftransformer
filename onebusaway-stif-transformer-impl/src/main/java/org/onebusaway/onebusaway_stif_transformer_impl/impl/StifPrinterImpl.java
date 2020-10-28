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
    private int printType = 0;
    private String inputPath;

    public void setAddress(String homePath) {
        this.homePath = homePath;
    }

    public void addSupportByFile(String file, StifSupport support){
        _supportsByFile.put(file,support);
    }

    public void setSupportsByFile(HashMap<String, StifSupport> supportsByFile) {
        _supportsByFile = supportsByFile;
    }

    public void setInputPath(String inputPath){this.inputPath = inputPath;}

    public void setPrintType(int printType){this.printType = printType;}

    public void print(){
        switch(printType){
            case 0:
                printFormatAsOriginal();
                break;
            case 1:
                printFormatForBoroughs();
                break;
            case 2:
                printListOfBoroughsAndRoutes();
                break;
        }
    }


    public void printFormatAsOriginal() {
        File outputHomePath = new File(homePath);
        outputHomePath.mkdir();
        for (Map.Entry<String, StifSupport> entry : _supportsByFile.entrySet()) {
            File outputPath = new File(outputHomePath + "/" + entry.getKey().substring(inputPath.length()));
            File outputPathParent = outputPath.getParentFile();
            outputPathParent.mkdirs();
            File sourcePath = new File(entry.getKey());
            StifSupport support = entry.getValue();
            printer.printToDirectoryOriginalFormat(entry.getValue(), sourcePath, outputPathParent);
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
