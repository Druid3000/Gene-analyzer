package Controllers;

import Model.Area;
import Model.Line;
import Model.Pixel;

import java.io.File;
import java.util.ArrayList;

public class MainController {

    private ArrayList<Line> lineArray= new ArrayList<Line>();
    private Area area= new Area();
    private File picture;
    public MainController(){
    }
    public void addLine(Pixel p1, Pixel p2){
        Line l = new Line(p1, p2, picture);
        lineArray.add(l);

    }
    public Line getLine(int id){
        return lineArray.get(id);

    }
    public File getPicture(){
        return picture;

    }
    public void setPicture(File p){
        picture = p;
    }

    public ArrayList<Line> getLines(){
        return lineArray;
    }
    public ArrayList<Pixel> getArea(Pixel p){
        area.setArea(p,picture);
        return area.getArea();
    }
    public ArrayList<Pixel> getAreaPerimetr(Pixel p){
        area.setArea(p,picture);
        return area.getArea();
    }
}
