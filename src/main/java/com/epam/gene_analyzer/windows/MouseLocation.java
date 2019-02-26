package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;
import com.epam.gene_analyzer.model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseLocation implements MouseListener, MouseMotionListener {//private
    private int xPosition1;
    private int yPosition1;
    private int xPosition2;
    private int yPosition2;
    private int xPositionNow;
    private int yPositionNow;
    private CanvasLine canvasLine;
    private MainController mainController;

    MouseLocation(CanvasLine cl, MainController ml) {
        canvasLine = cl;
        mainController = ml;
        xPosition1 = 0;
        yPosition1 = 0;
        xPosition2 = 1;
        yPosition2 = 1;
    }

    protected int getXPosition1() {
        return xPosition1;
    }

    public int getYPosition1() {
        return yPosition1;
    }

    private int getXPosition2() {
        return xPosition2;
    }

    private int getYPosition2() {
        return yPosition2;
    }

    public int getXPositionNow() {
        return xPositionNow;
    }

    public int getYPositionNow() {
        return yPositionNow;
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
            if (canvasLine.isChooseBackgroundIntensity()) {
                Pixel p = new Pixel();
                p.setX(event.getX());
                p.setY(event.getY());
                p.setColor(new Color(mainController.getPicture().getRGB(event.getX(), event.getY())));
                mainController.setBackgroundIntensity(p);
                //mainController.reloadGraphics();//обновление графиков с новой интенсивностью, под вопросом
                canvasLine.setChooseBackgroundIntensity(false);
            } else {
                if (canvasLine.getPosition()) {
                    xPosition1 = event.getX();
                    yPosition1 = event.getY();
                } else {
                    xPosition2 = event.getX();
                    yPosition2 = event.getY();

                    if (getXPositionNow() < canvasLine.getWidth() & getYPositionNow() < canvasLine.getHeight()) {
                        if (canvasLine.getMode()) {
                            if (mainController.getPicture() != null) {
                                int x1 = getXPosition1();
                                int y1 = getYPosition1();
                                Pixel p1 = new Pixel();
                                p1.setX(x1);
                                p1.setY(y1);
                                int x2 = getXPosition2();
                                int y2 = getYPosition2();
                                Pixel p2 = new Pixel();
                                p2.setX(x2);
                                p2.setY(y2);
                                if (!(x1 == x2 & y1 == y2)) {
                                    //if (mainController.getLines() != null) {
                                    if (mainController.getLines().size() == 10)
                                        JOptionPane.showMessageDialog(null, "Удалите хотя бы одну линию, чтобы добавить новую!");
                                    else
                                        mainController.addLine(p1, p2);
                                    canvasLine.setLines(mainController.getLines());
                                }
                            }
                        }

                    }
                }
                if (!canvasLine.getMode()) {
                    int x = getXPositionNow(), y = getYPositionNow();
                    Pixel p = new Pixel();
                    p.setX(x);
                    p.setY(y);
                    p.setColor(new Color(mainController.getPicture().getRGB(x, y)));
                    mainController.addArea(p);//set -> add (в массив)
                }
                canvasLine.changePosition();
            }
        }
    }

    public void mouseMoved(MouseEvent event) {
        xPositionNow = event.getPoint().x;
        yPositionNow = event.getPoint().y;

    }

    public void mouseReleased(MouseEvent event) {
    }

    public void mouseDragged(MouseEvent event) {
    }
}