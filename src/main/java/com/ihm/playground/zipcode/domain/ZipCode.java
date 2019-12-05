package com.ihm.playground.zipcode.domain;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Setter
@Getter

public class ZipCode {

    private Integer zip;
    private Float latitude;
    private Float longitude;

}
