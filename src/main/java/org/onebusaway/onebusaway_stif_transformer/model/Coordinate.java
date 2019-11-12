package org.onebusaway.onebusaway_stif_transformer.model;

public class Coordinate {
    private double lat;
    private double lon;

    public Coordinate(double la,double lo){
        this.lat = la;
        this.lon = lo;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
