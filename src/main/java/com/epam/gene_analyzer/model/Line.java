package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Line {
    private static final AtomicInteger id = new AtomicInteger(0);
    private int idThis;
    private double maxDensity;
    private Color color;
    private ArrayList<Pixel> pixels = new ArrayList<Pixel>();

    public Line(Pixel p1, Pixel p2, BufferedImage image) {
        int x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY(), y2 = p2.getY();
        double k, b;
        k = (double) (y1 - y2) / (x1 - x2);
        b = y1 - k * x1;
        int length = Math.max(Math.abs(x1 - x2), (Math.abs(y1 - y2)));//длина отрезка (кол-во пикселей)
        int h = image.getHeight(), w = image.getWidth(), R, G, B;

        Pixel[][] allPixels = new Pixel[w][h];
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

        // заполнение
        if (Math.abs(k) >= 1) {
            double x, y = y1;
            for (int i = 0; i < length; i++) {
                x = (y - b) / k;
                pixels.add(allPixels[(int) x][(int) y]); //pxls[i]=allPixels[(int)x][(int)y];//заполняю массив пикселей на отрезке
                if (y1 < y2) y++;
                else y--;
            }
        } else {
            double x = x1, y;
            for (int i = 0; i < length; i++) {
                y = k * x + b;
                pixels.add(allPixels[(int) x][(int) y]); //pxls[i] = allPixels[(int) x][(int) y];//заполняю массив пикселей на отрезке
                if (x1 < x2) x++;
                else x--;
            }
        }

        idThis = id.incrementAndGet();

        switch (idThis % 10) {
            case 1:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.CYAN;
                break;
            case 4:
                color = Color.GREEN;
                break;
            case 5:
                color = Color.MAGENTA;
                break;
            case 6:
                color = Color.ORANGE;
                break;
            case 7:
                color = Color.PINK;
                break;
            case 8:
                color = Color.RED;
                break;
            case 9:
                color = Color.WHITE;
                break;
            case 0:
                color = Color.YELLOW;
                break;
            default:
                color = Color.YELLOW;

        }
    }

    public int getId() {
        return idThis;
    }

    public double getMaxDensity(double intensity) {
        for (int a = 0; a < pixels.size(); a++) {
            if (pixels.get(a).getIntensity() <= 1) {
                maxDensity = 2.9;
                break;
            } else if (Math.log10(intensity / pixels.get(a).getIntensity()) > maxDensity)
                maxDensity = Math.log10(intensity / pixels.get(a).getIntensity());
        }
        return maxDensity;
    }

    public int getLengthX() {   //метод для масштабирования по оси абсцисс
        return pixels.get(pixels.size() - 1).getX() - pixels.get(0).getX();
    }

    public int getLength() {
        return pixels.size();
    }

    public ArrayList<Pixel> getArray() {
        return pixels;
    }

    public double getPixelIntensity(int id) {
        return pixels.get(id).getIntensity();
    }

    public Color getColor() {
        return color;
    }
}