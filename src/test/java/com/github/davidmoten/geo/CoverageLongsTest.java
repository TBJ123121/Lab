package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

public class CoverageLongsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void getHashLength() {
        long[] hash = {1};
        double ratio = 1.0;
        CoverageLongs coverageLongs = new CoverageLongs(hash,1,ratio);
        int hashlen = coverageLongs.getHashLength();
        assertEquals(1,hashlen);

        hash = new long[]{};
        coverageLongs = new CoverageLongs(hash,0,ratio);
        hashlen = coverageLongs.getHashLength();
        assertEquals(0,hashlen);

    }

    @Test
    public void getCount() {
        long[] hash = {1};
        double ratio = 1.0;
        CoverageLongs coverageLongs = new CoverageLongs(hash,1,1.0);
        int count = coverageLongs.getCount();
        assertEquals(1,count);

        hash = new long[]{};
        coverageLongs = new CoverageLongs(hash,0,1.0);
        count = coverageLongs.getCount();
        assertEquals(0,count);

        hash = new long[]{};
        coverageLongs = new CoverageLongs(hash,-1,1.0);
        count = coverageLongs.getCount();
        assertEquals(-1,count);
    }

    @Test
    public void testToString() {
        long[] hash = {1};
        double ratio = 1.0;
        CoverageLongs coverageLongs = new CoverageLongs(hash,1,ratio);
        String str = coverageLongs.toString();
        assertNotEquals("Coverage [hashes=" + coverageLongs.getHashes() + ", ratio=" + coverageLongs.getRatio() + "]",str);
        System.out.println("Coverage [hashes=" + coverageLongs.getHashes() + ", ratio=" + coverageLongs.getRatio() + "]");

        hash = new long[]{};
        coverageLongs = new CoverageLongs(hash,0,ratio);
        str = coverageLongs.toString();
        assertNotEquals("Coverage [hashes=" + coverageLongs.getHashes() + ", ratio=" + coverageLongs.getRatio() + "]",str);
        System.out.println("Coverage [hashes=" + coverageLongs.getHashes() + ", ratio=" + coverageLongs.getRatio() + "]");

    }
}