package com.epam.gene_analyzer.window;

import java.awt.event.*;

public class MouseLocation implements MouseListener, MouseMotionListener {
    private boolean position = true;//true = 1 точка. false = 2 точка
    private int xPosition1 = 0;
    private int yPosition1 = 0;
    private int xPosition2 = 1;
    private int yPosition2 = 1;
    private int xPositionNow;
    private int yPositionNow;

    public int getXPosition1() {
        return xPosition1;
    }

    public int getXPosition2() {
        return xPosition2;
    }

    public int getYPosition1() {
        return yPosition1;
    }

    public int getYPosition2() {
        return yPosition2;
    }

    public int getXPositionNow() {
        return xPositionNow;
    }

    public int getYPositionNow() {
        return yPositionNow;
    }

    public void mousePressed(MouseEvent event) {
        if (position) {
            xPosition1 = event.getX();
            yPosition1 = event.getY();
        } else {
            xPosition2 = event.getX();
            yPosition2 = event.getY();
        }
        position = !position;
    }

    public void mouseMoved(MouseEvent event) {
        xPositionNow = event.getPoint().x;
        yPositionNow = event.getPoint().y;
    }

    public void mouseDragged(MouseEvent event) {
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }
}