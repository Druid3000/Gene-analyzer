package Window;

import Controllers.GraphController;
import Controllers.MainController;
import Model.Line;

import javax.swing.*;
import java.util.ArrayList;

public class GraphWindow extends JFrame {
    //private GraphController graphController=new GraphController();
    private MainController mainController;
    private CanvasGraph canvasGraph;
    public GraphWindow(MainController mc){
        setTitle("Графики оптической плотности");
        //graphController.setLines(l);
        mainController=mc;
        canvasGraph = new CanvasGraph(mainController);
        add(canvasGraph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700,550);
    }
}
