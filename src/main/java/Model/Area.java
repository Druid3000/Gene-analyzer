package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Area {
    private ArrayList<Pixel> pixels;
    private ArrayList<Pixel> perimetr;
    private final double delta = 0.1;
    private static final AtomicInteger id = new AtomicInteger(0);
    private int idThis = 1;

    public void setArea(Pixel pixel, BufferedImage im) {
        pixels = new ArrayList<Pixel>();
        perimetr = new ArrayList<Pixel>();
        Pixel[][] all_pxls = arrayFromPicture(im);
        Pixel[][] all_pxls_unchanged = arrayFromPicture(im);
        int x = pixel.get_x(), y = pixel.get_y();
        double intensity = pixel.get_intensity();
        if (intensity == 0) intensity = 0.000001;
        pixels.add(pixel);
        all_pxls[x][y].set_R(1000000);
        checkNear(all_pxls,all_pxls_unchanged, x, y, intensity, 0);
        idThis = id.incrementAndGet();

    }

    public ArrayList<Pixel> getArea() {
        return pixels;
    }

    public ArrayList<Pixel> getAreaPerimetr() {
        return perimetr;
    }

    private void checkNear(Pixel[][] all_pxls,Pixel[][]all_pxls_unchanged, int x, int y, double intensity, int side) {//side - показывает, откуда пришла проверка
        //side = 0 - начало; 1 - слева; 2 - справа; 3 - сверху; 4 - снизу.
        double newIntensity;
        if ((x - 1 >= 0) & side != 1)//слева от пикселя
        {
            newIntensity = all_pxls[x - 1][y].get_intensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - intensity) / (intensity)) <= delta) {
                pixels.add(all_pxls_unchanged[x - 1][y]);
                all_pxls[x - 1][y].set_R(1000000);
                checkNear(all_pxls,all_pxls_unchanged, x - 1, y, intensity, 2);
            } else if (all_pxls[x - 1][y].get_intensity() < 256) {
                perimetr.add(all_pxls_unchanged[x - 1][y]);
            }
        }
        if (x + 1 < all_pxls.length & side != 2)//cправа от пикселя
        {
            newIntensity = all_pxls[x + 1][y].get_intensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls_unchanged[x + 1][y]);
                all_pxls[x + 1][y].set_R(1000000);
                checkNear(all_pxls,all_pxls_unchanged, x + 1, y, intensity, 1);
            } else if (all_pxls[x + 1][y].get_intensity() < 256) perimetr.add(all_pxls_unchanged[x + 1][y]);
        }
        if (y - 1 >= 0 & side != 3)//сверху от пикселя
        {
            newIntensity = all_pxls[x][y - 1].get_intensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls_unchanged[x][y - 1]);
                all_pxls[x][y - 1].set_R(1000000);
                checkNear(all_pxls,all_pxls_unchanged, x, y - 1, intensity, 4);
            } else if (all_pxls[x][y - 1].get_intensity() < 256) perimetr.add(all_pxls_unchanged[x][y - 1]);
        }
        if (y + 1 < all_pxls[0].length & side != 4)//снизу от пикселя
        {
            newIntensity = all_pxls[x][y + 1].get_intensity();
            if (newIntensity == 0) newIntensity = 0.000001;
            if ((Math.abs(newIntensity - (intensity)) / (intensity)) <= delta) {
                pixels.add(all_pxls_unchanged[x][y + 1]);
                all_pxls[x][y + 1].set_R(1000000);
                checkNear(all_pxls, all_pxls_unchanged, x, y + 1, intensity, 3);
            } else if (all_pxls[x][y + 1].get_intensity() < 256) perimetr.add(all_pxls_unchanged[x][y + 1]);
        }
    }

    private Pixel[][] arrayFromPicture(BufferedImage image) {
        Pixel[][] all_pxls;
        int h = image.getHeight(), w = image.getWidth(), R, G, B;
        all_pxls = new Pixel[w][h];
        Color c;
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

        return all_pxls;
    }

    public int getId() {
        return idThis;
    }
}