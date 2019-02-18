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
public  class CanvasLine extends JComponent{
    private File file;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();
    private static int height=1000, weight=1000;
    private boolean mode=true;  //true - линии, false - области
    //private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();
    public void setMode(boolean m){
        mode = m;
    }
    public void setPicture(File f){
        file=f;
    }
    public void setLines(ArrayList<Line> l){
        lines=l;
    }
    public void setArea(ArrayList<Pixel> pa){
        pixelArray=pa;
    }
    public void drawPicture(Graphics2D g){//отрисовка изображения
        if(file!=null) {
            try {
                //file = f;
                BufferedImage image = ImageIO.read(file);
                //Graphics2D g = (Graphics2D) this.getGraphics();
                g.drawImage(image, 0, 0, this);
            } catch (IOException ex) {
                // handle exception...
            }
        }
    }
    public void drawLines(Graphics2D g) {
        if(file!=null) {
            //Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.RED);//цвет для графика
            int x1, x2, y1, y2, length;//координаты начала и конца отрезка
            int x_, y_;              //чисто для проверки вхождения пикселей в изображение
            //введение координат отрезка с мышки
            if (lines.size() > 0) {
                for (int i = 0; i < lines.size(); i++) {
                    //System.out.println(lines.get(i).getArray().size());
                    g.setColor(Color.RED);//цвет для графика
                    x1 = lines.get(i).getArray().get(0).get_x();
                    x2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).get_x();
                    y1 = lines.get(i).getArray().get(0).get_y();
                    y2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).get_y();
                    g.drawLine(x1, y1, x2, y2);
                    g.setFont(new Font("Verdana", Font.PLAIN, 18));
                    g.setColor(new Color(144, 9, 9));
                    g.drawString(Integer.toString(i + 1), x1, y1);//подпись номера линии

                    System.out.println(x1 + ":" + y1 + "--" + x2 + ":" + y2);

                }
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

            //g2d.setColor(Color.black);
            /*if(file==null)
            {
                //("C:\\practice\\src\\photo1.jpg");
                file=new File("image.png");
                BufferedImage png = new BufferedImage(400,367,1);//ImageIO.read(file);
                Color color = new Color(0, 0, 0);
                png.setRGB(2, 2, color.getRGB());
                ImageIO.write(png, "png", file);
                file.createNewFile();
                /*
                FileOutputStream f = new FileOutputStream("\\image.png");
                f.write(12);
                f.close();


            }*/
            //else file= MainWindow.picture;
            //BufferedImage image= ImageIO.read(this.file);
            //отрисовка изображения
            //g2d.drawImage(image, 0, 0, this);
            drawPicture(g2d);
            if(mode)drawLines(g2d);
            else drawArea();


            super.repaint();
        //} //catch (IOException ex) {
            // handle exception...
        //}
    }
}