package com.epam.gene_nalyzer.Window;

import com.epam.gene_nalyzer.Controllers.MainController;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private MainController mainController = new MainController();
    private GraphWindow graphWindow = new GraphWindow(mainController);
    private AboutWindow aboutWindow = new AboutWindow();
    private TableWindow tableWindow = new TableWindow(mainController);
    private CanvasLine canvasLine = new CanvasLine(mainController);


    public MainWindow() {
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
