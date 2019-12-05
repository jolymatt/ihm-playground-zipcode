package com.ihm.playground.zipcode.repo;

//import com.ihm.playground.zipcode.domain.ZipCode;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZipCodeRepository  //extends CrudRepository<ZipCode,Integer>
{
//
//    //select zip, latitude, longitude from ZipCode where ((latitude>37.5772) and (latitude<45.2111) and (longitude>-105.1488) and (longitude<-90.8226) )
//    @Query("select zip, latitude, longitude from zipcodes where ((latitude>:minLatitude) and (latitude<:maxLatitude) and (longitude>:minLongitude) and (longitude<:maxLongitude) )")
//    public List<ZipCode> find(@Param("maxLatitude") Float maxLatitude,@Param("minLatitude") Float minLatitude,@Param("maxLongitude") Float maxLongitude,@Param("minLongitude") Float minLongitude);

}
