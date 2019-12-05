package com.ihm.playground.zipcode.controller;

import com.ihm.playground.zipcode.domain.ZipCode;
import com.ihm.playground.zipcode.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/zip")
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping(path = "/zipcode")
    public @ResponseBody
    Iterable<ZipCode> getAllUsers(@RequestParam(name = "latlngs") String latlngs) {

        //select zip, latitude, longitude from zipcodes where ((latitude>37.5772) and (latitude<45.2111) and (longitude>-105.1488) and (longitude<-90.8226) )
        System.out.println("Controller = " + latlngs);
        return zipCodeService.retrieveZipCodeByLatLngs(latlngs);

    }

}
