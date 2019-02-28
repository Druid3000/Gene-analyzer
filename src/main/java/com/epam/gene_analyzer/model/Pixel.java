package com.epam.gene_analyzer.model;

import java.awt.*;

/** Model class for Pixel.
 * Use x, y for coordinates, R, G, B for color
 * Get X, Y, get coordinates, get colors and color of pixel, get intensity.
 *
 */
public class Pixel {

    private int x;
    private int y;

    private int R;
    private int G;
    private int B;

    /** Method for getting intensity
     *
     * @return intensity
     */
    public double getIntensity() {
        if ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11 > 0)
            return ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11);
        else
            return 0.000000001;
    }

    /** Method for getting X
     *
     * @return X
     */
    public int getX() {
        return x;
    }

    /** Method for getting Y
     *
     * @return Y
     */
    public int getY() {
        return y;
    }

    /** Method for setting X coordinate
     *
     * @param xCoordinate of X
     */
    public void setX(int xCoordinate) {
        x = xCoordinate;
    }

    /** Method for setting Y coordinate
     *
     * @param yCoordinate of Y
     */
    public void setY(int yCoordinate) {
        y = yCoordinate;
    }

    /** Method for setting Red
     *
     * @param red color
     */
    public void setR(int red) {
        R = red;
    }

    /** Method for setting Green
     *
     * @param green color
     */
    public void setG(int green) {
        G = green;
    }

    /** Method for setting Blue
     *
     * @param blue color
     */
    public void setB(int blue) {
        B = blue;
    }

    /** Method for setting Color
     *
     * @param c color
     */
    public void setColor(Color c) {
        R = c.getRed();
        G = c.getGreen();
        B = c.getBlue();
    }

    /** Method for getting Color
     *
     * @return new color
     */
    public Color getColor() {
        return new Color(R, G, B);
    }

    /** Method for showing new color of pixel
     *
     * @return color
     */
    @Override
    public String toString() {
        return "Pixel{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + R + "," + G + "" + B +
                '}';
    }
}
