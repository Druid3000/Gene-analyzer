package Window;

import Controllers.MainController;

import javax.swing.*;

public class GraphWindow extends JFrame {
    private MainController mainController;
    private CanvasGraph canvasGraph;

    public GraphWindow(MainController mc) {
        setTitle("Графики оптической плотности");
        mainController = mc;
        canvasGraph = new CanvasGraph(mainController);
        add(canvasGraph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 550);
    }
}
