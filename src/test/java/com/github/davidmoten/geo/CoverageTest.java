package com.github.davidmoten.geo;

import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.IconView;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class CoverageTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getHashes() {
        Set<String> hash = Collections.singleton("29jw");
        double ratio = 1.0;
        Coverage coverage = new Coverage(hash,ratio);
        Set<String> ans = coverage.getHashes();
        assertEquals(hash,ans);

        hash = Collections.singleton("-29jw");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getHashes();
        assertEquals(hash,ans);

        hash = Collections.singleton("");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getHashes();
        assertEquals(hash,ans);

    }

    @Test
    public void getRatio() {
        Set<String> hash = Collections.singleton("29jw");
        double ratio = 1.0;
        Coverage coverage = new Coverage(hash,ratio);
        double ans = coverage.getRatio();
        assertEquals(Double.doubleToLongBits(ratio),Double.doubleToLongBits(ans));

        hash = Collections.singleton("29jw");
        ratio = 0.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getRatio();
        assertEquals(Double.doubleToLongBits(ratio),Double.doubleToLongBits(ans));

        hash = Collections.singleton("29jw");
        ratio = -1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getRatio();
        assertEquals(Double.doubleToLongBits(ratio),Double.doubleToLongBits(ans));
    }

    @Test
    public void getHashLength() {
        Set<String> hash = Collections.singleton("29jw");
        double ratio = 1.0;
        Coverage coverage = new Coverage(hash,ratio);
        int ans = coverage.getHashLength();
        assertEquals(4,ans);

        hash = Collections.singleton("-29jw");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getHashLength();
        assertEquals(5,ans);

        hash = Collections.singleton("");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.getHashLength();
        assertEquals(0,ans);

    }

    @Test
    public void ToString() {
        Set<String> hash = Collections.singleton("29jw");
        double ratio = 1.0;
        Coverage coverage = new Coverage(hash,ratio);
        String ans = coverage.toString();
        assertEquals("Coverage [hashes=" + "[29jw]" + ", ratio=" + 1.0 + "]",ans);

        hash = Collections.singleton("-29jw");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.toString();
        assertEquals("Coverage [hashes=" + "[-29jw]" + ", ratio=" + 1.0 + "]",ans);

        hash = Collections.singleton("");
        ratio = 1.0;
        coverage = new Coverage(hash,ratio);
        ans = coverage.toString();
        assertEquals("Coverage [hashes=" + "[]" + ", ratio=" + 1.0 + "]",ans);

    }
    @Test
    public void testCoverageAllWorldLeaflet() {
        double topLeftLat = 90;
        double topLeftLon = -703;
        double bottomRightLat = -90;
        double bottomRightLon = 624;
        Coverage coverage = GeoHash.coverBoundingBox(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon, 1);
        assertEquals(Sets.newHashSet( "0", "2", "8", "b"),
                coverage.getHashes());
    }

}