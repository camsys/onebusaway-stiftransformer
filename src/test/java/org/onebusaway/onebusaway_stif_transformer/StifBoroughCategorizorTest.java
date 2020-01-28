package org.onebusaway.onebusaway_stif_transformer;
import org.junit.Ignore;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.impl.StifLoaderImpl;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformFactory;
import org.onebusaway.onebusaway_stif_transformer.transformer.StifTransformer;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformContext;
import org.onebusaway.onebusaway_stif_transformer.transformer.TransformSpecificationException;




public class StifBoroughCategorizorTest {

    /*@Test
    public void testStifBoroughCategorizor(){
        StifLoaderImpl stifLoader = new StifLoaderImpl();
        File stifsPath = new File("/Users/caylasavitzky/src/mtaoba/onebusaway-stiftransformer/src/test/resources/stifs_mtabc");
        List<File> stifFiles = new ArrayList<>();
        stifFiles.add(stifsPath);
        HashMap<String,StifSupport> stifsMap = new HashMap<String,StifSupport>();
        stifLoader.load(stifFiles, stifsMap);

        StifBoroughCategorizor stifBoroughCategorizor = new StifBoroughCategorizor();
        for (Map.Entry<String, StifSupport> entry : stifsMap.entrySet()) {
            stifBoroughCategorizor.initialize(entry.getValue());
        }

    }*/
}
