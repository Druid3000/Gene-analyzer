package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Area {
    private ArrayList<Pixel> pixels;
    private ArrayList<Pixel> perimeter;
    private double delta;
    private static final AtomicInteger id = new AtomicInteger(0);
    private int idThis = 1;

    public void setArea(Pixel pixel, BufferedImage image, double del) {
        delta = del;
        pixels = new ArrayList<Pixel>();
        perimeter = new ArrayList<Pixel>();
        Pixel[][] allPixels = getPixelsArrayFromImage(image);
        Pixel[][] allPixelsUnchanged = getPixelsArrayFromImage(image);
        int x = pixel.getX(), y = pixel.getY();
        double intensity = pixel.getIntensity();
        if (intensity == 0) intensity = 0.000001;
        pixels.add(pixel);
        allPixels[x][y].setR(1000000);
        checkNear(allPixels, allPixelsUnchanged, x, y, intensity, 0);
        idThis = id.incrementAndGet();
    }

    public ArrayList<Pixel> getArea() {
        return pixels;
    }

    public ArrayList<Pixel> getAreaPerimeter() {
        return perimeter;
    }

    public int getId() {
        return idThis;
    }

    //Логика выделения областей
    private void checkNear(Pixel[][] allPixels, Pixel[][] allPixelsUnchanged, int x, int y, double intensity, int side) {//side - показывает, откуда пришла проверка
        //side = 0 - начало; 1 - слева; 2 - справа; 3 - сверху; 4 - снизу.
        double newIntensity;
        if ((x - 1 >= 0) & side != 1)//слева от пикселя
        {
            newIntensity = allPixels[x - 1][y].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - intensity) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x - 1][y]);
                allPixels[x - 1][y].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x - 1, y, intensity, 2);
            } else if (allPixels[x - 1][y].getIntensity() < 256) {
                perimeter.add(allPixelsUnchanged[x - 1][y]);
            }
        }
        if (x + 1 < allPixels.length & side != 2)// справа от пикселя
        {
            newIntensity = allPixels[x + 1][y].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x + 1][y]);
                allPixels[x + 1][y].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x + 1, y, intensity, 1);
            } else if (allPixels[x + 1][y].getIntensity() < 256) perimeter.add(allPixelsUnchanged[x + 1][y]);
        }
        if (y - 1 >= 0 & side != 3)//сверху от пикселя
        {
            newIntensity = allPixels[x][y - 1].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x][y - 1]);
                allPixels[x][y - 1].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x, y - 1, intensity, 4);
            } else if (allPixels[x][y - 1].getIntensity() < 256) perimeter.add(allPixelsUnchanged[x][y - 1]);
        }
        if (y + 1 < allPixels[0].length & side != 4)//снизу от пикселя
        {
            newIntensity = allPixels[x][y + 1].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x][y + 1]);
                allPixels[x][y + 1].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x, y + 1, intensity, 3);
            } else if (allPixels[x][y + 1].getIntensity() < 256) perimeter.add(allPixelsUnchanged[x][y + 1]);
        }
    }

    //Создание двумерного массива из картика
    private Pixel[][] getPixelsArrayFromImage(BufferedImage image) {
        Pixel[][] allPixels;
        int h = image.getHeight(), w = image.getWidth(), R, G, B;
        allPixels = new Pixel[w][h];
        Color c;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                c = new Color(image.getRGB(i, j));
                Pixel p = new Pixel();
                p.setX(i);
                p.setY(j);
                p.setColor(c);
                allPixels[i][j] = p;
            }
        }
        return allPixels;
    }
}