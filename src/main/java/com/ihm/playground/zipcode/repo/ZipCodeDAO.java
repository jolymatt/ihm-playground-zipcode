package com.ihm.playground.zipcode.repo;

import com.ihm.playground.zipcode.domain.ZipCode;

import java.util.List;

public interface ZipCodeDAO {

    public List<ZipCode> findAllZipCodeBasedOnLatLngByPreBuiltQuery(String sqlQuery);
    public List<ZipCode> findAllZipCodeBasedOnLatitudeAndLongitude(Float maxLatitude, Float minLatitude, Float maxLongitude, Float minLongitude);
}
