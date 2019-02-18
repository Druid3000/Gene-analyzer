package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Area {
    private ArrayList<Pixel> pixels = new ArrayList<Pixel>();
    private final double delta=0.2;
    public void setArea(Pixel pixel, File f){

        Pixel[][] all_pxls = arrayFromFile(f);
        int x=pixel.get_x(),y=pixel.get_y();
        double intensity=pixel.get_intensity();
        pixels.add(pixel);
        all_pxls[x][y].set_R(1000000);
        checkNear(all_pxls,x,y,intensity);

    }

    public ArrayList<Pixel> getArea() {
        return pixels;
    }

    public ArrayList<Pixel> getAreaPerimetr() {
        ArrayList<Pixel> perimetr=new ArrayList<Pixel>();
        Pixel[][] area = new Pixel[10000][10000];
        for(int i=0;i<pixels.size();i++){
            area[pixels.get(i).get_x()][pixels.get(i).get_y()]=pixels.get(i);
        }


        Pixel prav = pixels.get(0);
        for (int i=0;i<pixels.size();i++){//ищу самый правый пиксель
            if (prav.get_x()<pixels.get(i).get_x())
                prav=pixels.get(i);
        }
        perimetr.add(prav);

        Pixel lev = pixels.get(0);
        for (int i=0;i<pixels.size();i++){//ищу самый левый пиксель
            if (lev.get_x()<pixels.get(i).get_x())
                lev=pixels.get(i);
        }
        perimetr.add(lev);

        for (int i=lev.get_x()+1;i<prav.get_x();i++) {//от самого левого до самого правого ищу максималки по верхам
            int y=10000,id=-1;
            for (int j=0;j<pixels.size();j++){
                if (pixels.get(j).get_x()==i){
                    if (pixels.get(j).get_y()<y){
                        y=pixels.get(j).get_y();
                        id=j;
                    }
                }
            }
            perimetr.add(pixels.get(id));
        }

        for (int i=lev.get_x()+1;i<prav.get_x();i++) {//от самого левого до самого правого ищу максималки по низам
            int y=-1,id=-1;
            for (int j=0;j<pixels.size();j++){
                if (pixels.get(j).get_x()==i){
                    if (pixels.get(j).get_y()>y){
                        y=pixels.get(j).get_y();
                        id=j;
                    }
                }
            }
            perimetr.add(pixels.get(id));
        }
        /*{
            if (area[x+1][y+1]!=null){
                x=x+1;
                y=y+1;
                perimetr.add(area[x][y]);
            }else
            if (area[x][y+1]!=null){
                x=x;
                y=y+1;
                perimetr.add(area[x][y]);
            }else
            if (area[x][y+1]!=null){
                x=x+1;
                y=y=1;
                perimetr.add(area[x][y]);
            }

                p=pixels.get(i);
            pixels.
        }
        for (int i=0;i<pixels.size();i++){//налево
            if (p.get_x()<pixels.get(i).get_x())
                p=pixels.get(i);
        }
        for (int i=0;i<pixels.size();i++){//вверх
            if (p.get_x()<pixels.get(i).get_x())
                p=pixels.get(i);
        }
        for (int i=0;i<pixels.size();i++){//направо (возрастание x)
            if (p.get_x()<pixels.get(i).get_x())
                p=pixels.get(i);
        }
        */
        return perimetr;
    }

    private void checkNear(Pixel[][] all_pxls, int x, int y, double intensity){
        if(x-1>=0)
        if(Math.abs(all_pxls[x-1][y].get_intensity()-intensity)/intensity<=delta) {
            pixels.add(all_pxls[x-1][y]);
            all_pxls[x-1][y].set_R(1000000);
            checkNear(all_pxls, x - 1, y,intensity);
        }
        if(x+1<all_pxls.length)
            if(Math.abs(all_pxls[x+1][y].get_intensity()-intensity)/intensity<=delta) {
                pixels.add(all_pxls[x+1][y]);
                all_pxls[x+1][y].set_R(1000000);
                checkNear(all_pxls, x + 1, y,intensity);
            }
        if(y-1>=0)
            if(Math.abs(all_pxls[x][y-1].get_intensity()-intensity)/intensity<=delta) {
                pixels.add(all_pxls[x][y-1]);
                all_pxls[x][y-1].set_R(1000000);
                checkNear(all_pxls, x , y-1,intensity);
            }
        if(y+1<all_pxls[0].length)
            if(Math.abs(all_pxls[x][y+1].get_intensity()-intensity)/intensity<=delta) {
                pixels.add(all_pxls[x][y+1]);
                all_pxls[x][y+1].set_R(1000000);
                checkNear(all_pxls, x , y+1,intensity);
            }
    }
    private Pixel[][] arrayFromFile(File f){
        Pixel[][] all_pxls = new Pixel[0][0];
        try {
            BufferedImage image = ImageIO.read(f);
            int h = image.getHeight(), w = image.getWidth(), R, G, B;
            all_pxls = new Pixel[w][h];
            Color c = new Color(image.getRGB(100, 100));
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    c = new Color(image.getRGB(i, j));
                    R = c.getRed();
                    G = c.getGreen();
                    B = c.getBlue();
                    Pixel p = new Pixel();
                    p.set_x(i);
                    p.set_y(j);
                    p.set_R(R);
                    p.set_G(G);
                    p.set_B(B);
                    all_pxls[i][j] = p;
                }
            }

        }
        catch (Exception e){};
        return all_pxls;
    }
}