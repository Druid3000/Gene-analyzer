package com.epam.gene_analyzer.controllers;

import com.epam.gene_analyzer.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private ArrayList<Line> lineArray = new ArrayList<Line>();//массив всех линий
    private ArrayList<Pixel> graphPixels = new ArrayList<Pixel>();//массив пикселей для отрисовки графиков
    private ArrayList<Area> areaArray = new ArrayList<Area>();//массив всех областей
    private BufferedImage picture;
    private int numberOfLinesOld = 0;
    private double backgroundIntensityOld = 255.0;
    private double backgroundIntensity = 255.0;
    private double delta = 0.1;
    private RtModel rtModel = new RtModel();//таблица областей

    public void setDelta(double d) {
        delta = d;
    }

    public void addLine(Pixel p1, Pixel p2) {
        Line l = new Line(p1, p2, picture);
        lineArray.add(l);

    }

    public Line getLine(int id) {
        return lineArray.get(id);
    }

    public BufferedImage getPicture() {
        return picture;
    }

    public void setPicture(File f) {
        try {
            picture = ImageIO.read(f);
        } catch (IOException ex) {
            //
        }
    }

    public ArrayList<Line> getLines() {
        if (lineArray != null) return lineArray;
        else return null;
    }

    public void addArea(Pixel p) {
        Area area = new Area();
        area.setArea(p, picture, delta);
        areaArray.add(area);
        updateData();
    }

    public Area getArea(int id) {
        return areaArray.get(id);
    }

    public ArrayList<Area> getAreas() {
        return areaArray;
    }

    public ArrayList<Pixel> getAreaPerimeter(int id) {
        if (areaArray.size() > 0)
            return areaArray.get(id).getAreaPerimeter();
        else return null;
    }

    public int getMaxDensity() {//поиск максимальной плотности D
        int maxDensity = 1;
        for (int a = 0; a < getLines().size(); a++) {
            if (getLines().get(a).getMaxDensity(backgroundIntensity) > (double) maxDensity) {
                maxDensity += Math.abs((int) getLines().get(a).getMaxDensity(backgroundIntensity) + 1 - maxDensity);
            }
        }
        return maxDensity;
    }

    public void setBackgroundIntensity(Pixel p) {   //для установки по желанию интенсивности пикселя за интенсивность фона
        backgroundIntensityOld = backgroundIntensity;
        backgroundIntensity = p.getIntensity();
        updateData();
    }

    public void setBackgroundIntensity(double intensity) {
        backgroundIntensityOld = backgroundIntensity;
        backgroundIntensity = intensity;
        updateData();
    }

    public ArrayList<Pixel> getGraphics(int graphHeight, int height, int xBorder, int yBorder) {//на вход  идут размеры холста графика
        //для экономии памяти проверяю, изменилось ли количество линий
        if ((graphPixels == null) || (numberOfLinesOld != lineArray.size()) || (backgroundIntensity != backgroundIntensityOld))
            graphPixels = getNewGraphics(graphHeight, height, xBorder, yBorder);
        backgroundIntensityOld = backgroundIntensity;
        return graphPixels;
    }

    public RtModel getTable() {
        return rtModel;
    }

    public void removeAreas() {
        areaArray.clear();
        updateData();
    }

    public void removeArea(int id) {
        for (int i = 0; i < areaArray.size(); i++)
            if (areaArray.get(i).getId() == id) {
                areaArray.remove(i);
                break;

            }
        updateData();
    }

    //
    // методы из контроллера таблиц (переделано)
    //
    //метод для обновления данных в таблице

    public void updateData() {
        //rtModel=new RtModel();
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
    //метод для передачи индекса удаляемой ячейки

    public void transferDeleteData(int idRow) {
        //rtModel.deleteValueAt(idRow);
    }

    private ArrayList<Pixel> getNewGraphics(int graphHeight, int height, int xBorder, int yBorder) {//на вход  идут размеры холста графика
        numberOfLinesOld = lineArray.size();
        graphPixels.clear();
        for (int t = 0; t < lineArray.size(); t++) {
            Color c = getLines().get(t).getColor();
            double pixelIntensity;
            int x = xBorder, y, xPast, yPast;
            pixelIntensity = getLines().get(t).getPixelIntensity(0);
            if (pixelIntensity > 1)
                y = height - yBorder - (int) ((graphHeight * Math.log10(getBackgroundIntensity() / pixelIntensity)) / (getMaxDensity()));//нормирование
            else y = yBorder;
            if (y > height - yBorder) y = height - yBorder;
            yPast = y;
            Pixel p = new Pixel();
            p.setX(x);
            p.setY(y);
            p.setColor(c);
            graphPixels.add(p);
            for (int i = 1; i < getLines().get(t).getLength(); i++) {
                if (Math.abs(y - yPast) > 1) { //если по ординате большая разница - просто отрисовка прямой от точки к точке
                    for (int j = 1; j < Math.abs(y - yPast); j++) {
                        if (yPast < y) {
                            //g.drawLine(i - 1 + xBorder, yPast + j, i - 1 + 50, yPast + j);
                            p = new Pixel();
                            p.setX(i - 1 + xBorder);
                            p.setY(yPast + j);
                            p.setColor(c);
                            graphPixels.add(p);
                        } else {
                            p = new Pixel();
                            p.setX(i - 1 + xBorder);
                            p.setY(yPast - j);
                            p.setColor(c);
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

    private double getBackgroundIntensity() {   //для установки по желанию интенсивности пикселя за интенсивность фона
        return backgroundIntensity;
    }

}
