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
package org.onebusaway.onebusaway_stif_transformer_impl.model;

public class AgencyAndId {

    private String agency;
    private String id;

    public AgencyAndId(String agency, String id){
        this.agency = agency;
        this.id = id;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency() {
        return agency;
    }

    public String getId() {
        return id;
    }
}
