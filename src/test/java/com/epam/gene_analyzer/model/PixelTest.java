package com.epam.gene_analyzer.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PixelTest {

    private Pixel pixel;
    private int[] sourceRGB = { 1, 2, 3 };
    private double expected;

    @Before
    public void initTest() {
        pixel = new Pixel();
        expected = 1.81;
        pixel.setR(sourceRGB[0]);
        pixel.setG(sourceRGB[1]);
        pixel.setB(sourceRGB[2]);
    }

    @Test
    public void testGetIntensity() {
        double actual = pixel.getIntensity();
        assertEquals((long) expected, (long) actual);
    }
}