import java.awt.*;
import java.util.Random;

public class Line {
    public static String[] cordsCode=new String[0];
    private Color[] colors=new Color[0];
    public long id=-1;
    private Pixel[] pixels;
    private double maxDensity;
    private Color color;
    public Line(Pixel[] p){
        pixels=p;
        Random randCol=new Random();

        //проверка на существование этого же цвета
        //Color col=Color.getHSBColor(0,0,0);
        boolean eq=false;
         do{
            color = new Color(randCol.nextInt(200), randCol.nextInt(200), randCol.nextInt(200));
             System.out.println(color.getRGB());
            for (int j = 0; j < CanvasGraph.lines.length; j++) {//проверка такого же цвета
                eq = (color.getRGB()== CanvasGraph.lines[j].getColor().getRGB());
                System.out.println(color.getRGB());
                System.out.println(CanvasGraph.lines[j].getColor().getRGB());
                if(eq) break;
            }
            System.out.println(eq);
        }while (eq);

        //



        //проверка на существование подобной линии
        //я хитрый, буду сравнивать не сами линии, а строку координат пикселей
        String Code="";
        for (int i=0;i<p.length;i++){   //генерация кода
            Code+=Integer.toString(p[i].get_x())+":"+Integer.toString(p[i].get_y());
        }
        eq=false;
        for (int j = 0; j< Line.cordsCode.length; j++){//проверка такого же кода
            eq=(Code.toString().equals(cordsCode[j].toString()));
        }
        //System.out.println(eq);
        if(!eq) {//если линия уникальная по координатам, то отрисовка с запоминанием кода

            String[] newCodes =new String[cordsCode.length+1];
            for(int a=0;a<cordsCode.length;a++){
                newCodes[a]=cordsCode[a];
            }
            newCodes[cordsCode.length]=Code;
            cordsCode=newCodes;
            //
            CanvasGraph.addLine(this);
        }
        //проверю эту строку
        //System.out.println(Code);
        //System.out.println(cordsCode[cordsCode.length-1]);
        //System.out.println(Code.toString().equals(cordsCode[cordsCode.length-1].toString()));

        //this.id=Line.count;
        //Line.count+=1;
    }
    public double getMaxDensity(int intensity){
        for(int a=0;a<pixels.length;a++){
            if (pixels[a].get_intensity()<=1){
                maxDensity=2.9;
                break;
            }
            else if (Math.log10((double)intensity/pixels[a].get_intensity())>maxDensity) maxDensity=Math.log10((double)intensity/pixels[a].get_intensity());
        }
        return maxDensity;
    }
    public int getLengthX(){
        return pixels[pixels.length-1].get_x()-pixels[0].get_x();
    }
    public int getLength(){
        return pixels.length;
    }
    public Pixel[] getArray(){
        return pixels;
    }
    public double getPxlIntensity(int id){
        return pixels[id].get_intensity();
    }
    public Color getColor(){
        return color;
    }
}
