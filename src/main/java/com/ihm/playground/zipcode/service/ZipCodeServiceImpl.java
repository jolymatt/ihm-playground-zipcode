package com.ihm.playground.zipcode.service;

import com.ihm.playground.zipcode.domain.LatLng;
import com.ihm.playground.zipcode.domain.Polygon;
import com.ihm.playground.zipcode.domain.ZipCode;
import com.ihm.playground.zipcode.repo.ZipCodeDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
public class ZipCodeServiceImpl implements ZipCodeService {

    @Autowired
    private ZipCodeDAO zipCodeDAO;

    public static void main(String args[]) {
        try {
            ZipCodeServiceImpl service = new ZipCodeServiceImpl();
            Polygon polygon = service.createPolygon("[45.2111,-101.4574][38.8889,-105.1488][37.5772,-91.6136][42.5511,-94.9535][44.776,-96.1839][44.2117,-90.8226][39.975,-92.6683]");
            log.info("Polygon={} ", polygon.toString());
            String sqlQuery = service.buildSQLMinMaxLatLng(polygon);
            log.info(sqlQuery);
        } catch (Exception exception) {
           log.error(exception.getMessage());
        }
    }

    public List<ZipCode> retrieveZipCodeByLatLngs(String latLngs) {
        List<ZipCode> includedZipCodeList = new ArrayList<>();
        try {
            Polygon polygon = createPolygon(latLngs);
            String sqlQuery = buildSQLMinMaxLatLng(polygon);
            log.info("SQL Query ={} ", sqlQuery);
            List<ZipCode> zipCodeList = zipCodeDAO.findAllZipCodeBasedOnLatLngByPreBuiltQuery(sqlQuery);
            log.info("Max Rows ={} ",zipCodeList.size());
            if (null != zipCodeList && !zipCodeList.isEmpty()) {
                log.info("find points inside polgyon");
                includedZipCodeList = getZipCodesInPolygon(polygon, zipCodeList);

            }

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return includedZipCodeList;
    }

    protected Polygon createPolygon(String latlngs) {
        //[45.2111,-101.4574][38.8889,-105.1488][37.5772,-91.6136][42.5511,-94.9535][44.776,-96.1839][44.2117,-90.8226][39.975,-92.6683]
        Polygon polygon = new Polygon();
        String latlngsArr[] = latlngs.split("]");
        List<LatLng> latLngList = new ArrayList<>();
        for (String latlng : latlngsArr) {
            latlng = latlng.replace("[", "").replace("]", "");
            if (latlng != "") {
                String latlngsplit[] = latlng.split(",");
                LatLng latLng = new LatLng(new Float(latlngsplit[0]), new Float(latlngsplit[1]));
                latLngList.add(latLng);
            }
        }
        polygon.setLatlngs(latLngList);
        return polygon;
    }

    protected String buildSQLMinMaxLatLng(Polygon polygon) {
        float latmin = 85;
        float latmax = -85;
        float lngmin = 180;
        float lngmax = -180;

        List<LatLng> latLngList = polygon.getLatlngs();
        for (LatLng latLng : latLngList) {
            if (latmin > latLng.getLat()) latmin = latLng.getLat();
            if (latmax < latLng.getLat()) latmax = latLng.getLat();
            if (lngmin > latLng.getLng()) lngmin = latLng.getLng();
            if (lngmax < latLng.getLng()) lngmax = latLng.getLng();
        }

        String parameters = "( (latitude>" + latmin + ") and (latitude<" + latmax + ") and (longitude>" + lngmin + ") and (longitude<" + lngmax + ") )";

        String sql = "select zip, latitude, longitude from zipcodes where " + parameters;
        return sql;
    }

    protected boolean pointInPolygon(LatLng latLng, Polygon polygon) {

        /*
                          v2
                        o
                       /
                      / c (intersection)
            o--------x----------------------> to infinity
            t       /
                   /
                  /
                 o
                 v1
        */

        /*

                                  t.y - v1.y
            c.x = v1.x + ----------- * (v2.x - v1.x)
                         v2.y - v1.y

         */
        int c = 0;
        List<LatLng> latLngList = polygon.getLatlngs();
        LatLng latLng1 = latLngList.get(0);
        for (int i = 1; i <= latLngList.size(); i++) {
            LatLng latLng2 = latLngList.get(i % latLngList.size());
            if ((latLng.getLng() > java.lang.Math.min(latLng1.getLng(), latLng2.getLng()))
                    && (latLng.getLng() <= java.lang.Math.max(latLng1.getLng(), latLng2.getLng()))
                    && (latLng.getLat() <= java.lang.Math.max(latLng1.getLat(), latLng2.getLat()))
                    && (latLng1.getLng() != latLng2.getLng())) {
                float xinters = (latLng.getLng() - latLng1.getLng()) * (latLng2.getLat() - latLng1.getLat()) / (latLng2.getLng() - latLng1.getLng()) + latLng1.getLat();
                if ((latLng1.getLat() == latLng2.getLat()) || (latLng.getLat() <= xinters)) {
                    c++;
                }
            }
            latLng1 = latLng2;

        }
        return c % 2 != 0;
    }


    private List<ZipCode> getZipCodesInPolygon(Polygon polygon, List<ZipCode> zipCodeList) {
        List<ZipCode> includedZipCodeList = new ArrayList<>();
        for (ZipCode zipCode : zipCodeList) {
            if (this.pointInPolygon(new LatLng(zipCode.getLatitude(), zipCode.getLongitude()), polygon)) {
                includedZipCodeList.add(zipCode);
            }
        }
        return includedZipCodeList;
    }
}
