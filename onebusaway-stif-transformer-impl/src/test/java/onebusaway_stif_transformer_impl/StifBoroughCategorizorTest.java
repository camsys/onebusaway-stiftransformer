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
