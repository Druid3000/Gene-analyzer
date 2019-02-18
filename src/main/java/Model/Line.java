package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Line {
    public static String[] cordsCode=new String[0];
    //private ArrayList<Color> colors=new ArrayList<Color>();
    private static int id=1;
    private int idThis;
    //private Pixel[] pixels;
    private double maxDensity;
    private Color color;
    private Point point1;
    private Point point2;
    private ArrayList<Pixel> pixels= new ArrayList<Pixel>();
    public Line(Pixel p1, Pixel p2, File f){
        int x1=p1.get_x(), x2=p2.get_x(),y1=p1.get_y(),y2=p2.get_y();
        double k, b;
        k=(double)(y1-y2)/(x1-x2);
        b=y1-k*x1;
        //length=(Math.sqrt (( Math.pow((x1-x2),2)+Math.pow((y1-y2),2) )));//тута вычисляем длину
        int length=Math.max(Math.abs(x1-x2),(Math.abs(y1-y2)));//длина отрезка (кол-во пикселей)
        //System.out.println("длина отрезка "+length);
        //создаю массив пикселей всего изображения

        BufferedImage image;
        try {
            image = ImageIO.read(f);
            int h = image.getHeight(), w = image.getWidth(), R, G, B;
            //int weight = w;
            //int height = h;
            Pixel[][] all_pxls = new Pixel[w][h];
            Color c = new Color(image.getRGB(100, 100));
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    c = new Color(image.getRGB(i, j));
                    R = c.getRed();
                    G = c.getGreen();
                    B = c.getBlue();
                    Pixel p = new Pixel();
                    p.set_x(i);
                    p.set_y(j);
                    p.set_R(R);
                    p.set_G(G);
                    p.set_B(B);
                    all_pxls[i][j] = p;
                }
            }


            // заполнение
            if (Math.abs(k) >= 1) {
                double x, y = y1;
                for (int i = 0; i < length; i++) {
                    x = (y - b) / k;
                    //g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                    pixels.add(all_pxls[(int)x][(int)y]); //pxls[i]=all_pxls[(int)x][(int)y];//заполняю массив пикселей на отрезке
                    //System.out.println(x+":"+y);
                    if (y1 < y2) y++;
                    else y--;
                }
            } else {
                double x = x1, y;
                for (int i = 0; i < length; i++) {
                    y = k * x + b;
                    //g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                    pixels.add(all_pxls[(int) x][(int) y]); //pxls[i] = all_pxls[(int) x][(int) y];//заполняю массив пикселей на отрезке
                    x++;
                }
            }
            //pixels = p;
            idThis = Line.id;
            Line.id += 1;

            switch (id) {
                case 1:
                    color = Color.BLACK;
                case 2:
                    color = Color.BLUE;
                case 3:
                    color = Color.CYAN;
                case 4:
                    color = Color.GREEN;
                case 5:
                    color = Color.MAGENTA;
                case 6:
                    color = Color.ORANGE;
                case 7:
                    color = Color.PINK;
                case 8:
                    color = Color.RED;
                case 9:
                    color = Color.WHITE;
                case 10:
                    color = Color.YELLOW;
            }
        }
        catch (Exception e){};
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public double getMaxDensity(int intensity){
        for(int a=0;a<pixels.size();a++){
            if (pixels.get(a).get_intensity()<=1){
                maxDensity=2.9;
                break;
            }
            else if (Math.log10((double)intensity/pixels.get(a).get_intensity())>maxDensity) maxDensity=Math.log10((double)intensity/pixels.get(a).get_intensity());
        }
        return maxDensity;
    }
    public int getLengthX(){
        return pixels.get(pixels.size()-1).get_x()-pixels.get(0).get_x();
    }
    public int getLength(){
        return pixels.size();
    }
    public ArrayList<Pixel> getArray(){
        return pixels;
    }
    public double getPxlIntensity(int id){
        return pixels.get(id).get_intensity();
    }
    public Color getColor(){
        return color;
    }
}
