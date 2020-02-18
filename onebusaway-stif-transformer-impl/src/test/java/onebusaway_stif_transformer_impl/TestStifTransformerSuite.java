package onebusaway_stif_transformer_impl;

import org.junit.Ignore;
import org.junit.Test;
import org.onebusaway.onebusaway_stif_transformer_impl.StifTransformerSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStifTransformerSuite {
    private static Logger _log = LoggerFactory.getLogger(TestStifTransformerSuite.class);

    @Ignore
    @Test
    public void testLoadAndTransformFromSiteAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/onebusaway-stif-transformer-impl/src/test/resources/stifs_mtabc";
        //inputPaths[0] = "/var/folders/2t/k5nkxf3s49153ccm9h7wqmx40000gn/T/tmp1580408549199/stif/stif/non_holiday/stif.m_0034sb.212123.sat";
        String transform = "https://raw.githubusercontent.com/wiki/camsys/onebusaway-nyc/stif_transformations.md";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }

    @Ignore
    @Test
    public void testLoadAndTransformFromFileAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/onebusaway-stif-transformer-impl/src/test/resources/stifs_mtabc";
        String transform = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/onebusaway-stif-transformer-impl/src/test/resources/loadTransformByFileTest.json";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }

    @Ignore
    @Test
    public void testLoadAndTransformFromStringAndPrint() {

        String[] inputPaths = new String[1];
        inputPaths[0] = "/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/onebusaway-stif-transformer-impl/src/test/resources/stifs_mtabc";
        //inputPaths[0] = "/var/folders/2t/k5nkxf3s49153ccm9h7wqmx40000gn/T/tmp1580408549199/stif/stif/non_holiday/stif.m_0034sb.212123.sat";
        String transform = "{\"op\":\"update\",\"match\":{\"class\":\"EventRecord\",\"distanceFromStartOfTrip\":\"0\"},\"update\":{\"boardingAlightingFlag\":\"m\"}}";
        String outputPath = "/Users/caylasavitzky/Downloads/Stif-transformer" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }


}
