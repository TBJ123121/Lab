package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeoHashTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void right() {
        String rightstr = GeoHash.right("29jw");
        assertEquals("29jy",rightstr);
    }

    @Test
    public void left() {
        String leftstr = GeoHash.left("29jw");
        assertEquals("29jq",leftstr);
    }

    @Test
    public void top() {
        String topstr = GeoHash.top("29jw");
        assertEquals("29jx",topstr);
    }

    @Test
    public void bottom() {
        String bottomstr = GeoHash.bottom("29jw");
        assertEquals("29jt",bottomstr);
    }

    @Test
    public void adjacentHash() {
        String str_adjhash = GeoHash.adjacentHash("29jw",Direction.TOP,1);
        assertEquals("29jx",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.LEFT,-1);
        assertEquals("29jy",str_adjhash);
    }

    @Test
    public void neighbours() {
        List<String> str_neighbor  = GeoHash.neighbours("29jw");
        List<String> ans = Arrays.asList("29jq", "29jy", "29jx", "29jt", "29jr", "29jm", "29jz", "29jv");
        assertEquals(ans,str_neighbor);
    }

    @Test
    public void widthDegrees() {


    }

    @Test
    public void encodeHashToLong() {
        assertEquals(0x65c0000000000002L,GeoHash.encodeHashToLong(41.84,-71.2,2));
    }
}