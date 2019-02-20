
package Controllers;

import Model.Area;
import Model.Line;
import Model.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private ArrayList<Line> lineArray = new ArrayList<Line>();//массив всех линий
    private ArrayList<Pixel> graphPixels = new ArrayList<Pixel>();//массив пикселей для отрисовки графиков
    private Area area = new Area();
    private BufferedImage picture;
    private int numberOfLinesOld = 0;
    private double backgroundIntensity = 255.0;

    public MainController() {
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
        return lineArray;
    }

    public void setArea(Pixel p) {
        area.setArea(p, picture);
    }

    public ArrayList<Pixel> getArea() {
        return area.getArea();
    }

    public ArrayList<Pixel> getAreaPerimetr() {
        return area.getAreaPerimetr();
    }

    public double getMaxDensity() {//поиск максимальной плотности D
        double maxDensity = 1;
        for (int a = 0; a < getLines().size(); a++) {
            if ((int) getLines().get(a).getMaxDensity(backgroundIntensity) + 1 > maxDensity) {
                maxDensity += Math.abs((int) getLines().get(a).getMaxDensity(backgroundIntensity) + 1 - maxDensity);
            }
        }
        return maxDensity;
    }

    public void setBackgroundIntensity(Pixel p) {   //для установки по желанию интенсивности пикселя за интенсивность фона
        backgroundIntensity = p.get_intensity();
    }

    private double getBackgroundIntensity() {
        return backgroundIntensity;
    }

    public ArrayList<Pixel> getGraphics(int graphHeight, int height, int xBorder, int yBorder) {//на вход  идут размеры холста графика
        //для экономии памяти проверяю, изменилось ли количество линий
        if (graphPixels == null || numberOfLinesOld != lineArray.size())
            graphPixels = getNewGraphics(graphHeight, height, xBorder, yBorder);
        return graphPixels;
    }

    private ArrayList<Pixel> getNewGraphics(int graphHeight, int height, int xBorder, int yBorder) {//на вход  идут размеры холста графика
        numberOfLinesOld = lineArray.size();
        graphPixels.clear();
        for (int t = 0; t < lineArray.size(); t++) {
            Color c = getLines().get(t).getColor();
            double pxlIntensity;
            int x = xBorder, y, x_past, y_past;
            pxlIntensity = getLines().get(t).getPxlIntensity(0);
            if (pxlIntensity > 2)
                y = height - yBorder - (int) ((graphHeight * Math.log10(getBackgroundIntensity() / pxlIntensity)) / (getMaxDensity()));//нормирование
            else y = yBorder;
            y_past = y;
            Pixel p = new Pixel();
            p.set_x(x);
            p.set_y(y);
            p.set_Color(c);
            graphPixels.add(p);
            for (int i = 1; i < getLines().get(t).getLength(); i++) {
                if (Math.abs(y - y_past) > 1) { //если по ординате большая разница - просто отрисовка прямой от точки к точке
                    for (int j = 1; j < Math.abs(y - y_past); j++) {
                        if (y_past < y) {
                            //g.drawLine(i - 1 + xBorder, y_past + j, i - 1 + 50, y_past + j);
                            p = new Pixel();
                            p.set_x(i - 1 + xBorder);
                            p.set_y(y_past + j);
                            p.set_Color(c);
                            graphPixels.add(p);
                        } else {
                            p = new Pixel();
                            p.set_x(i - 1 + xBorder);
                            p.set_y(y_past - j);
                            p.set_Color(c);
                            graphPixels.add(p);
                        }
                    }
                }
                y_past = y;
                pxlIntensity = getLines().get(t).getPxlIntensity(i);
                x = i + xBorder;
                if (pxlIntensity > 2)
                    y = height - yBorder - (int) ((graphHeight * Math.log10(getBackgroundIntensity() / pxlIntensity)) / (getMaxDensity()));
                else y = yBorder;
                p = new Pixel();
                p.set_x(x);
                p.set_y(y);
                p.set_Color(c);
                graphPixels.add(p);
            }
        }

        return graphPixels;
    }
}
