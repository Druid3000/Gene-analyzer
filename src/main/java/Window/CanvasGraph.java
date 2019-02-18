package Window;

import Controllers.MainController;
import Model.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
class CanvasGraph extends JComponent{
    private final int height=500, weight=600,intensity=256,xBorder=50, yBorder=50,graphHeight=400,graphWeight=400;
    //private double maxDensity=1;
    //private ArrayList<Line> lines=new ArrayList<Line>();
    private MainController mainController;
    private int maxLengthX;
    public CanvasGraph(MainController mc){
        mainController=mc;
        setVisible(true);
    }

    public void drawCoordinateAxes(Graphics2D g){//отрисовка осей координат
        g.setColor(Color.BLACK);
        g.drawLine(xBorder, graphHeight+yBorder, xBorder, yBorder);
        g.drawLine(xBorder, graphHeight+yBorder, graphWeight+xBorder, graphHeight+yBorder);
    }

    public void drawCoordinateLabels( Graphics2D g){//отрисовка подписей координат

        if(mainController.getLines()!=null) {
            g.setColor(Color.BLACK);
            int n = 20;   //n делений по оси ординат
            //int l=(int)maxDensity+1;    //максимальное значение оси ординат
            //maxDensity=l;

            g.drawString(Double.toString(0), xBorder / 2, (height - yBorder));
            for (int i = 1; i < n + 1; i++) {
                g.drawString(Double.toString((double) maxDensity() * i / n), xBorder / 2, (height - yBorder) - i * graphHeight / n);//высота графика 800px
            }

            n = 10;       //n делений по оси абсцисс
            maxLengthX();        //поиск максимального значение оси абсцисс (без нормирования всегда graphWeight
            g.drawString(Double.toString(0), xBorder, (height - yBorder / 2));
            for (int i = 1; i < n + 1; i++) {
                g.drawString(Integer.toString((int) graphWeight * i / n), xBorder + i * graphWeight / n, (height - yBorder / 2));//ширина графика 800px
            }
        }
    }
    public void drawGraph(Graphics2D g){//отрисовка графика линий
        if(mainController.getLines()!=null) {
            for (int t = 0; t < mainController.getLines().size(); t++) {
                g.setColor(mainController.getLines().get(t).getColor());
                double pxlIntensity;
                int x = xBorder, y, x_past, y_past;
                pxlIntensity = mainController.getLines().get(t).getPxlIntensity(0);
                if (pxlIntensity > 2)
                    y = height - yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity()));//нормирование
                else y = yBorder;
                //y=h+266-(int)pxlIntensity;
                y_past = y;
                g.drawLine(x, y, x, y);
                for (int i = 1; i < mainController.getLines().get(t).getLength(); i++) {
                    if (Math.abs(y - y_past) > 1) { //если по ординате большая разница - просто отрисовка прямой от точки к точке
                        for (int p = 1; p < Math.abs(y - y_past); p++) {
                            if (y_past < y)
                                g.drawLine(i - 1 + xBorder, y_past + p, i - 1 + 50, y_past + p);
                            else
                                g.drawLine(i - 1 + xBorder, y_past - p, i - 1 + 50, y_past - p);
                        }
                    }
                    y_past = y;
                    pxlIntensity = mainController.getLines().get(t).getPxlIntensity(i);
                    x = i + xBorder;
                    if (pxlIntensity > 2)
                        y = height - yBorder - (int) ((graphHeight * Math.log10(intensity / pxlIntensity)) / (maxDensity()));
                    else y = yBorder;
                    g.drawLine(x, y, x, y);
                }
                //отрисовка легенды графика

                    super.setFont(new Font("Verdana", Font.PLAIN, 14));
                    g.drawLine(xBorder / 2 + weight, yBorder + t * 15, xBorder / 2 + weight + 20, yBorder + t * 15);
                    g.drawLine(xBorder / 2 + weight, yBorder + 1 + t * 15, xBorder / 2 + weight + 20, yBorder + 1 + t * 15);
                    g.setColor(Color.black);
                    g.drawString(Integer.toString(mainController.getLines().get(t).getId()), xBorder / 2 + weight + 25, yBorder + t * 15 + 5);
                    super.setFont(new Font("Verdana", Font.PLAIN, 11));

            }
        }
    }
    public double maxDensity() {//поиск максимальной плотности D
        double maxDensity=1;
        for(int a=0;a<mainController.getLines().size();a++){
            if ((int)mainController.getLines().get(a).getMaxDensity(intensity)+1>maxDensity){
                maxDensity+=Math.abs((int)mainController.getLines().get(a).getMaxDensity(intensity)+1-maxDensity);
            }
        }
        return maxDensity;
    }
    public int maxLengthX() {//поиск максимальной длины по абсциссе
        int l=0;
        for(int a=0;a<mainController.getLines().size();a++){
            if (mainController.getLines().get(a).getLengthX()>l)
                l=mainController.getLines().get(a).getLengthX();
        }
        //int module=l%50;
        //this.maxLengthX=l+(50-module);
        this.maxLengthX=l;
        return l;
    }
    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){

            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;
            //maxDensity();
            //g2d.drawString("qq)))",10,10);
            //System.out.println(mainController==null);
            drawCoordinateAxes(g2d);
            drawCoordinateLabels(g2d);
            drawGraph(g2d);
            super.repaint();  /* 	Вызывает обновление себя после завершения рисования	*/

    }

}