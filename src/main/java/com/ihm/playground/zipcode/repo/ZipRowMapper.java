package com.ihm.playground.zipcode.repo;

import com.ihm.playground.zipcode.domain.ZipCode;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ZipRowMapper implements RowMapper<ZipCode> {

    public ZipCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        ZipCode zipCode = new ZipCode();

        zipCode.setZip(rs.getInt("zip"));
        zipCode.setLatitude(rs.getFloat("latitude"));
        zipCode.setLongitude(rs.getFloat("longitude"));

        return zipCode;
    }
}
