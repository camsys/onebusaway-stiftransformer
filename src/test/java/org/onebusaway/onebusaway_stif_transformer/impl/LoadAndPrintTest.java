package org.onebusaway.onebusaway_stif_transformer.impl;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformSpecificationException;


public class LoadAndPrintTest {


    @Test
    public void testLoadAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/src/test/resources/stifs_mtabc";
        String transform = "{\"op\":\"update\",\"match\":{\"class\":\"EventRecord\",\"location\":\"1473\"},\"update\":{\"boardingAlightingFlag\":\"N\"}}";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }
}
