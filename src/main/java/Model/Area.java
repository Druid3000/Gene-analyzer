package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Area {
    private ArrayList<Pixel> pixels;
    private ArrayList<Pixel> perimetr;
    private final double delta=0.1;
    public void setArea(Pixel pixel, BufferedImage im){
        pixels = new ArrayList<Pixel>();
        perimetr = new ArrayList<Pixel>();
        Pixel[][] all_pxls = arrayFromPicture(im);
        int x=pixel.get_x(),y=pixel.get_y();
        double intensity=pixel.get_intensity();
        if(intensity==0)intensity=0.000001;
        //System.out.println(intensity + " это интенсивность первого пикселя");
        pixels.add(pixel);
        all_pxls[x][y].set_R(1000000);
        checkNear(all_pxls,x,y,intensity);

    }

    public ArrayList<Pixel> getArea() {
        return pixels;
    }

    public ArrayList<Pixel> getAreaPerimetr() {
        return perimetr;
    }

    private void checkNear(Pixel[][] all_pxls, int x, int y, double intensity){

//        System.out.println("checkNear"+Integer.toString(pixels.size()));

        double newIntensity;
        if(x-1>=0)//слева от пикселя
        {
            newIntensity=all_pxls[x-1][y].get_intensity();
            if(newIntensity==0) newIntensity=0.000001;
            //System.out.println(pixels.size()+ "слева от пикселя " + (x-1)+y);
            if ((Math.abs(newIntensity - intensity) / (intensity)) <= delta) {
                pixels.add(all_pxls[x - 1][y]);
                all_pxls[x - 1][y].set_R(1000000);
                //System.out.println(pixels.size()+ "слева от пикселя " + (x-1)+y);
                checkNear(all_pxls, x - 1, y, intensity);
                //System.out.println(x+":"+y);
            }
            else
            if(all_pxls[x - 1][y].get_intensity()<256) perimetr.add(all_pxls[x - 1][y]);
        }
        if(x+1<all_pxls.length)//cправа от пикселя
        {
            newIntensity=all_pxls[x+1][y].get_intensity();
            if(newIntensity==0)newIntensity=0.000001;
            //System.out.println(pixels.size()+ "справа от пикселя " + (x+1)+y);
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls[x + 1][y]);
                all_pxls[x + 1][y].set_R(1000000);
                //System.out.println(pixels.size()+ "справа от пикселя " + (x+1)+y);
                checkNear(all_pxls, x + 1, y, intensity);
            }
            else
                if(all_pxls[x + 1][y].get_intensity()<256) perimetr.add(all_pxls[x + 1][y]);
        }
        if(y-1>=0)//сверху от пикселя
        {
            newIntensity=all_pxls[x][y - 1].get_intensity();
            if(newIntensity==0)newIntensity=0.000001;
            //System.out.println(pixels.size()+ "сверху от пикселя " + (x)+(y-1));
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls[x][y - 1]);
                all_pxls[x][y - 1].set_R(1000000);
                //System.out.println(pixels.size());
                checkNear(all_pxls, x, y - 1, intensity);
            }
            else
            if(all_pxls[x ][y-1].get_intensity()<256) perimetr.add(all_pxls[x ][y-1]);
        }
        if(y+1<all_pxls[0].length)//снизу от пикселя
        {
            newIntensity=all_pxls[x][y + 1].get_intensity();
            if(newIntensity==0)newIntensity=0.000001;
            //System.out.println(pixels.size()+ "снизу от пикселя " + (x)+(y+1));
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls[x][y + 1]);
                all_pxls[x][y + 1].set_R(1000000);
                //System.out.println(pixels.size()+ "снизу от пикселя " + (x)+(y+1));
                checkNear(all_pxls, x, y + 1, intensity);
            }
            else
            if(all_pxls[x ][y+1].get_intensity()<256) perimetr.add(all_pxls[x ][y+1]);
        }
    }
    private Pixel[][] arrayFromPicture(BufferedImage image){
        Pixel[][] all_pxls;
        //try {
            //BufferedImage image =im;
            int h = image.getHeight(), w = image.getWidth(), R, G, B;
            all_pxls = new Pixel[w][h];
            //System.out.println(w+" and "+h);
            Color c ;
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

        //}
        //catch (Exception e){};
        return all_pxls;
    }
}