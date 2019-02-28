package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/** Model class for creating Square.
 * Use array of pixels.
 * Get pixel coordinates of square, get height and width of square, ger arrays of pixels for creating square.
 *
 */
public class Square {
    private ArrayList<Pixel> pixels = new ArrayList<Pixel>();

    /** Method for getting coordinates and color of pixels for building square.
     *
     * @param p1 pixel
     * @param p2 pixel
     * @param image buffered picture
     */
    public Square(Pixel p1, Pixel p2, BufferedImage image){
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();

        Pixel[][] allPixels;
        int h = image.getHeight();
        int w = image.getWidth();

        int r;
        int g;
        int b;

        allPixels = new Pixel[w][h];
        Color c;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                c = new Color(image.getRGB(i, j));
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                Pixel p = new Pixel();
                p.setX(i);
                p.setY(j);
                p.setR(r);
                p.setG(g);
                p.setB(b);
                allPixels[i][j] = p;
            }
        }

        /** Method for filling array with pixel coordinates
         *
         */
        for (int i=x1; i<x2; i++){
            for (int j=y2; j<y1; j++){
                pixels.add(allPixels[i][j]);
            }
        }
    }

    /** Method for getting array with pixels
     *
     * @return array
     */
    public ArrayList<Pixel> getArray() {
        return pixels;
    }
}
