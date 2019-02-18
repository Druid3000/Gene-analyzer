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
    //private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();

    public void drawPicture(File f){//отрисовка изображения
        try {
            file = f;
            BufferedImage image = ImageIO.read(f);
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.drawImage(image, 0, 0, this);
        }
        catch (IOException ex) {
                // handle exception...
        }
    }
    public void drawArea(ArrayList<Pixel> pixelArray) {
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(Color.RED);//цвет для области
        for (int i = 0; i < pixelArray.size(); i++) {
             g.drawLine(pixelArray.get(i).get_x(),pixelArray.get(i).get_y(),pixelArray.get(i).get_x(),pixelArray.get(i).get_y());
        }
    }

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
        try {
            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;

            g2d.setColor(Color.black);
            if(file==null)
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

                */
            }
            //else file= MainWindow.picture;
            BufferedImage image= ImageIO.read(this.file);
            //отрисовка изображения
            g2d.drawImage(image, 0, 0, this);

            //super.repaint();
        } catch (IOException ex) {
            // handle exception...
        }
    }
}