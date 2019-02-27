package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CanvasLine canvasLine;
    private Menu menu;

    public MainWindow() {
        MainController mainController = new MainController();
        GraphWindow graphWindow = new GraphWindow(mainController);
        AboutWindow aboutWindow = new AboutWindow();
        TableWindow tableWindow = new TableWindow(mainController);

        canvasLine = new CanvasLine(mainController);
        menu = new Menu(aboutWindow, tableWindow, mainController, graphWindow, canvasLine);
    }

    public void showWindow(){
        setTitle("Optical density determination");
        setIconImage(getToolkit().getImage("src/main/resources/iconMain.gif"));
        setLocation(60, 70);
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.getMenuBar());
        add(canvasLine);
        pack();
        setVisible(true);
    }
}
