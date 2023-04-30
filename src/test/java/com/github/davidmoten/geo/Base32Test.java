package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Base32Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void encodeBase32() throws Exception {
        String encode = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode);
        encode = Base32.encodeBase32(-75324,4);
        assertEquals("-29jw",encode);
        encode = Base32.encodeBase32(75324,9);
        assertEquals("0000029jw",encode);
        encode = Base32.encodeBase32(-75324,9);
        assertEquals("-0000029jw",encode);
        encode = Base32.encodeBase32(10);
        assertEquals("00000000000b",encode);

    }

    @Test
    public void decodeBase32() {
        long decode = Base32.decodeBase32("29jw");
        assertEquals(75324,decode);
        decode = Base32.decodeBase32("-29jw");
        assertEquals(-75324,decode);
        decode = Base32.decodeBase32("");
        assertEquals(0,decode);
    }

    @Test
    public void testEncodeBase32() {
        String encode = Base32.encodeBase32(75324);
        assertEquals("0000000029jw", encode);
        encode = Base32.encodeBase32(-75324);
        assertEquals("-0000000029jw",encode);
    }

    @Test
    public void getCharIndex() {
        try{
            Base32.getCharIndex('e');
        }catch (IllegalArgumentException e){
            String s = "not a base32 character: e";
            assertEquals(s,e.getMessage());
        }
        int i = Base32.getCharIndex('0');
        assertEquals(0,i);
    }

    @Test
    public void padLeftWithZerosToLength() {
        String s = Base32.padLeftWithZerosToLength("29jw",4);
        assertEquals(s,"29jw");
        s = Base32.padLeftWithZerosToLength("29jw",5);
        assertEquals(s,"029jw");
    }
}