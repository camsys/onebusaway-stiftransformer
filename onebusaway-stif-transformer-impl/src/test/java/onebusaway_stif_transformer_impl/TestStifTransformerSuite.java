/**
 * Copyright (C) 2020 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package onebusaway_stif_transformer_impl;
import org.junit.Test;
import org.onebusaway.onebusaway_stif_transformer_impl.StifTransformerSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TestStifTransformerSuite {
    private static Logger _log = LoggerFactory.getLogger(TestStifTransformerSuite.class);

    @Test
    public void testLoadAndTransformFromSiteAndPrint() {

        ClassLoader classLoader = new StifBoroughCategorizorTest().getClass().getClassLoader();
        File stifsPath = new File(classLoader.getResource("onebusaway_stif_transformer_impl/stifs_mtabc").getFile());

        String[] inputPaths = new String[1];
        inputPaths[0] = stifsPath.getAbsolutePath();
        //inputPaths[0] = "/var/folders/2t/k5nkxf3s49153ccm9h7wqmx40000gn/T/tmp1580408549199/stif/stif/non_holiday/stif.m_0034sb.212123.sat";
        String transform = "https://raw.githubusercontent.com/wiki/camsys/onebusaway-nyc/stif_transformations.md";
        String outputPath = System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }

    @Test
    public void testLoadAndTransformFromFileAndPrint() {

        ClassLoader classLoader = new StifBoroughCategorizorTest().getClass().getClassLoader();
        File stifsPath = new File(classLoader.getResource("onebusaway_stif_transformer_impl/stifs_mtabc").getFile());
        File transformationFile = new File(classLoader.getResource("onebusaway_stif_transformer_impl/loadTransformByFileTest.json").getFile());

        String[] inputPaths = new String[1];
        inputPaths[0] = stifsPath.getAbsolutePath();
        String transform = transformationFile.getAbsolutePath();
        String outputPath = System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis();

        StifTransformerSuite transformerSuite = new StifTransformerSuite();
        transformerSuite.setInputPaths(inputPaths);
        transformerSuite.setTranform(transform);
        transformerSuite.setOutputPath(outputPath);
        transformerSuite.run();
    }

}
