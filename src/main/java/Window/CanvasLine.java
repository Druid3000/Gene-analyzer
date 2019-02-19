package Window;

import Controllers.MainController;
import Model.Line;
import Model.Pixel;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
public  class CanvasLine extends JComponent{
    private BufferedImage picture;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private JPopupMenu popupMenu;//=new JPopupMenu();
   // private ArrayList<Pixel> pixelArray = new ArrayList<Pixel>();
    private MainController mainController;
    private boolean mode=true;  //true - линии, false - области
    public CanvasLine(MainController mc){
        addListener();
        addJPopMenu();
        mainController=mc;
    }
    public void setMode(boolean m){
        mode = m;
    }
    public void setLines(ArrayList<Line> l){
        lines=l;
    }
    private void drawPicture(Graphics2D g){//отрисовка изображения
        picture=mainController.getPicture();
        if (picture!=null) {
            this.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
            g.drawImage(picture, 0, 0, this);
        }
    }
    private void drawLines(Graphics2D g) {
        if(picture!=null) {
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
                    g.drawString(Integer.toString(lines.get(i).getId()), x1, y1);//подпись номера линии

                    //System.out.println(x1 + ":" + y1 + "--" + x2 + ":" + y2);

                }
            }
        }

    }

    private void drawArea(Graphics2D g) {
        if(picture!=null&mainController.getArea()!=null) {
            //Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.RED);//цвет для области
            //System.out.println(pixelArray.size());
            for (int i = 0; i < mainController.getArea().size(); i++) {
                g.drawLine(mainController.getArea().get(i).get_x(), mainController.getArea().get(i).get_y(), mainController.getArea().get(i).get_x(), mainController.getArea().get(i).get_y());
                //System.out.println(i+"  "+mainController.getArea().get(i).get_x()+":"+ mainController.getArea().get(i).get_y());
            }
        }
    }
    private void drawAreaPerimetr(Graphics2D g) {
        if(picture!=null&mainController.getAreaPerimetr()!=null) {
            //Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.red);//цвет для области
            //System.out.println(pixelArray.size());
            for (int i = 0; i < mainController.getAreaPerimetr().size(); i++) {
                g.drawLine(mainController.getAreaPerimetr().get(i).get_x(), mainController.getAreaPerimetr().get(i).get_y(), mainController.getAreaPerimetr().get(i).get_x(), mainController.getAreaPerimetr().get(i).get_y());
                //System.out.println(i+"  "+mainController.getArea().get(i).get_x()+":"+ mainController.getArea().get(i).get_y());
            }
        }
    }
    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
            super.paintComponents(g);
            reloadJPopMenu();
            Graphics2D g2d=(Graphics2D)g;
            drawPicture(g2d);
            if(mode)drawLines(g2d);
            else {
                //drawArea(g2d);
                drawAreaPerimetr(g2d);//или drawArea(g2d) - закрасит область
            }
            super.repaint();
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
            public void mousePressed (MouseEvent Event) {
                if (Event.getButton() == MouseEvent.BUTTON1) {
                    //System.out.println("mousePressed");
                    xPositionNow = Event.getX();
                    yPositionNow = Event.getY();
                    if (pos) {
                        xPosition1 = Event.getX();
                        yPosition1 = Event.getY();
                    } else {
                        xPosition2 = Event.getX();
                        yPosition2 = Event.getY();

                        /////////метод из мэйнвиндоу//////////
                        //оставил работу с геттерами а не с полями напрямую для того, чтоб потом вынести
                        // слушатель в контроллер при желании
                        if (getxPositionNow() < getWidth() & getyPositionNow() < getHeight()) {
                            if (mode) {
                                if (picture != null) {
                                    int x1 = getxPosition1();
                                    int y1 = getyPosition1();
                                    Pixel p1 = new Pixel();
                                    p1.set_x(x1);
                                    p1.set_y(y1);
                                    int x2 = getxPosition2();
                                    int y2 = getyPosition2();
                                    Pixel p2 = new Pixel();
                                    p2.set_x(x2);
                                    p2.set_y(y2);
                                    //System.out.println(p1 + "" + p2);
                                    if (!(x1 == x2 & y1 == y2)) {
                                        if (mainController.getLines().size() == 10)
                                            JOptionPane.showMessageDialog(null, "Удалите хотя бы одну линию, чтобы добавить новую!");
                                        else
                                            mainController.addLine(p1, p2);
                                        setLines(mainController.getLines());
                                    }
                                }
                            }

                        }
                        //System.out.println(getPos());
                    }
                    if (!mode) {
                        int x = getxPositionNow(), y = getyPositionNow();
                        Pixel p = new Pixel();
                        p.set_x(x);
                        p.set_y(y);
                        p.set_R((new Color(picture.getRGB(x, y)).getRed()));
                        p.set_G((new Color(picture.getRGB(x, y)).getGreen()));
                        p.set_B((new Color(picture.getRGB(x, y)).getBlue()));
                        mainController.setArea(p);
                        //System.out.println(p.get_R()+" "+p.get_G()+" "+p.get_B());
                        //System.out.println(p.get_x()+":"+p.get_y());
                        //setArea(mainController.getAreaPerimetr(p));
                        //repaint();
                    }

                    pos = !pos;



            }//System.out.println("");
            }
            public void mouseReleased (MouseEvent Event)
            {
                //xPosition2 = Event.getX() ;
                //yPosition2 = Event.getY() ;
                //pos=true;
            }
        });
    }
    private void addJPopMenu(){
        popupMenu = new  JPopupMenu();
        JMenuItem deleteAll = new JMenuItem("Удалить все линии");
        popupMenu.add(deleteAll);
        setComponentPopupMenu(popupMenu);
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                reloadJPopMenu();
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        } );
    }
    private void reloadJPopMenu(){
        /*for (int i=0;i<popupMenu.getSubElements().length;i++)
            popupMenu.remove(i);
        popupMenu.updateUI();*/
        popupMenu=new JPopupMenu();
        for (int i = 0;i<lines.size();i++) {
            JMenuItem cutMenuItem = new JMenuItem("Удалить линию номер" + lines.get(i).getId());
            cutMenuItem.addActionListener(new JPopMenuListener(i) {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(popupMenu.getSubElements().length);
                    lines.remove(this.id);
                }
            });
            popupMenu.add(cutMenuItem);
        }
        popupMenu.addSeparator();

        JMenuItem deleteAll = new JMenuItem("Удалить все линии");
        deleteAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println(popupMenu.getSubElements().length);
                lines.clear();
            }
        });
        popupMenu.add(deleteAll);
        setComponentPopupMenu(popupMenu);

    }
}