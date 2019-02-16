package example.controller;

import example.model.Line;
import example.model.Point;

public class Controller {

    private Line line = new Line();

    public void setLine(Point point1, Point point2){
        line.setPoint1(point1);
        line.setPoint2(point2);
    }

    public Line getLine(){
        return line;

    }
}
