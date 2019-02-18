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
    private CanvasLine canvasLine = new CanvasLine(mainController);
    //private CanvasArea canvasArea = new CanvasArea();
    //private File picture;
    //private MouseLocation mouseLocation;
    private int xBorder=0, yBorder=0;
    //private static boolean pos=true;//true = 1 точка. false = 2 точка
    //private MouseLocation coordinates = new MouseLocation();
    private Menu menu = new Menu(aboutWindow, mainController, graphWindow, canvasLine);


    public MainWindow(){
        this.setVisible(true);
        this.setTitle("Определение оптической плотности");
        this.setPreferredSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.get_menuBar());
        //yBorder=menu.get_menuBar().getHeight()+getInsets().top;
        //xBorder=getInsets().left;
        System.out.println(xBorder+":"+yBorder);

        add(canvasLine);
        pack();
        onMenuOpenGraphWindow();
    }

    private void onMenuOpenGraphWindow(){
        //graphWindow.drawLine(controller.getLine());
    }
    private void onMenuOpenAboutWindow(){
        //aboutWindow.drawLine(controller.getLine());
    }
}
