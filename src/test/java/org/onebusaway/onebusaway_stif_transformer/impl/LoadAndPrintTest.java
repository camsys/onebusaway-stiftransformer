package org.onebusaway.onebusaway_stif_transformer.impl;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformSpecificationException;


public class LoadAndPrintTest {


    public StifSupport load(String from){
        String stifFile = from;
        StifLoaderImpl loader = new StifLoaderImpl();
        ArrayList<File> files = new ArrayList<File>();
        files.add((new File(stifFile)));
        loader.load(files);
        return loader.getSupport();
    }

    public void print(String to,StifSupport support){
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress(to);
        printer.setSupport(support);
        printer.printBorough();
    }

    public void print(String to,StifSupport support,StifSupport holidaySupport){
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress(to);
        printer.setSupport(support);
        printer.setHolidaySupport(holidaySupport);
        printer.printBorough();
    }

    public void transform(String transform, StifSupport support){
        StifTransformer transformer = new StifTransformer();
        transformer.setStifSupport(support);
        transformer.setContext(new TransformContext());
        StifTransformFactory factory = new StifTransformFactory(transformer);
        try {
            factory.addModificationsFromString(transform);
        }
        catch (IOException exception){
            System.out.print("That was bad JSON " +exception.toString());
        }
        catch (TransformSpecificationException exception){
            System.out.print("That was a bad transform " +exception.toString());
        }
    }

    @Test
    public void loadAndPrint(){

        StifSupport support = load("/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/src/test/java/resources/stifs_staten_island");
        //StifSupport holidaySupport = load("/Users/caylasavitzky/Downloads/testing/SIDAT S9");
        //print("/Users/caylasavitzky/Downloads/demo", support, holidaySupport);
        transform("{\"op\":\"update\",\"match\":{\"class\":\"EventRecord\",\"location\":\"1473\"},\"update\":{\"boardingAlightingFlag\":\"N\"}}", support);
        print("/Users/caylasavitzky/Downloads/demo", support);
    }
}
