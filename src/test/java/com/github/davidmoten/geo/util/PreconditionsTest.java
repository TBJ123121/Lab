package com.github.davidmoten.geo.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PreconditionsTest {


    @Test(expected = NullPointerException.class)
    public void testCheckNotNullGivenNullThrowsException() {
        Preconditions.checkNotNull(null, "message");
    }
}