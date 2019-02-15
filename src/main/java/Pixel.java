public class Pixel {
    private int x;
    private int y;
    private int R,G,B;
    public Pixel(int x, int y, int Red, int Green, int Blue){
        this.x=x;
        this.y=y;
        this.R=Red;
        this.G=Green;
        this.B=Blue;
    }
    public double get_intensity(){
        return ((double)R*0.3 + (double)G*0.59 + (double)B*0.11);
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public int get_R(){
        return R;
    }
    public int get_G(){
        return G;
    }
    public int get_B(){
        return B;
    }
}
