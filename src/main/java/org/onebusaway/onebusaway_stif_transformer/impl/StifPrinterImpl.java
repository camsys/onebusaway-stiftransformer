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
    private StifSupport support = null;
    private StifSupport holidaySupport = null;
    private String address = null;
    private StifPrinter printer = new StifPrinter();

    public void setSupport(StifSupport support) {
        this.support = support;
    }

    public void setHolidaySupport(StifSupport holidaySupport) {
        this.holidaySupport = holidaySupport;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void printBorough() {
        if (support != null && address != null) {
            File homeFolder = new File(address);
            Boolean madeHomeDir = homeFolder.mkdir();
            if (madeHomeDir) {
                File nonHolidayFolder = new File(address.concat("/non_holiday"));
                Boolean madeNonHolidayFolder = nonHolidayFolder.mkdir();
                if (madeNonHolidayFolder) {
                    printer.printToDirectory(support, nonHolidayFolder);
                }
                if (holidaySupport != null) {
                    File holidayFolder = new File(address.concat("/holiday"));
                    Boolean madeholidayFolder = holidayFolder.mkdir();
                    if (madeholidayFolder) {
                        printer.printToDirectory(holidaySupport, holidayFolder);
                    }
                }
            }
            else{
                System.out.print("Could not make home directory");
            }
        }
        System.out.print("printing complete");
    }




    public static void main(){

    }

}
