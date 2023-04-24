package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.Assert.*;

public class GeoHashTest {

    private CoverageLongs coverageLongs;

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

        topstr = GeoHash.top("-29jw");
        assertEquals("-29jy",topstr);

        try {
            GeoHash.top("");
        }catch (IllegalArgumentException e){
            String s = "adjacent has no meaning for a zero length hash that covers the whole world";
            assertEquals(s,e.getMessage());
        }
    }

    @Test
    public void bottom() {
        String bottomstr = GeoHash.bottom("-29jw");
        assertEquals("-29jq",bottomstr);

        bottomstr = GeoHash.bottom("29jw");
        assertEquals("29jt",bottomstr);

        try {
            GeoHash.bottom("");
        }catch (IllegalArgumentException e){
            String s = "adjacent has no meaning for a zero length hash that covers the whole world";
            assertEquals(s,e.getMessage());
        }

    }

    @Test
    public void adjacentHash() {
        //無負號
        String str_adjhash = GeoHash.adjacentHash("29jw",Direction.BOTTOM,1);
        assertEquals("29jt",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.BOTTOM,0);
        assertEquals("29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.BOTTOM,-1);
        assertEquals("29jx",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("29jw",Direction.TOP,1);
        assertEquals("29jx",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.TOP,0);
        assertEquals("29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.TOP,-1);
        assertEquals("29jt",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("29jw",Direction.LEFT,1);
        assertEquals("29jq",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.LEFT,0);
        assertEquals("29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.LEFT,-1);
        assertEquals("29jy",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("29jw",Direction.RIGHT,1);
        assertEquals("29jy",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.RIGHT,0);
        assertEquals("29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("29jw",Direction.RIGHT,-1);
        assertEquals("29jq",str_adjhash);
        //有負號
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.BOTTOM,1);
        assertEquals("-29jq",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.BOTTOM,0);
        assertEquals("-29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.BOTTOM,-1);
        assertEquals("-29jy",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.TOP,1);
        assertEquals("-29jy",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.TOP,0);
        assertEquals("-29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.TOP,-1);
        assertEquals("-29jq",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.LEFT,1);
        assertEquals("-29jt",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.LEFT,0);
        assertEquals("-29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.LEFT,-1);
        assertEquals("-29jx",str_adjhash);

        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.RIGHT,1);
        assertEquals("-29jx",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.RIGHT,0);
        assertEquals("-29jw",str_adjhash);
        str_adjhash = GeoHash.adjacentHash("-29jw",Direction.RIGHT,-1);
        assertEquals("-29jt",str_adjhash);

    }

    @Test
    public void neighbours() {
        List<String> str_neighbor  = GeoHash.neighbours("29jw");
        List<String> ans = Arrays.asList("29jq", "29jy", "29jx", "29jt", "29jr", "29jm", "29jz", "29jv");
        assertEquals(ans,str_neighbor);

        str_neighbor  = GeoHash.neighbours("-29jw");
        ans = Arrays.asList("-29jt", "-29jx", "-29jy", "-29jq", "-29jv", "-29jm", "-29jz", "-29jr");
        assertEquals(ans,str_neighbor);

        try {
            GeoHash.neighbours("");
        }catch (IllegalArgumentException e)
        {
            String s = "adjacent has no meaning for a zero length hash that covers the whole world";
            assertEquals(s,e.getMessage());
        }

    }


    @Test
    public void encodeHashToLong() {
        assertEquals(0x65c0000000000002L,GeoHash.encodeHashToLong(41.84,-71.2,2));
    }


    @Test
    public void heightDegrees() {
        double res = GeoHash.heightDegrees(1);
        assertEquals(45,res,0);

        res = GeoHash.heightDegrees(15);
        assertEquals(1.3096723705530167E-9,res,0);

        res = GeoHash.heightDegrees(0);
        assertEquals(180,res,0);

        try{
            GeoHash.widthDegrees(-1);
        }catch (ArrayIndexOutOfBoundsException e){
            assertEquals("-1",e.getMessage());
        }
    }

    @Test
    public void widthDegrees() {
        double res = GeoHash.widthDegrees(1);
        assertEquals(45,res,0);

        res = GeoHash.widthDegrees(15);
        assertEquals(1.3096723705530167E-9,res,0);

        res = GeoHash.widthDegrees(0);
        assertEquals(360,res,0);

        try{
            GeoHash.widthDegrees(-1);
        }catch (ArrayIndexOutOfBoundsException e){
            assertEquals("-1",e.getMessage());
        }
    }

    @Test
    public void gridAsString() {
        Set<String> highlightThese = Collections.singleton("");
        String s = GeoHash.gridAsString("29jw",1,1,1,1,highlightThese);
        assertEquals("29jv \n",s);

        highlightThese = Collections.singleton("29jw");
        s = GeoHash.gridAsString("29jw",1,1,1,1,highlightThese);
        assertEquals("29jv \n",s);

        highlightThese = Collections.singleton("29jv");
        s = GeoHash.gridAsString("29jw",1,1,1,1,highlightThese);
        assertEquals("29JV \n",s);
    }

    @Test
    public void coverBoundingBoxLongs() {
        CoverageLongs coverageLongs;
        try{
            GeoHash.coverBoundingBoxLongs(0,0,0,0,0);
        }catch (IllegalArgumentException e){
            String s = "length must be greater than zero";
            assertEquals(s,e.getMessage());
        }

        try{
            GeoHash.coverBoundingBoxLongs(0,1,0,0,0);
        }catch (IllegalArgumentException e){
            String s = "topLeftLon must be <= bottomRighLon";
            assertEquals(s,e.getMessage());
        }

        try{
            GeoHash.coverBoundingBoxLongs(0,0,1,0,0);
        }catch (IllegalArgumentException e){
            String s = "topLeftLat must be >= bottomRighLat";
            assertEquals(s,e.getMessage());
        }

            coverageLongs = GeoHash.coverBoundingBoxLongs(1,0,0,1,1);
            assertEquals(1,coverageLongs.getCount());
            assertEquals(2025.0,coverageLongs.getRatio(),0);

    }

    @Test
    public void encodeHash() {
        String encodeHash = "";
        try{
            encodeHash = GeoHash.encodeHash(1,1,1);
        }catch (IllegalArgumentException e){
            assertEquals("s",encodeHash);
        }

        try{
            encodeHash = GeoHash.encodeHash(1,1,0);
        }catch (IllegalArgumentException e){
            String s = "length must be greater than zero";
            assertEquals("s",encodeHash);
        }

        try{
            encodeHash = GeoHash.encodeHash(100,1,1);
        }catch (IllegalArgumentException e){
            String s = "length must be greater than zero";
            assertEquals("s",encodeHash);
        }

        try{
            encodeHash = GeoHash.encodeHash(-100,1,1);
        }catch (IllegalArgumentException e){
            String s = "length must be greater than zero";
            assertEquals("s",encodeHash);
        }

        try{
            encodeHash = GeoHash.encodeHash(90,100,13);
        }catch (IllegalArgumentException e){
            String s = "length must be greater than zero";
            assertEquals("s",encodeHash);
        }
    }

    @Test
    public void fromLongToString() {

    }
}

