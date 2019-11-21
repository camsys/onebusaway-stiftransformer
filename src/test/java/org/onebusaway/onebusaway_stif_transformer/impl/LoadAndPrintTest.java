package org.onebusaway.onebusaway_stif_transformer.impl;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;



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

    @Test
    public void loadAndPrint(){
        StifSupport support = load("/Users/caylasavitzky/Desktop/QUCOL");
        print("/Users/caylasavitzky/Downloads/demo", support);
    }
}
