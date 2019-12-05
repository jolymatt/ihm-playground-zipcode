package com.ihm.playground.zipcode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LatLng {

    private Float lat;
    private Float lng;

    public LatLng(Float lat, Float lng){
        this.lat = lat;
        this.lng= lng;
    }

}
