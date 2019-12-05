package com.ihm.playground.zipcode.repo;

import com.ihm.playground.zipcode.domain.ZipCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ZipCodeDAOImpl implements ZipCodeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ZipCode> findAllZipCodeBasedOnLatitudeAndLongitude(Float maxLatitude, Float minLatitude, Float maxLongitude, Float minLongitude) {
        List<ZipCode> list = new ArrayList<>();
        try {

            String sql = "select zip, latitude, longitude from zipcodes where ((latitude>?) and (latitude<?) and (longitude>?) and (longitude<?) )";

            list = jdbcTemplate.query(sql, new Object[]{minLatitude, maxLatitude, minLongitude, maxLongitude}, new ZipRowMapper());

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return list;
    }


    public List<ZipCode> findAllZipCodeBasedOnLatLngByPreBuiltQuery(String sqlQuery) {
        List<ZipCode> list = new ArrayList<>();
        try {

            list = jdbcTemplate.query(sqlQuery, new ZipRowMapper());

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return list;
    }
}
