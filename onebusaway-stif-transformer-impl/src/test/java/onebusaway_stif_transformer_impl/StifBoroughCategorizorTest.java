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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer_impl.StifBoroughCategorizor;
import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;
import org.onebusaway.onebusaway_stif_transformer_impl.impl.StifLoaderImpl;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer_impl.transformer.TransformSpecificationException;




public class StifBoroughCategorizorTest {

    @Test
    public void testStifBoroughCategorizor(){

        ClassLoader classLoader = new StifBoroughCategorizorTest().getClass().getClassLoader();
        File stifsPath = new File(classLoader.getResource("onebusaway_stif_transformer_impl/stifs_mtabc").getFile());

        StifLoaderImpl stifLoader = new StifLoaderImpl();
        List<File> stifFiles = new ArrayList<>();
        stifFiles.add(stifsPath);
        HashMap<String,StifSupport> stifsMap = new HashMap<String,StifSupport>();
        stifLoader.load(stifFiles, stifsMap);

        StifBoroughCategorizor stifBoroughCategorizor = new StifBoroughCategorizor();
        for (Map.Entry<String, StifSupport> entry : stifsMap.entrySet()) {
            stifBoroughCategorizor.initialize(entry.getValue());
        }

    }
}
