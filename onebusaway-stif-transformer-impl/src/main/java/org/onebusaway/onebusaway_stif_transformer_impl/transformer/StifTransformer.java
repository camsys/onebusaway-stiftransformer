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
package org.onebusaway.onebusaway_stif_transformer_impl.transformer;


import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;

import java.util.ArrayList;

public class StifTransformer {

    ArrayList <StifTransformStrategy> transforms = new ArrayList<StifTransformStrategy>();
    StifSupport stifSupport;
    TransformContext context;


    public void addTransform(StifTransformStrategy strategy) {
        transforms.add(strategy);
    }

    public StifTransformStrategy getLastTransform() {
        return transforms.get(transforms.size()-1);
    }

    public void setStifSupport(StifSupport stifSupport) {
        this.stifSupport = stifSupport;
    }

    public void setContext(TransformContext context) {
        this.context = context;
    }

    public void run(){
        for ( StifTransformStrategy transform : transforms){
            transform.run(context,stifSupport);
        }
    }
}
