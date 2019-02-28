package com.epam.gene_analyzer.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PixelTest {

    private Pixel pixel;
    private int[] sourceRGB = { 1, 2, 3 };
    private double expectedIntensity;

    @Before
    public void initTest() {
        pixel = new Pixel();
        expectedIntensity = 1.81;
        pixel.setR(sourceRGB[0]);
        pixel.setG(sourceRGB[1]);
        pixel.setB(sourceRGB[2]);
    }

    @Test
    public void testGetIntensity() {
        double actual = pixel.getIntensity();
        assertEquals((long) expectedIntensity, (long) actual);
    }

    @Test
    public void testSetX(){
        pixel.setX(1);
        assertEquals(1,pixel.getX());
    }

    @Test
    public void testSetY(){
        pixel.setY(1);
        assertEquals(1,pixel.getY());
    }

    @Test
    public void  testSetColor(){
        pixel.setColor(new Color(10,20,30));
        assertEquals(new Color(10,20,30),pixel.getColor());
    }
}