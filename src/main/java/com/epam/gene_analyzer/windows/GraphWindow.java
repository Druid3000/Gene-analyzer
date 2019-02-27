package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;

public class GraphWindow extends JFrame {

    GraphWindow(MainController mc) {

        MainController mainController = mc;
        CanvasGraph canvasGraph = new CanvasGraph(mainController);
        setTitle("Optical density graphs");
        add(canvasGraph);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setIconImage(getToolkit().getImage("src/main/resources/iconGraph.png"));
        setBounds(810, 220, 550, 550);
        setResizable(false);

    }
}
