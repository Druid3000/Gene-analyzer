package com.epam.gene_nalyzer.Window;

import com.epam.gene_nalyzer.Controllers.MainController;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    public MainWindow() {
        MainController mainController = new MainController();
        GraphWindow graphWindow = new GraphWindow(mainController);
        AboutWindow aboutWindow = new AboutWindow();
        TableWindow tableWindow = new TableWindow(mainController);
        CanvasLine canvasLine = new CanvasLine(mainController);
        Menu menu = new Menu(aboutWindow, tableWindow, mainController, graphWindow, canvasLine);

        setVisible(true);
        setTitle("Определение оптической плотности");
        setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.getMenuBar());
        add(canvasLine);
        pack();
    }

}
