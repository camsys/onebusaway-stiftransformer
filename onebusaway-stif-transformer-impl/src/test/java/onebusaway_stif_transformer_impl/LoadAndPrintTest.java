package onebusaway_stif_transformer_impl;
import org.junit.Test;

import org.onebusaway.onebusaway_stif_transformer_impl.StifTransformerSuite;

import java.io.File;


public class LoadAndPrintTest {


    @Test
    public void testLoadAndPrint() {

        ClassLoader classLoader = new StifBoroughCategorizorTest().getClass().getClassLoader();
        File stifsPath = new File(classLoader.getResource("onebusaway_stif_transformer_impl/stifs_mtabc").getFile());

        String[] inputPaths = new String[1];
        inputPaths[0] = stifsPath.getAbsolutePath();
        String transform = "{\"op\":\"update\",\"match\":{\"class\":\"EventRecord\",\"location\":\"1473\"},\"update\":{\"boardingAlightingFlag\":\"N\"}}";
        String outputPath = System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }
}
