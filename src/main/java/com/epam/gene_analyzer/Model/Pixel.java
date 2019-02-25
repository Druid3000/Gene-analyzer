package com.epam.gene_analyzer.Model;

import java.awt.*;

public class Pixel {
    private int x;
    private int y;
    private int R = 0, G = 0, B = 0;

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

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

    public void setX(int xx) {
        x = xx;
    }

    public void setY(int yy) {
        y = yy;
    }

    public void setR(int r) {
        R = r;
    }

    public void setG(int g) {
        G = g;
    }

    public void setB(int b) {
        B = b;
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
