package Controllers;

import Model.Area;
import Model.Line;
import Model.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    private ArrayList<Line> lineArray= new ArrayList<Line>();
    private Area area= new Area();
    private BufferedImage picture;
    public MainController(){
    }
    public void addLine(Pixel p1, Pixel p2){
        Line l = new Line(p1, p2, picture);
        lineArray.add(l);

    }
    public Line getLine(int id){
        return lineArray.get(id);

    }
    public BufferedImage getPicture(){
        return picture;
    }
    public void setPicture(File f){
        try{
            picture= ImageIO.read(f);
        }
        catch (IOException ex) {
            // handle exception...
        }
    }

    public ArrayList<Line> getLines(){
        return lineArray;
    }

    public void setArea(Pixel p){
        area.setArea(p,picture);
    }
    public ArrayList<Pixel> getArea(){
        //area.setArea(p,picture);
        return area.getArea();
    }
    public ArrayList<Pixel> getAreaPerimetr(){
        //area.setArea(p,picture);
        return area.getAreaPerimetr();
    }
}
