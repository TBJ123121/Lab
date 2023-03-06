package com.github.davidmoten.geo;

import com.github.davidmoten.geo.mem.Info;
import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class infoTest {
    Info<Integer,Integer> info = new Info(1,1,1,1, Optional.of(1));

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void id() {
        Optional<Integer> idd = info.id();
        assertEquals(Optional.of(1),idd);
    }

    @Test
    public void lat() {
        double s = info.lat();
        assertEquals(1,s,0);
    }

    @Test
    public void lon() {
        double s = info.lon();
        assertEquals(1,s,0);
    }

    @Test
    public void value() {
    }

    @Test
    public void testToString() {
    }
}