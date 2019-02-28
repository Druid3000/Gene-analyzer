package com.epam.gene_analyzer.services;

import com.epam.gene_analyzer.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Service class for creating graphics and tables.
 * Using array of lines, array of pixels for drawing graphics, array of areas, buffered pictures, values of old and new background intensity, table of areas.
 */
public class MainService {
    private ArrayList<Line> lineArray = new ArrayList<Line>();
    private ArrayList<Pixel> graphPixels = new ArrayList<Pixel>();
    private ArrayList<Area> areaArray = new ArrayList<Area>();
    private BufferedImage picture;
    private int numberOfLinesOld = 0;
    private double backgroundIntensityOld = 255.0;
    private double backgroundIntensity = 255.0;
    private double delta = 0.1;
    private RtModel rtModel = new RtModel();

    /**
     * Method for setting value of delta
     *
     * @param d value of possible distance difference between pixels
     */
    public void setDelta(double d) {
        delta = d;
    }

    /**
     * Method for adding object of class Line in lineArray
     *
     * @param p1 value of pixel
     * @param p2 value of pixel
     */
    public void addLine(Pixel p1, Pixel p2) {
        Line l = new Line(p1, p2, picture);
        lineArray.add(l);

    }

    /**
     * Method for get id of line
     *
     * @param id of line
     * @return array of line id
     */
    public Line getLine(int id) {
        return lineArray.get(id);
    }

    /**
     * Method for buffering picture from computer
     *
     * @return buffered picture
     */
    public BufferedImage getPicture() {
        return picture;
    }

    /**
     * Method for setting file of picture
     * Clear all data with previous picture
     *
     * @param f file of picture
     */
    public void setPicture(File f) {
        try {
            picture = ImageIO.read(f);
            areaArray.clear();
            lineArray.clear();
            graphPixels.clear();
            backgroundIntensity = 255.5;
            backgroundIntensityOld = 255.0;
            updateData();
        } catch (IOException ex) {
            //
        }
    }

    /**
     * Method for removing  picture
     * Clear all data with previous picture
     */
    public void remPicture() {
        areaArray.clear();
        lineArray.clear();
        graphPixels.clear();
        backgroundIntensity = 255.5;
        backgroundIntensityOld = 255.0;
        updateData();
    }

    /**
     * Method for getting list of line by using array of pixels
     *
     * @return array of pixels
     */
    public ArrayList<Line> getLines() {
        if (lineArray != null) return lineArray;
        else return null;
    }

    /**
     * Method for adding area.
     * Create new area.
     * Set new area on picture with using delta.
     * Add new area to array of areas.
     * Update data about areas.
     *
     * @param p chosen pixel on the picture
     */
    public void addArea(Pixel p) {
        Area area = new Area();
        area.setArea(p, picture, delta);
        areaArray.add(area);
        updateData();
    }

    /**
     * Method for getting area by area id
     *
     * @param id number of area
     * @return array of areas
     */
    public Area getArea(int id) {
        return areaArray.get(id);
    }

    /**
     * Method for getting list of area
     *
     * @return array of areas
     */
    public ArrayList<Area> getAreas() {
        return areaArray;
    }

    /**
     * Method for getting list of area perimeters
     *
     * @param id number of area
     * @return array of area perimeters
     */
    public ArrayList<Pixel> getAreaPerimeter(int id) {
        if (areaArray.size() > 0)
            return areaArray.get(id).getAreaPerimeter();
        else return null;
    }

    /**
     * Method for searching max value of density D
     *
     * @return max value of density
     */
    public double getMaxDensity() {
        double maxDensity = 0;
        for (int a = 0; a < getLines().size(); a++) {
            if (getLines().get(a).getMaxDensity(backgroundIntensity) > maxDensity) {
                maxDensity = getLines().get(a).getMaxDensity(backgroundIntensity);
            }
        }
        if (maxDensity >= 2) return 2;
        else return (double) ((int) maxDensity + 1);
    }

    /**
     * Method for setting pixel intensity for background intensity by will of user
     *
     * @param p value of pixel
     */
    public void setBackgroundIntensity(Pixel p) {
        backgroundIntensityOld = backgroundIntensity;
        backgroundIntensity = p.getIntensity();
        updateData();
    }

    /**
     * Method for setting background intensity
     *
     * @param intensity new intensity
     */
    public void setBackgroundIntensity(double intensity) {
        backgroundIntensityOld = backgroundIntensity;
        backgroundIntensity = intensity;
        updateData();
    }

    /**
     * Method for getting graphics.
     * Using sizes of graphic canvas.
     * Check change of numbers of lines for memory saving.
     *
     * @param graphHeight height of graphic
     * @param height      height
     * @param xBorder     border for x
     * @param yBorder     border for y
     * @return graphic
     */
    public ArrayList<Pixel> getGraphics(int graphHeight, int height, int xBorder, int yBorder) {
        if ((graphPixels == null) || (numberOfLinesOld != lineArray.size()) || (backgroundIntensity != backgroundIntensityOld))
            graphPixels = getNewGraphics(graphHeight, height, xBorder, yBorder);
        backgroundIntensityOld = backgroundIntensity;
        return graphPixels;
    }


    /**
     * Method for getting table
     *
     * @return model of table
     */
    public RtModel getTable() {
        return rtModel;
    }

    /**
     * Method for removing areas.
     * Delete areas from array of areas.
     */
    public void removeAreas() {
        areaArray.clear();
        updateData();
    }

    /**
     * Method for removing areas by using their id
     *
     * @param id of area
     */
    public void removeArea(int id) {
        for (int i = 0; i < areaArray.size(); i++)
            if (areaArray.get(i).getId() == id) {
                areaArray.remove(i);
                break;

            }
        updateData();
    }


    /**
     * Method for updating data in table
     */
    public void updateData() {
        rtModel.removeRows();
        Double[] newData;
        for (int n = 0; n < areaArray.size(); n++) {
            newData = new Double[areaArray.get(n).getArea().size()];
            for (int i = 0; i < areaArray.get(n).getArea().size(); i++) {
                newData[i] = areaArray.get(n).getArea().get(i).getIntensity();
            }
            rtModel.setValueAt(areaArray.get(n).getId(), newData, backgroundIntensity);
        }
        rtModel.fireTableDataChanged();
    }

    /**
     * Method for transfer index of deleted row of table
     *
     * @param idRow id of row
     */
    public void transferDeleteData(int idRow) {
    }

    /**
     * Method for drawing new graphics.
     * Using sizes of graphic canvas.
     * Getting values of intensity.
     * Ration of graphic.
     * If the ordinate makes a big difference - just draw a line from point to point.
     *
     * @param graphHeight height of graphic
     * @param height      height
     * @param xBorder     border for x
     * @param yBorder     border for y
     * @return drawn graphics
     */
    private ArrayList<Pixel> getNewGraphics(int graphHeight, int height, int xBorder, int yBorder) {
        numberOfLinesOld = lineArray.size();
        graphPixels.clear();
        for (int t = 0; t < lineArray.size(); t++) {
            Color c = getLines().get(t).getColor();
            double pixelIntensity;
            int x = xBorder, y, xPast, yPast;
            pixelIntensity = getLines().get(t).getPixelIntensity(0);
            if (pixelIntensity > 2.55)
                y = height - yBorder - (int) ((graphHeight * Math.log10(getBackgroundIntensity() / pixelIntensity)) / (getMaxDensity()));
            else y = yBorder;
            if (y > height - yBorder) y = height - yBorder;
            yPast = y;
            Pixel p = new Pixel();
            p.setX(x);
            p.setY(y);
            p.setColor(c);
            graphPixels.add(p);
            for (int i = 1; i < getLines().get(t).getLength(); i++) {
                if (Math.abs(y - yPast) > 1) {
                    for (int j = 1; j < Math.abs(y - yPast); j++) {
                        if (yPast < y) {
                            p = new Pixel();
                            p.setColor(c);
                            p.setX(i - 1 + xBorder);
                            if (pixelIntensity > 2.55)
                                p.setY(yPast + j);
                            else p.setY(yBorder);
                            graphPixels.add(p);
                        } else {
                            p = new Pixel();
                            p.setX(i - 1 + xBorder);
                            p.setColor(c);
                            if (pixelIntensity > 2.55)
                                p.setY(yPast - j);
                            else p.setY(yBorder);
                            graphPixels.add(p);
                        }
                    }
                }
                yPast = y;
                pixelIntensity = getLines().get(t).getPixelIntensity(i);
                x = i + xBorder;
                if (pixelIntensity > 1)
                    y = height - yBorder - (int) ((graphHeight * Math.log10(getBackgroundIntensity() / pixelIntensity)) / (getMaxDensity()));
                else y = yBorder;

                if (y > height - yBorder) y = height - yBorder;
                p = new Pixel();
                p.setX(x);
                p.setY(y);
                p.setColor(c);
                graphPixels.add(p);
            }
        }

        return graphPixels;
    }

    /**
     * Method for setting pixel intensity for background intensity by will of user
     *
     * @return new background intensity
     */
    private double getBackgroundIntensity() {
        return backgroundIntensity;
    }

}
