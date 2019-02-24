package com.epam.gene_nalyzer.Window;

import com.epam.gene_nalyzer.Controllers.MainController;
import com.epam.gene_nalyzer.Model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
class CanvasGraph extends JComponent {
    private final int height = 500, weight = 450, intensity = 256, xBorder = 50, yBorder = 50, graphHeight = 400, graphWeight = 400, labelX = 10, labelY = 10;//размеры окна
    private MainController mainController;

    public CanvasGraph(MainController mc) {
        mainController = mc;
        setVisible(true);
    }

    private void drawCoordinateAxes(Graphics2D g) {//отрисовка осей координат
        g.setColor(Color.white);
        Rectangle r = new Rectangle(xBorder, yBorder, graphWeight, graphHeight);
        g.fill(r);
        g.setColor(Color.BLACK);
        g.drawLine(xBorder, graphHeight + yBorder, xBorder, yBorder);
        g.drawLine(xBorder, graphHeight + yBorder, graphWeight + xBorder, graphHeight + yBorder);

        g.drawString(Double.toString(0), xBorder, (height - yBorder / 2));
        for (int i = 1; i < labelY + 1; i++) {
            g.setColor(Color.GRAY);
            g.drawLine(xBorder / 2 + 20, (height - yBorder) - i * graphHeight / labelY, graphWeight + 50, (height - yBorder) - i * graphHeight / labelY);
            g.drawLine(xBorder + i * graphWeight / labelY, (height - yBorder / 2) - 20, xBorder + i * graphWeight / labelY, yBorder);
        }
        for (int i = 1; i < labelX + 1; i++) {
            g.setColor(Color.GRAY);
            g.drawLine(xBorder / 2 + 20, (height - yBorder) - i * graphHeight / labelX, graphWeight + 50, (height - yBorder) - i * graphHeight / labelX);
            g.drawLine(xBorder + i * graphWeight / labelX, (height - yBorder / 2) - 20, xBorder + i * graphWeight / labelX, yBorder);
        }
    }

    private void drawCoordinateLabels(Graphics2D g) {//отрисовка подписей координат

        if (mainController.getLines() != null) {
            g.setColor(Color.BLACK);
            int n = labelY;   //n делений по оси ординат

            for (int i = 1; i < n + 1; i++) {
                g.drawString(Double.toString(mainController.getMaxDensity() * i / n), xBorder / 2, (height - yBorder) - i * graphHeight / n);
            }

            n = labelX;   //n делений по абсциссе
            for (int i = 1; i < n + 1; i++) {
                g.drawString(Integer.toString(graphWeight * i / n), xBorder + i * graphWeight / n, (height - yBorder / 2));//ширина графика 800px
            }
        }
    }

    private void drawGraph(Graphics2D g) {//отрисовка графика линий
        ArrayList<Pixel> pixels = mainController.getGraphics(graphHeight, height, xBorder, yBorder);
        for (int i = 0; i < pixels.size(); i++) {
            g.setColor(pixels.get(i).get_Color());
            int x = pixels.get(i).getX();
            int y = pixels.get(i).getY();
            g.drawLine(x, y, x, y);
        }
    }

    private void drawLegend(Graphics2D g) {//отрисовка легенды графика
        super.setFont(new Font("Verdana", Font.PLAIN, 14));
        for (int t = 0; t < mainController.getLines().size(); t++) {
            g.setColor(mainController.getLines().get(t).getColor());
            g.drawLine(xBorder / 2 + weight, yBorder + t * 15, xBorder / 2 + weight + 20, yBorder + t * 15);
            g.drawLine(xBorder / 2 + weight, yBorder + 1 + t * 15, xBorder / 2 + weight + 20, yBorder + 1 + t * 15);
            g.setColor(Color.black);
            g.drawString(Integer.toString(mainController.getLines().get(t).getId()), xBorder / 2 + weight + 25, yBorder + t * 15 + 5);
        }
        super.setFont(new Font("Verdana", Font.PLAIN, 11));

    }

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g) {

        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        drawCoordinateAxes(g2d);
        drawCoordinateLabels(g2d);
        drawGraph(g2d);
        drawLegend(g2d);
        super.repaint();  /* 	Вызывает обновление себя после завершения рисования	*/

    }

}