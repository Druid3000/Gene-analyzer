package Window;

import Controllers.GraphController;
import Controllers.MainController;
import Model.Line;

import javax.swing.*;
import java.util.ArrayList;

public class GraphWindow extends JFrame {
    private GraphController graphController=new GraphController();
    private CanvasGraph canvasGraph = new CanvasGraph();
    private MainController mainController;
    public GraphWindow(MainController mc){
        //graphController.setLines(l);
        mainController=mc;
        super.add(canvasGraph);
        graphController.drawCoordinateAxes();
        graphController.drawCoordinateLabels();
        drawGraphs(mainController.getLines());
        this.setTitle("Графики оптической плотности");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700,550);
    }
    void drawGraphs(ArrayList<Line> lines){
        //Вместо draw я просто выведу в консоль координаты.
        //System.out.println(line.getPoint1() + " " + line.getPoint2());
        canvasGraph.drawGraph(lines);
    }
}
