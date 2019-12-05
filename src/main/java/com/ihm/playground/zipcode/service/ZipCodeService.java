package com.ihm.playground.zipcode.service;

import com.ihm.playground.zipcode.domain.ZipCode;

import java.util.List;

public interface ZipCodeService {

    public List<ZipCode> retrieveZipCodeByLatLngs(String latLngs);

}
