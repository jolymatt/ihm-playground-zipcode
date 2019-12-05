package com.ihm.playground.zipcode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Polygon {
    List<LatLng> latlngs;


}
