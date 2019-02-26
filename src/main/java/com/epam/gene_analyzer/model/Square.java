package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Square {
    private ArrayList<Pixel> pixels = new ArrayList<Pixel>();

    public Square(Pixel p1, Pixel p2, BufferedImage image){
        int x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY(), y2 = p2.getY();
        Pixel[][] allPixels;
        int h = image.getHeight(), w = image.getWidth(), R, G, B;
        allPixels = new Pixel[w][h];
        Color c;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                c = new Color(image.getRGB(i, j));
                R = c.getRed();
                G = c.getGreen();
                B = c.getBlue();
                Pixel p = new Pixel();
                p.setX(i);
                p.setY(j);
                p.setR(R);
                p.setG(G);
                p.setB(B);
                allPixels[i][j] = p;
            }
        }

        //заполнение
        for (int i=x1; i<x2; i++){
            for (int j=y2; j<y1; j++){
                pixels.add(allPixels[i][j]);
            }
        }
    }

    public ArrayList<Pixel> getArray() {
        return pixels;
    }

}
