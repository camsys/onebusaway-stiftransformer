package org.onebusaway.onebusaway_stif_transformer.impl;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import org.onebusaway.onebusaway_stif_transformer.StifFileLoader;
import org.onebusaway.onebusaway_stif_transformer.StifSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StifPrinterImplTest {


    @Test
    public void main() {
        StifPrinterImpl printer = new StifPrinterImpl();
        printer.setAddress("/Users/caylasavitzky/Downloads/demo");
        printer.setSupport(new StifSupport());
        printer.printBorough();
    }
}