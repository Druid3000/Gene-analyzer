package Controllers;

import Model.Line;
import Model.Pixel;
import Model.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    //private Line line = new Line();
    private ArrayList<Line> lineArray;
    private File picture;
    public MainController(){
        lineArray= new ArrayList<Line>();
    }
    /*public void setLine(Point point1, Point point2){
        line.setPoint1(point1);
        line.setPoint2(point2);
    }*/
    public void addLine(Pixel p1, Pixel p2){
        Line l = new Line(p1, p2, picture);
        //l.setPoint1(p1);
        //l.setPoint2(p2);
        lineArray.add(l);

    }
    public Line getLine(int id){
        return lineArray.get(id);

    }
    public File getPicture(){
        //File picture = new File("");//тут кнопка должна работать
        return picture;

    }
    public void setPicture(File p){
        picture = p;
    }

    public ArrayList<Line> getLines(){
        return lineArray;
    }
}
