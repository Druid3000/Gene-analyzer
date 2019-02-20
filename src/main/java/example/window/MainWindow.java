package example.window;

import example.controller.Controller;
import example.model.Point;

public class MainWindow {

    private static Controller controller = new Controller();
    private static GraphWindow graphWindow = new GraphWindow();

    public static void main(String[] args) {
        onMouseClick();
        onMenuOpenGraphWindow();
    }

    private static void onMouseClick(){
        //Берем коодринаты мышки X и Y.
        //К примеру, они будут:
        //x1=100, y1=100
        //x2=200, y2=200
        Point p1 = new Point();
        p1.setX(100);
        p1.setY(100);

        Point p2 = new Point();
        p2.setX(200);
        p2.setY(200);

        controller.setLine(p1, p2);
    }

    private static void onMenuOpenGraphWindow(){
        graphWindow.drawLine(controller.getLine());
    }
}
