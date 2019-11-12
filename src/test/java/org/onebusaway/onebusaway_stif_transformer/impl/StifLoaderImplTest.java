package org.onebusaway.onebusaway_stif_transformer.impl;

import org.junit.Test;
import org.onebusaway.onebusaway_stif_transformer.StifTripLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StifLoaderImplTest {

    @Test
    public void main() {
            System.out.println(System.getenv());
        System.out.println(System.getenv().get("PATH"));
            System.out.println(System.getenv().get("PWD"));

            String stifFile = "/Users/caylasavitzky/src/mtaoba/onebusawaystiftransformer/stif.q_0027__.5H9603.wkd.closed";

            StifTripLoader loader = new StifTripLoader();
            InputStream in = null;
            try {
                in = new FileInputStream(stifFile);
                loader.run(in, new File(stifFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}