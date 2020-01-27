package org.onebusaway.onebusaway_stif_transformer.impl;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformSpecificationException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StifTransformerSuite {

    String[] _inputPaths;
    String _tranform;
    String _outputPath;
    int _outputFormat = 0;

    public void setInputPaths(String ... paths){
        _inputPaths = paths;
    }

    public void setTranform(String tranform){ _tranform = tranform;}

    public void setOutputPath(String outputPath){ _outputPath = outputPath;}

    public void setOutputFormat(String formatForBoroughs) {
        _outputFormat = new Integer(formatForBoroughs);
    }

    public void run (){
        HashMap<String, StifSupport> supportsByDirectory = new HashMap<>();
        load(supportsByDirectory);
        transform(supportsByDirectory, _tranform);
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress(_outputPath);
        printer.setSupportsByFile(supportsByDirectory);
        if (_outputFormat==1) {
            printer.printFormatForBoroughs(_inputPaths);
        }
        if(_outputFormat==0){
            printer.print();
        }
        if(_outputFormat==2) {
            printer.printListOfBoroughsAndRoutes();
        }
    }

    private void load(HashMap<String, StifSupport> supportsByDirectory){
        for (String path : _inputPaths){
            StifLoaderImpl loader = new StifLoaderImpl();
            ArrayList<File> files = new ArrayList<File>();
            files.add((new File(path)));
            loader.load(files, supportsByDirectory);
        }
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
                System.out.print("That was bad JSON " + exception.toString());
            } catch (TransformSpecificationException exception) {
                System.out.print("That was a bad transform " + exception.toString());
            }
            transformer.run();
        }

    }

}