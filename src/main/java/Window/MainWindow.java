package Window;

import Controllers.MainController;


import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private MainController mainController = new MainController();
    private GraphWindow graphWindow = new GraphWindow(mainController);
    private AboutWindow aboutWindow = new AboutWindow();
    private TableWindow tableWindow = new TableWindow(mainController);
    private CanvasLine canvasLine = new CanvasLine(mainController);
    private Menu menu = new Menu(aboutWindow, tableWindow, mainController, graphWindow, canvasLine);


    public MainWindow() {
        this.setVisible(true);
        this.setTitle("Определение оптической плотности");
        this.setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.get_menuBar());
        add(canvasLine);
        pack();
    }

}
