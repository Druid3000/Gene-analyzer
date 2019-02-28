package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.services.MainService;

import javax.swing.*;

/** Class based on JFrame for creating window with graphics
 *
 */
public class GraphWindow extends JFrame {

    GraphWindow(MainService mc) {

        MainService mainService = mc;
        CanvasGraph canvasGraph = new CanvasGraph(mainService);
        setTitle("Optical density graphs");
        add(canvasGraph);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setIconImage(getToolkit().getImage("src/main/resources/iconGraph.png"));
        setBounds(810, 220, 550, 550);
        setResizable(false);

    }
}
