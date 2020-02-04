package onebusaway_stif_transformer_impl;
import org.junit.Test;

import org.onebusaway.onebusaway_stif_transformer_impl.StifTransformerSuite;


public class LoadAndPrintTest {


    @Test
    public void testLoadAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/onebusaway-stif-transformer-impl/src/test/resources/stifs_mtabc";
        String transform = "{\"op\":\"update\",\"match\":{\"class\":\"EventRecord\",\"location\":\"1473\"},\"update\":{\"boardingAlightingFlag\":\"N\"}}";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }
}
