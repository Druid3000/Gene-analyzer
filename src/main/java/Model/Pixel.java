package Model;

import java.awt.*;

public class Pixel {
    private int x;
    private int y;
    private int R = 0, G = 0, B = 0;

    public double get_intensity() {
        return ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11);
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public int get_R() {
        return R;
    }

    public int get_G() {
        return G;
    }

    public int get_B() {
        return B;
    }

    public void set_x(int xx) {
        x = xx;
    }

    public void set_y(int yy) {
        y = yy;
    }

    public void set_R(int r) {
        R = r;
    }

    public void set_G(int g) {
        G = g;
    }

    public void set_B(int b) {
        B = b;
    }

    public void set_Color(Color c) {
        R = c.getRed();
        G = c.getGreen();
        B = c.getBlue();
    }

    public Color get_Color() {
        return new Color(R, G, B);
    }

    @Override
    public String toString() {
        return "PIxel{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + R + "," + G + "" + B +
                '}';
    }
}
