package Window;

import Controllers.MainController;
import Model.Line;
import Model.Pixel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
public  class CanvasLine extends JComponent{
    private File file;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();
    private MainController mainController;
    //private static int height=1000, weight=1000;
    private boolean mode=true;  //true - линии, false - области
    //private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();
    public CanvasLine(MainController mc){
        addListener();
        mainController=mc;
    }
    public void setMode(boolean m){
        mode = m;
    }
    public boolean getMode(){
        return mode;
    }
    public void setPicture(File f){
        file=f;

            try {
                //file = f;
                BufferedImage image = ImageIO.read(file);
                Graphics2D g = (Graphics2D) this.getGraphics();
                this.setSize( new Dimension(image.getWidth(),image.getHeight()));
            } catch (IOException ex) {
                // handle exception...
            }
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
            //System.out.println(pixelArray.size());
            for (int i = 0; i < pixelArray.size(); i++) {
                g.drawLine(pixelArray.get(i).get_x(), pixelArray.get(i).get_y(), pixelArray.get(i).get_x(), pixelArray.get(i).get_y());
                System.out.println(i+"  "+pixelArray.get(i).get_x()+":"+ pixelArray.get(i).get_y());
            }
        }
    }
    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
        //try {
            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;
            drawPicture(g2d);
            if(mode)drawLines(g2d);
            else drawArea();


            super.repaint();
        //} //catch (IOException ex) {
            // handle exception...
        //}
    }


    private void addListener(){
        addMouseListener(new MouseLocation(){

            private boolean pos=true;//true = 1 точка. false = 2 точка
            private int xPosition1=0;
            private int yPosition1=0;
            private int xPosition2=1;
            private int yPosition2=1;
            private int xPositionNow;
            private int yPositionNow;
            public int getxPosition1(){
                return xPosition1;
            }
            public int getxPosition2(){
                return xPosition2;
            }
            public int getyPosition1(){
                return yPosition1;
            }
            public boolean getPos(){return pos;}
            public int getyPosition2(){
                return yPosition2;
            }
            public int getxPositionNow(){
                return xPositionNow;
            }
            public int getyPositionNow(){
                return yPositionNow;
            }
            public void setPos(boolean p){
                pos=p;
            }
            public void mouseClicked (MouseEvent Event)
            {
                //System.out.println("mouseClicked");

                //onMouseClick();
            }
            public void mouseEntered (MouseEvent Event)
            {
                //System.out.println("mouseEntered");
            }
            public void mouseExited (MouseEvent Event)
            {
                //System.out.println("mouseExited");
            }
            public void mousePressed (MouseEvent Event)
            {
                //System.out.println("mousePressed");
                xPositionNow=Event.getX();
                yPositionNow=Event.getY();
                if (pos) {
                    xPosition1 = Event.getX();
                    yPosition1 = Event.getY();
                } else {
                    xPosition2 = Event.getX();
                    yPosition2 = Event.getY();

                    /////////метод из мэйнвиндоу//////////
                    //оставил работу с геттерами а не с полями напрямую для того, чтоб потом вынести
                    // слушатель в контроллер при желании
                    if(getxPositionNow()<getWidth()&getyPositionNow()<getHeight()) {
                        if (mode) {
                            if ( file != null) {
                                int x1 = getxPosition1();
                                int y1 = getyPosition1();
                                Pixel p1 = new Pixel();
                                p1.set_x(x1);
                                p1.set_y(y1);
                                int x2 = getxPosition2() ;
                                int y2 = getyPosition2() ;
                                Pixel p2 = new Pixel();
                                p2.set_x(x2);
                                p2.set_y(y2);
                                System.out.println(p1 + "" + p2);
                                if (!(x1 == x2 & y1 == y2)) {
                                    mainController.addLine(p1, p2);
                                    //System.out.println(mainController.getLines());
                                    setLines(mainController.getLines());
                                    //canvasLine.drawLines();
                                    //System.out.println(mouseLocation.getPos());
                                }
                            }
                        } else {
                            int x = getxPositionNow(), y = getyPositionNow();
                            Pixel p = new Pixel();
                            p.set_x(x);
                            p.set_y(y);
                            setArea(mainController.getArea(p));
                        }
                    }
                    System.out.println(getPos());
                }
                pos = !pos;


                System.out.println("");
            }
            public void mouseReleased (MouseEvent Event)
            {
                //xPosition2 = Event.getX() ;
                //yPosition2 = Event.getY() ;
                //pos=true;
            }
        });
    }
}