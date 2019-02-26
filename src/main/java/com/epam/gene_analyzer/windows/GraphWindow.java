package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;

class GraphWindow extends JFrame {

    GraphWindow(MainController mc) {

        MainController mainController = mc;
        CanvasGraph canvasGraph = new CanvasGraph(mainController);
        setTitle("Графики оптической плотности");
        setIconImage(getToolkit().getImage("iconGraph.png"));
        add(canvasGraph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(710, 200, 550, 650);
        setResizable(false);

    }
}
