package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;

public class GraphWindow extends JFrame {

    public GraphWindow(MainController mc) {

        MainController mainController = mc;
        CanvasGraph canvasGraph = new CanvasGraph(mainController);
        setTitle("Графики оптической плотности");
        setIconImage(getToolkit().getImage("iconGraph.png"));
        add(canvasGraph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 550);

    }
}
