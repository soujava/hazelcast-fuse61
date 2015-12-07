package com.hazelcast.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherVO {

    CoordVO coord;

    String base;

    public CoordVO getCoord() {
        return coord;
    }

    public void setCoord(CoordVO coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
