package Window;

import Controllers.MainController;


import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private MainController mainController = new MainController();
    private GraphWindow graphWindow = new GraphWindow(mainController);
    private AboutWindow aboutWindow = new AboutWindow();
    private CanvasLine canvasLine = new CanvasLine(mainController);
    private int xBorder = 0, yBorder = 0;
    private Menu menu = new Menu(aboutWindow, mainController, graphWindow, canvasLine);


    public MainWindow() {
        this.setVisible(true);
        this.setTitle("Определение оптической плотности");
        this.setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.get_menuBar());
        //yBorder=menu.get_menuBar().getHeight()+getInsets().top;
        //xBorder=getInsets().left;
        //System.out.println(xBorder+":"+yBorder);

        add(canvasLine);
        pack();
        onMenuOpenGraphWindow();
    }

    private void onMenuOpenGraphWindow() {
        //graphWindow.drawLine(controller.getLine());
    }

    private void onMenuOpenAboutWindow() {
        //aboutWindow.drawLine(controller.getLine());
    }
}
