package Window;

import Model.Line;
import Model.Pixel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
public  class CanvasArea extends JComponent{
    private File file;
    private static int height=1000, weight=1000;
    private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();

    public void setPicture(File f){
        file=f;
    }
    public void setLines(ArrayList<Pixel> pa){
        pixelArray=pa;
    }
    public void drawPicture(){//отрисовка изображения
        if(file!=null) {
            try {

                BufferedImage image = ImageIO.read(file);
                Graphics2D g = (Graphics2D) this.getGraphics();
                g.drawImage(image, 0, 0, this);
            } catch (IOException ex) {
                // handle exception...
            }
        }
    }
    public void drawArea() {
        if(file!=null) {
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.RED);//цвет для области
            for (int i = 0; i < pixelArray.size(); i++) {
                g.drawLine(pixelArray.get(i).get_x(), pixelArray.get(i).get_y(), pixelArray.get(i).get_x(), pixelArray.get(i).get_y());
            }
        }
    }

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
        //try {
            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;

            drawPicture();
            drawArea();
            super.repaint();
        //} catch (IOException ex) {
            // handle exception...
        //}
    }
}