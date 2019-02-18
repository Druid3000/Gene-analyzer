package Window;
import Controllers.MainController;
//import MouseLocation;
import Model.Line;
import Model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class MainWindow extends JFrame {

    private MainController mainController = new MainController();
    private GraphWindow graphWindow = new GraphWindow(mainController);
    private AboutWindow aboutWindow = new AboutWindow();
    private CanvasLine canvasLine = new CanvasLine();
    //private CanvasArea canvasArea = new CanvasArea();
    //private File picture;
    private MouseLocation mouseLocation;
    private int xBorder=0, yBorder=0;
    //private static boolean pos=true;//true = 1 точка. false = 2 точка
    //private MouseLocation coordinates = new MouseLocation();
    private Menu menu = new Menu(mainController, graphWindow, canvasLine,/* canvasArea, */mouseLocation);


    public MainWindow(){
        this.setVisible(true);
        this.setTitle("Определение оптической плотности");
        this.setPreferredSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.get_menuBar());
        //yBorder=menu.get_menuBar().getHeight()+getInsets().top;
        //xBorder=getInsets().left;
        System.out.println(xBorder+":"+yBorder);

        addListener();
        //canvasLine.addMouseListener(mouseLocation);
        /*this.addMouseListener(new MouseLocation(){
            @Override
            public void mousePressed (MouseEvent Event)
            {
                onMouseClick(Event);
            }
        });*/
        //setLayout(null);
        //canvasLine.setLocation(0, 0);
        //JPanel panel = new JPanel();
        //panel.setLayout(null);
        //panel.setLocation(xBorder,yBorder);
        //panel.add(canvasLine);
        //panel.setPreferredSize(new Dimension());
        //this.add(new JPanel());
        //panel.add(canvasLine);
        //setContentPane(panel);
        //canvasLine.setVisible(true);
        //canvasArea.setVisible(false);
        add(canvasLine);
        //add(canvasArea);
        pack();
        //canvasArea.setVisible(false);

        //System.out.println(menu.get_menuBar().getHeight());

        //System.out.println(canvasLine.getLocation().x+""+canvasLine.getLocation().y);

        //onMenuOpenPicture(); в меню есть слушатель на это дело
        //onMouseClick();
        onMenuOpenGraphWindow();
    }

    private void addListener(){
        addMouseListener(mouseLocation = new MouseLocation(){

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
                return yPosition1;
            }
            public int getyPositionNow(){
                return yPosition2;
            }
            public void setPos(boolean p){
                pos=p;
            }
            public void mouseClicked (MouseEvent Event)
            {
                //System.out.println("mouseClicked");

                onMouseClick();
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
                //if((Event.getX() < CanvasLine.weight)&(Event.getY()< CanvasLine.height)) {

                int str1=getClass().toString().length();    //больше 25 будем считать канвас
                int str2=MainWindow.class.toString().length();
                //if(str1>25) {
                    if (pos) {
                        xPosition1 = Event.getX();
                        yPosition1 = Event.getY();
                    } else {
                        xPosition2 = Event.getX();
                        yPosition2 = Event.getY();
                    }
                    pos = !pos;
                //}


                //System.out.println(str1+"="+str2+(str1==str2));
                //System.out.println(str1.);
                //System.out.println("x " + xPosition1);
                //System.out.println("y " + yPosition1);
                System.out.println("");
                //onMouseClick();
                //}
            }
            public void mouseReleased (MouseEvent Event)
            {
                //xPosition2 = Event.getX() ;
                //yPosition2 = Event.getY() ;
                //pos=true;
            }
        });
        canvasLine.addMouseListener(mouseLocation);
        //canvasArea.addMouseListener(mouseLocation);
    }

    private void onMouseClick(){

        System.out.println("onMouseClick");

        if ((mouseLocation.getPos())&(mainController.getPicture()!=null)){
            int x1=mouseLocation.getxPosition1()-xBorder;
            int y1=mouseLocation.getyPosition1()-yBorder;
            Pixel p1 = new Pixel();
            p1.set_x(x1);
            p1.set_y(y1);
            int x2=mouseLocation.getxPosition2()-xBorder;
            int y2=mouseLocation.getyPosition2()-yBorder;
                Pixel p2 = new Pixel();
                p2.set_x(x2);
                p2.set_y(y2);
                System.out.println(p1+""+p2);
                if(!(x1==x2&y1==y2)){
                mainController.addLine(p1, p2);
                //System.out.println(mainController.getLines());
                canvasLine.setLines(mainController.getLines());
                //canvasLine.drawLines();
                //System.out.println(mouseLocation.getPos());
            }
        }

        System.out.println(mouseLocation.getPos());
//        System.out.println(picture.getPath());
    }

    /*private void onMenuOpenPicture(){

        this.add(canvasLine);
        picture = mainController.getPicture();
        canvasLine.drawPicture(picture);
    }
    */
    private void onMenuOpenGraphWindow(){
        //graphWindow.drawLine(controller.getLine());
    }
    private void onMenuOpenAboutWindow(){
        //aboutWindow.drawLine(controller.getLine());
    }
}
/*
public class MainWindow extends JFrame {
    public static File picture;
    private MainController mainController = new MainController();
    public MainWindow(){
        super("Gene Analyzer");//Old: Определение оптической плотности
        super.setMinimumSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        new Menu(mainController);//создаем меню
        //накидываем кнопки в меню с помощью методов класса Menu

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
*/