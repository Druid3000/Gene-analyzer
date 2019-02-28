package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;
import com.epam.gene_analyzer.model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/** Class for building Graphic with coordinate axes.
 * Drawing a segment and filling an array of pixels.
 *
 */
class CanvasGraph extends JComponent {
    private final int height = 500, weight = 450, intensity = 256, xBorder = 50, yBorder = 50, graphHeight = 400, graphWeight = 400, labelX = 10, labelY = 10;//размеры окна
    private MainController mainController;

    /** Method for calling mainController
     *
     * @param mc mainController
     */
    public CanvasGraph(MainController mc) {
        mainController = mc;
        setVisible(true);
    }


    /** Method that redraws the element inside the window when updating.
     *Cause updating itself after drawing is complete
     *
     * @param graphics graphics
     */
    public void paintComponent(Graphics graphics) {

        super.paintComponents(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        drawCoordinateAxes(graphics2D);
        drawCoordinateLabels(graphics2D);
        drawGraph(graphics2D);
        drawLegend(graphics2D);
        super.repaint();

    }

    /** Method for drawing axes.
     * Draw background and grid.
     *
     * @param graphics2D graphic2D
     */
    private void drawCoordinateAxes(Graphics2D graphics2D) {
        graphics2D.setColor(Color.white);
        Rectangle r = new Rectangle(xBorder, yBorder, graphWeight, graphHeight);
        graphics2D.fill(r);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(xBorder, graphHeight + yBorder, xBorder, yBorder);
        graphics2D.drawLine(xBorder, graphHeight + yBorder, graphWeight + xBorder, graphHeight + yBorder);

        graphics2D.drawString(Double.toString(0), xBorder, (height - yBorder / 2));
        for (int i = 1; i < labelY + 1; i++) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(xBorder / 2 + 20, (height - yBorder) - i * graphHeight / labelY, graphWeight + 50, (height - yBorder) - i * graphHeight / labelY);
            graphics2D.drawLine(xBorder + i * graphWeight / labelY, (height - yBorder / 2) - 20, xBorder + i * graphWeight / labelY, yBorder);
        }
        for (int i = 1; i < labelX + 1; i++) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(xBorder / 2 + 20, (height - yBorder) - i * graphHeight / labelX, graphWeight + 50, (height - yBorder) - i * graphHeight / labelX);
            graphics2D.drawLine(xBorder + i * graphWeight / labelX, (height - yBorder / 2) - 20, xBorder + i * graphWeight / labelX, yBorder);
        }
    }

    /** Method for drawing labels fo coordinates.
     * Set n tick marks along the axises.
     *
     * @param graphics2D graphic2D
     */
    private void drawCoordinateLabels(Graphics2D graphics2D) {

        if (mainController.getLines() != null) {
            graphics2D.setColor(Color.BLACK);
            int n = labelY;

            for (int i = 1; i < n + 1; i++) {
                graphics2D.drawString(Double.toString(mainController.getMaxDensity() * i / n), xBorder / 2, (height - yBorder) - i * graphHeight / n);
            }

            n = labelX;
            for (int i = 1; i < n + 1; i++) {
                graphics2D.drawString(Integer.toString(graphWeight * i / n), xBorder + i * graphWeight / n, (height - yBorder / 2));//ширина графика 800px
            }
        }
    }

    /** Method for drawing graphic of lines
     *
     * @param graphics2D graphic2D
     */
    private void drawGraph(Graphics2D graphics2D) {
        ArrayList<Pixel> pixels = mainController.getGraphics(graphHeight, height, xBorder, yBorder);
        for (int i = 0; i < pixels.size(); i++) {
            graphics2D.setColor(pixels.get(i).getColor());
            int x = pixels.get(i).getX();
            int y = pixels.get(i).getY();
            graphics2D.drawLine(x, y, x, y);
        }
    }

    /** Method for drawing graphic legend
     *
     * @param graphics2D graphic2D
     */
    private void drawLegend(Graphics2D graphics2D) {
        super.setFont(new Font("Verdana", Font.PLAIN, 14));
        for (int t = 0; t < mainController.getLines().size(); t++) {
            graphics2D.setColor(mainController.getLines().get(t).getColor());
            graphics2D.drawLine(xBorder / 2 + weight, yBorder + t * 15, xBorder / 2 + weight + 20, yBorder + t * 15);
            graphics2D.drawLine(xBorder / 2 + weight, yBorder + 1 + t * 15, xBorder / 2 + weight + 20, yBorder + 1 + t * 15);
            graphics2D.setColor(Color.black);
            graphics2D.drawString(Integer.toString(mainController.getLines().get(t).getId()), xBorder / 2 + weight + 25, yBorder + t * 15 + 5);
        }
        super.setFont(new Font("Verdana", Font.PLAIN, 11));

    }


}