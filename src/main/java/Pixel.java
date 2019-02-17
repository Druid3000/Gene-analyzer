public class Pixel {
    private int x;
    private int y;
    private int R = 0, G = 0, B = 0;

    public Pixel(int x, int y, int Red, int Green, int Blue) {
        this.x = x;
        this.y = y;
        this.R = Red;
        this.G = Green;
        this.B = Blue;
    }

    public double get_intensity() {
        return ((double) R * 0.3 + (double) G * 0.59 + (double) B * 0.11);
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public void set_x(int x) {
        x = x;
    }

    public void set_y(int y) {
        y = y;
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
}