package com.epam.gene_analyzer.window;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;

public class GraphWindow extends JFrame {

    public GraphWindow(MainController mc) {

        MainController mainController = mc;
        CanvasGraph canvasGraph = new CanvasGraph(mainController);
        setTitle("Графики оптической плотности");
        add(canvasGraph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 550);

    }
}