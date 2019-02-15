import javax.swing.*;
import java.awt.*;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
class CanvasGraph extends JComponent{
    public static int height=500, weight=500,intensity=256,xBorder=50, yBorder=50,graphHeight=400,graphWeight=400;
    public static double maxDensity=0;
    public static Line[] lines=new Line[0];
    public static int maxLengthX=0;

    public static void addLine(Line line){
        Line[] newLines =new Line[lines.length+1];
        for(int a=0;a<lines.length;a++){
            newLines[a]=lines[a];
        }
        newLines[lines.length]=line;
        lines=newLines;
    }
    public void drawCoordinateAxes(Graphics2D g, Color c){//отрисовка осей координат
        g.setColor(c);
        g.drawLine(xBorder, height-yBorder, xBorder, yBorder);
        g.drawLine(xBorder, height-yBorder, weight-xBorder, height-yBorder);
    }

    public void drawCoordinateLabels(Graphics2D g, Color c){//отрисовка подписей координат
        g.setColor(c);
        int n=20;   //n делений по оси ординат
        int l=(int)maxDensity+1;    //максимальное значение оси ординат
        maxDensity=l;
        g.drawString(Double.toString(0), xBorder/2 , (height - yBorder));
        for (int i=1;i<n+1;i++) {
            g.drawString(Double.toString((double)l*i/n), xBorder/2 , (height - yBorder )-i*graphHeight/n);//высота графика 800px
        }

        n=10;       //n делений по оси абсцисс
        maxLengthX();        //поиск максимального значение оси абсцисс (без нормирования всегда graphWeight
        g.drawString(Double.toString(0), xBorder , (height - yBorder/2));
        for (int i=1;i<n+1;i++) {
            g.drawString(Double.toString((double)graphWeight*i/n), xBorder+i*graphWeight/n , (height - yBorder/2));//ширина графика 800px
        }
    }
    public void drawGraph(Graphics2D g){//отрисовка графика линий
        for (int t=0;t<lines.length;t++) {
            g.setColor(lines[t].getColor());
            double pxlIntensity;
            int x = xBorder, y, x_past, y_past;
            pxlIntensity = lines[t].getPxlIntensity(0);
            if (pxlIntensity > 2) y = height-yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity));//нормирование
            else y = yBorder;
            //y=h+266-(int)pxlIntensity;
            y_past = y;
            g.drawLine(x, y, x, y);
            for (int i = 1; i < lines[t].getLength(); i++) {
                if (Math.abs(y - y_past) > 1) { //если по ординате большая разница - просто отрисовка прямой от точки к точке
                    for (int p = 1; p < Math.abs(y - y_past); p++) {
                        if (y_past < y)
                            g.drawLine(i - 1 + xBorder, y_past + p, i - 1 + 50, y_past + p);
                        else
                            g.drawLine(i - 1 + xBorder, y_past - p, i - 1 + 50, y_past - p);
                    }
                }
                y_past = y;
                pxlIntensity = lines[t].getPxlIntensity(i);
                x = i + xBorder;
                if (pxlIntensity > 2) y = height - yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity));
                else y =  yBorder;
                g.drawLine(x, y, x, y);
            }
        }
    }
    public void maxDensity() {//поиск максимальной плотности D
        maxDensity=0;
        for(int a=0;a<lines.length;a++){
            if (lines[a].getMaxDensity(intensity)>maxDensity){
                maxDensity=1+(int)lines[a].getMaxDensity(intensity);
            }
        }
    }
    public int maxLengthX() {//поиск максимальной длины по абсциссе
        int l=0;
        for(int a=0;a<lines.length;a++){
            if (lines[a].getLengthX()>l)
                l=lines[a].getLengthX();
        }
        //int module=l%50;
        //this.maxLengthX=l+(50-module);
        this.maxLengthX=l;
        return l;
    }
    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
        try {
            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;

            //отрисовка осей графика
            drawCoordinateAxes(g2d, Color.black);

            //цвет для графика
            //g2d.setColor(Color.RED);

            //поиск максимальной плотности D
            maxDensity();

            //подписи оси ординат (плотности)
            drawCoordinateLabels(g2d,Color.BLACK);
            //отрисовка графика интенсивности
            drawGraph(g2d);

            super.repaint();  /* 	Вызывает обновление себя после завершения рисования	*/
        } catch (Exception ex) {
            // handle exception...
        }
    }
}