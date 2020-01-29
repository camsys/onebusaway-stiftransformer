package org.onebusaway.onebusaway_stif_transformer;

import org.junit.Test;

public class TestStifTransformerSuite {
    @Test
    public void testLoadAndTransformFromStringAndPrint() {

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

    @Test
    public void testLoadAndTransformFromFileAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/src/test/resources/stifs_mtabc";
        String transform = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/src/test/resources/loadTransformByFileTest.json";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }


}
