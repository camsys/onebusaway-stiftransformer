/**
 * Copyright (C) 2011 Metropolitan Transportation Authority
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

import org.onebusaway.onebusaway_stif_transformer_impl.impl.StifLoaderImpl;
import org.onebusaway.onebusaway_stif_transformer_impl.impl.StifPrinterImpl;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.TransformSpecificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StifTransformerSuite {

    private static Logger _log = LoggerFactory.getLogger(StifTransformerSuite.class);

    String[] _inputPaths;
    String _tranform;
    String _outputPath;
    int _outputFormat = 1;

    public void setInputPaths(String ... paths){
        _inputPaths = paths;
    }

    public void setTranform(String tranform){ _tranform = tranform;}

    public void setOutputPath(String outputPath){ _outputPath = outputPath;}

    public void setOutputFormat(String formatForBoroughs) {
        _outputFormat = new Integer(formatForBoroughs);
    }
    public void setOutputFormat(int formatForBoroughs) {
        _outputFormat = formatForBoroughs;
    }

    public void run (){
        HashMap<String,HashMap<String,StifSupport>> supportsByFileByInputSource = new HashMap<>();
        _log.info("Number of stif sources being loaded " + _inputPaths.length);
        for (String inputPath : _inputPaths) {
            HashMap<String, StifSupport> supportsByFile = new HashMap<>();
            load(supportsByFile, inputPath);
            supportsByFileByInputSource.put(inputPath,supportsByFile);
        }
        _log.info("Stif Transformer loading complete");
        for(Map.Entry<String, HashMap<String,StifSupport>> supportsByFileByInputSourceEntry : supportsByFileByInputSource.entrySet()){
            transform(supportsByFileByInputSourceEntry.getValue(), _tranform);
        }

        _log.info("Stif Transformer transformation complete");

        for(Map.Entry<String, HashMap<String,StifSupport>> supportsByFileByInputSourceEntry : supportsByFileByInputSource.entrySet()){
            print(supportsByFileByInputSourceEntry.getValue(), supportsByFileByInputSourceEntry.getKey());
        }
        _log.info("Stif Transformer printing complete");
    }

    private void load(HashMap<String, StifSupport> supportsByDirectory, String path){
        StifLoaderImpl loader = new StifLoaderImpl();
        ArrayList<File> files = new ArrayList<File>();
        files.add((new File(path)));
        //_log.info(path);
        loader.load(files, supportsByDirectory);
    }



    private void transform(HashMap<String, StifSupport> supportsByDirectory, String transform) {
        for(Map.Entry<String, StifSupport> entry : supportsByDirectory.entrySet()){
            StifSupport support = entry.getValue();
            StifTransformer transformer = new StifTransformer();
            transformer.setStifSupport(support);
            transformer.setContext(new TransformContext());
            StifTransformFactory factory = new StifTransformFactory(transformer);
            try {
                if (transform.startsWith("{")) {
                    factory.addModificationsFromString(transform);
                }
                else if(transform.startsWith("http")){
                    URL url = new URL(transform);
                    factory.addModificationsFromUrl(url);
                }
                else if(transform.startsWith("/")){
                    File file = new File(transform);
                    factory.addModificationsFromFile(file);
                }
            } catch (IOException exception) {
                _log.error("The Stif-Transformer was fed bad json", exception.toString());
            } catch (TransformSpecificationException exception) {
                _log.error("The Stif-Transformer was fed a bad transformation", exception.toString());
            }
            transformer.run();
        }

    }

    private void print(HashMap<String, StifSupport> supportsByFile, String inputPath){
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress(_outputPath);
        printer.setSupportsByFile(supportsByFile);
        printer.setPrintType(_outputFormat);
        printer.setInputPath(inputPath);
        printer.print();
    }
}