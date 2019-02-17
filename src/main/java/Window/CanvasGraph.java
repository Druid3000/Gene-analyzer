package Window;

import Model.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
class CanvasGraph extends JComponent{
    private static int height=500, weight=600,intensity=256,xBorder=50, yBorder=50,graphHeight=400,graphWeight=400;
    private static double maxDensity=1;
    private static ArrayList<Line> lines=new ArrayList<Line>();
    private static int maxLengthX=0;

    public static void addLine(Line line){
        lines.add(line);
    }
    public void drawCoordinateAxes(Graphics2D g, Color c){//отрисовка осей координат
        g.setColor(c);
        g.drawLine(xBorder, graphHeight+yBorder, xBorder, yBorder);
        g.drawLine(xBorder, graphHeight+yBorder, graphWeight+xBorder, graphHeight+yBorder);
    }

    public void drawCoordinateLabels( Color c){//отрисовка подписей координат
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(c);
        int n=20;   //n делений по оси ординат
        //int l=(int)maxDensity+1;    //максимальное значение оси ординат
        //maxDensity=l;

        g.drawString(Double.toString(0), xBorder/2 , (height - yBorder));
        for (int i=1;i<n+1;i++) {
            g.drawString(Double.toString((double)maxDensity*i/n), xBorder/2 , (height - yBorder )-i*graphHeight/n);//высота графика 800px
        }

        n=10;       //n делений по оси абсцисс
        maxLengthX();        //поиск максимального значение оси абсцисс (без нормирования всегда graphWeight
        g.drawString(Double.toString(0), xBorder , (height - yBorder/2));
        for (int i=1;i<n+1;i++) {
            g.drawString(Integer.toString((int)graphWeight*i/n), xBorder+i*graphWeight/n , (height - yBorder/2));//ширина графика 800px
        }
    }
    public void drawGraph(ArrayList<Line> lines){//отрисовка графика линий
        for (int t=0;t<lines.size();t++) {
            this.getGraphics().setColor(lines.get(t).getColor());
            double pxlIntensity;
            int x = xBorder, y, x_past, y_past;
            pxlIntensity = lines.get(t).getPxlIntensity(0);
            if (pxlIntensity > 2) y = height-yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity));//нормирование
            else y = yBorder;
            //y=h+266-(int)pxlIntensity;
            y_past = y;
            this.getGraphics().drawLine(x, y, x, y);
            for (int i = 1; i < lines.get(t).getLength(); i++) {
                if (Math.abs(y - y_past) > 1) { //если по ординате большая разница - просто отрисовка прямой от точки к точке
                    for (int p = 1; p < Math.abs(y - y_past); p++) {
                        if (y_past < y)
                            this.getGraphics().drawLine(i - 1 + xBorder, y_past + p, i - 1 + 50, y_past + p);
                        else
                            this.getGraphics().drawLine(i - 1 + xBorder, y_past - p, i - 1 + 50, y_past - p);
                    }
                }
                y_past = y;
                pxlIntensity = lines.get(t).getPxlIntensity(i);
                x = i + xBorder;
                if (pxlIntensity > 2) y = height - yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity));
                else y =  yBorder;
                this.getGraphics().drawLine(x, y, x, y);
            }
            //отрисовка легенды графика
            if(t>0) {
                super.setFont(new Font("Verdana", Font.PLAIN, 14));
                this.getGraphics().drawLine(xBorder/2 + weight, yBorder + t * 15, xBorder/2 + weight + 20, yBorder + t * 15);
                this.getGraphics().drawLine(xBorder/2 + weight, yBorder +1+ t * 15, xBorder/2 + weight + 20, yBorder +1+ t * 15);
                this.getGraphics().setColor(Color.black);
                this.getGraphics().drawString(Integer.toString(t), xBorder/2 + weight + 25, yBorder + t * 15 + 5);
                super.setFont(new Font("Verdana", Font.PLAIN, 11));
            }
        }
    }
    public void maxDensity() {//поиск максимальной плотности D
        maxDensity=1;
        for(int a=0;a<lines.size();a++){
            if ((int)lines.get(a).getMaxDensity(intensity)+1>maxDensity){
                maxDensity+=Math.abs((int)lines.get(a).getMaxDensity(intensity)+1-maxDensity);
            }
        }
    }
    public int maxLengthX() {//поиск максимальной длины по абсциссе
        int l=0;
        for(int a=0;a<lines.size();a++){
            if (lines.get(a).getLengthX()>l)
                l=lines.get(a).getLengthX();
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
            maxDensity();

            //отрисовка осей графика
            drawCoordinateAxes(g2d, Color.black);

            //цвет для графика
            //g2d.setColor(Color.RED);

            //поиск максимальной плотности D

            //подписи оси ординат (плотности)
            //drawCoordinateLabels(g2d,Color.BLACK);
            //отрисовка графика интенсивности
            //drawGraph();

            super.repaint();  /* 	Вызывает обновление себя после завершения рисования	*/
        } catch (Exception ex) {
            // handle exception...
        }
    }
}