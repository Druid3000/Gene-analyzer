package com.epam.gene_analyzer.model;

import java.awt.*;

public class Pixel {

    private int x;
    private int y;

    private int R;
    private int G;
    private int B;

    public double getIntensity() {
        if ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11 > 0)
            return ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11);
        else
            return 0.000000001;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xCoordinate) {
        x = xCoordinate;
    }

    public void setY(int yCoordinate) {
        y = yCoordinate;
    }

    public void setR(int red) {
        R = red;
    }

    public void setG(int green) {
        G = green;
    }

    public void setB(int blue) {
        B = blue;
    }

    public void setColor(Color c) {
        R = c.getRed();
        G = c.getGreen();
        B = c.getBlue();
    }

    public Color getColor() {
        return new Color(R, G, B);
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + R + "," + G + "" + B +
                '}';
    }
}
