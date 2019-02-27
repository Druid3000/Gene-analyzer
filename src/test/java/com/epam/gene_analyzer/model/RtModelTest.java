package com.epam.gene_analyzer.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RtModelTest {

    RtModel rtm;
    Double[] source = {1.0, 2.0,3.0};
    Object expected;


    @Before
    public void initTest() {
        rtm = new RtModel();
        source[0] = 255.0;
        source[1] = 255.0;
        source[2] = 255.0;
        expected = 0.0;
    }

    @After
    public void afterTest() {
        rtm = null;
        source = null;
        expected = null;
    }


    @Test
    public void testSetValueAt() {
        Object actual = rtm.setData(1, source, 255.0)[1];
        assertEquals(expected, actual);
    }
}

/*
double source = 3.1415;
    String expected="3.1415";

    String actual = StringUtils.fromDouble(source);
    assertEquals("Unexpected string value", expected, actual);
 */