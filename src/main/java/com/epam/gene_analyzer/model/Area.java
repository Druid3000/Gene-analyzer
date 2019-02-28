package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/** Model class for creating Area.
 * Get pixel and array of pixels around chosen pixel.
 *
 */
public class Area {
    private ArrayList<Pixel> pixels;
    private ArrayList<Pixel> perimeter;
    private double delta;
    private static final AtomicInteger id = new AtomicInteger(0);
    private int idThis = 1;

    /** Method for setting area
     *
     * @param pixel chosen pixel
     * @param image picture
     * @param del value of delta
     */
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

    /** Method for getting array of pixels
     *
     * @return array
     */
    public ArrayList<Pixel> getArea() {
        return pixels;
    }

    /** Method for getting array of perimeter
     *
     * @return array
     */
    public ArrayList<Pixel> getAreaPerimeter() {
        return perimeter;
    }

    /** Method for getting id of area
     *
     * @return id
     */
    public int getId() {
        return idThis;
    }


    /** Method with Area selection logic.
     * Using: side for showing where the check came from.
     * Side = 0 - start; 1 - from the left; 2 - from the right; 3 - from above; 4 - from bottom.
     *
     * @param allPixels pixels
     * @param allPixelsUnchanged unchanged pixels
     * @param x coordinate
     * @param y coordinate
     * @param intensity of pixel
     * @param side direction of check
     */
    private void checkNear(Pixel[][] allPixels, Pixel[][] allPixelsUnchanged, int x, int y, double intensity, int side) {
        double newIntensity;
        if ((x - 1 >= 0) & side != 1)
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
        if (x + 1 < allPixels.length & side != 2)
        {
            newIntensity = allPixels[x + 1][y].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x + 1][y]);
                allPixels[x + 1][y].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x + 1, y, intensity, 1);
            } else if (allPixels[x + 1][y].getIntensity() < 256) perimeter.add(allPixelsUnchanged[x + 1][y]);
        }
        if (y - 1 >= 0 & side != 3)
        {
            newIntensity = allPixels[x][y - 1].getIntensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(allPixelsUnchanged[x][y - 1]);
                allPixels[x][y - 1].setR(1000000);
                checkNear(allPixels, allPixelsUnchanged, x, y - 1, intensity, 4);
            } else if (allPixels[x][y - 1].getIntensity() < 256) perimeter.add(allPixelsUnchanged[x][y - 1]);
        }
        if (y + 1 < allPixels[0].length & side != 4)
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

    /** Method for creating a two-dimensional array of picture
     *
     * @param image picture
     * @return array
     */
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