package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;
import com.epam.gene_analyzer.model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Class for getting mouse locations
 *
 */
public class MouseLocation implements MouseListener, MouseMotionListener {
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

    /** Getting X1
     *
     * @return X1
     */
    protected int getXPosition1() {
        return xPosition1;
    }

    /** Getting Y1
     *
     * @return Y1
     */
    public int getYPosition1() {
        return yPosition1;
    }

    /** Getting X2
     *
     * @return X2
     */
    private int getXPosition2() {
        return xPosition2;
    }

    /** Getting Y2
     *
     * @return Y2
     */
    private int getYPosition2() {
        return yPosition2;
    }

    /** Getting X now
     *
     * @return X now
     */
    public int getXPositionNow() {
        return xPositionNow;
    }

    /** Getting Y now
     *
     * @return Y now
     */
    public int getYPositionNow() {
        return yPositionNow;
    }

    /** Method for mouse click
     *
     * @param event click
     */
    public void mouseClicked(MouseEvent event) {
    }

    /** Method for mouse enter
     *
     * @param event enter
     */
    public void mouseEntered(MouseEvent event) {
    }

    /** Method for mouse exit
     *
     * @param event exit
     */
    public void mouseExited(MouseEvent event) {
    }

    /** Method for mouse press.
     * Using drawing lines.
     *
     * @param event press
     */
    public void mousePressed(MouseEvent event) {
        if (mainController.getPicture() != null) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                if (canvasLine.isChooseBackgroundIntensity()) {
                    Pixel p = new Pixel();
                    p.setX(event.getX());
                    p.setY(event.getY());
                    p.setColor(new Color(mainController.getPicture().getRGB(event.getX(), event.getY())));
                    mainController.setBackgroundIntensity(p);
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

                                        if (mainController.getLines().size() == 10)
                                            JOptionPane.showMessageDialog(null, "Delete at least one line to add a new one!");
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
                        mainController.addArea(p);
                    }
                    canvasLine.changePosition();
                }
            }
        }
    }

    /** Method for mouse move
     *
     * @param event move
     */
    public void mouseMoved(MouseEvent event) {
        if (mainController.getPicture() != null) {
            xPositionNow = event.getPoint().x;
            yPositionNow = event.getPoint().y;
        }
    }

    /** Method for mouse release
     *
     * @param event release
     */
    public void mouseReleased(MouseEvent event) {
    }

    /** Method for mouse drag
     *
     * @param event drag
     */
    public void mouseDragged(MouseEvent event) {
    }
}