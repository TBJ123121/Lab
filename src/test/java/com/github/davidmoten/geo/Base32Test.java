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

<<<<<<< HEAD
=======
    //每個@Test都為測試個案
>>>>>>> e1c57c6 (first commit)
    @Test
    public void encodeBase32() throws Exception {
        String encode = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode);
<<<<<<< HEAD
    }

=======
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
    }


>>>>>>> e1c57c6 (first commit)
}