package com.epam.gene_analyzer.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/** Model class for creating Line.
 * Get pixels from picture, get color of pixels, get intensity, get density, get array of pixels for drawing line.
 *
 */
public class Line {
    private static final AtomicInteger id = new AtomicInteger(0);
    private int idThis;
    private double maxDensity;
    private Color color;
    private ArrayList<Pixel> pixels = new ArrayList<Pixel>();

    /** Method for drawing lines.
     * Get coordinates, calculate segment length and number of pixels.
     * Get color of pixels.
     * Fill array of pixels on the segment for creating line.
     * Color assignment for lines in graphic.
     *
     * @param p1 pixel
     * @param p2 pixel
     * @param image picture
     */
    public Line(Pixel p1, Pixel p2, BufferedImage image) {
        int x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY(), y2 = p2.getY();
        double k, b;
        k = (double) (y1 - y2) / (x1 - x2);
        b = y1 - k * x1;
        int length = Math.max(Math.abs(x1 - x2), (Math.abs(y1 - y2)));
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

        if (Math.abs(k) >= 1) {
            double x, y = y1;
            for (int i = 0; i < length; i++) {
                x = (y - b) / k;
                pixels.add(allPixels[(int) x][(int) y]);
                if (y1 < y2) y++;
                else y--;
            }
        } else {
            double x = x1, y;
            for (int i = 0; i < length; i++) {
                y = k * x + b;
                pixels.add(allPixels[(int) x][(int) y]);
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

    /** Method for getting line id
     *
     * @return id
     */
    public int getId() {
        return idThis;
    }

    /** Method for getting max value of density
     *
     * @param intensity of pixel
     * @return max density
     */
    public double getMaxDensity(double intensity) {
        for (int a = 0; a < pixels.size(); a++) {
            if (pixels.get(a).getIntensity() <= 1) {
                maxDensity = 2.0;
                break;
            } else if (Math.log10(intensity / pixels.get(a).getIntensity()) > maxDensity)
                maxDensity = Math.log10(intensity / pixels.get(a).getIntensity());
        }
        return maxDensity;
    }

    /** Method for scaling on the X axis
     *
     * @return X
     */
    public int getLengthX() {
        return pixels.get(pixels.size() - 1).getX() - pixels.get(0).getX();
    }

    /** Method for getting segment length and number of pixels
     *
     * @return number of pixels
     */
    public int getLength() {
        return pixels.size();
    }

    /** Method for getting array of pixels
     *
     * @return array
     */
    public ArrayList<Pixel> getArray() {
        return pixels;
    }

    /** Method for getting pixel intensity
     *
     * @param id of pixel
     * @return intensity
     */
    public double getPixelIntensity(int id) {
        return pixels.get(id).getIntensity();
    }

    /**  Method for getting pixel color
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }
}
