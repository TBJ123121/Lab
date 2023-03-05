package com.github.davidmoten.geo;

import org.junit.Test;

import static org.junit.Assert.*;

public class LatLongTest {

    @Test
    public void getLat() {
        double lat = 1.0;
        double lon = 2.0;
        LatLong latLong = new LatLong(lat,lon);
        assertEquals(Double.doubleToLongBits(1.0),Double.doubleToLongBits(latLong.getLat()));

    }

    @Test
    public void getLon() {
        double lat = 1.0;
        double lon = 2.0;
        LatLong latLong = new LatLong(lat,lon);
        assertEquals(Double.doubleToLongBits(2.0),Double.doubleToLongBits(latLong.getLon()));
    }

    @Test
    public void add() {
        double lat = 1.0;
        double lon = 2.0;
        LatLong latLong = new LatLong(lat,lon);
        LatLong newlatlong = latLong.add(1,1);
        assertEquals(lat+1,newlatlong.getLat(),0);
        assertEquals(lon+1,newlatlong.getLon(),0);

        newlatlong = latLong.add(-1,-1);
        assertEquals(lat-1,newlatlong.getLat(),0);
        assertEquals(lon-1,newlatlong.getLon(),0);

        newlatlong = latLong.add(0,0);
        assertEquals(lat,newlatlong.getLat(),0);
        assertEquals(lon,newlatlong.getLon(),0);


    }

    @Test
    public void testToString() {
        assertEquals("LatLong [lat=1.0, lon=2.0]",new LatLong(1.0,2.0).toString());
    }
}