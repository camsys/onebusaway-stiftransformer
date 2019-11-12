package org.onebusaway.onebusaway_stif_transformer.model;

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
