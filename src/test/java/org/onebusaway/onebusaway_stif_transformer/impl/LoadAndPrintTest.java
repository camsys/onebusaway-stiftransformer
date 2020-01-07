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

    public void print(String to,StifSupport support,StifSupport holidaySupport){
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress(to);
        printer.setSupport(support);
        printer.setHolidaySupport(holidaySupport);
        printer.printBorough();
    }

    @Test
    public void loadAndPrint(){

        StifSupport support = load("/Users/caylasavitzky/Downloads/stifs_bronx");
        //StifSupport holidaySupport = load("/Users/caylasavitzky/Downloads/testing/SIDAT S9");
        //print("/Users/caylasavitzky/Downloads/demo", support, holidaySupport);
        print("/Users/caylasavitzky/Downloads/demo", support);
    }
}
