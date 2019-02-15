import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* Класс с отрисовкой отрезка и заполнением массива пикселей */
class CanvasLine extends JComponent{
    private File file;
    public static int height=1000, weight=1000;

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics g){
        try {
            super.paintComponents(g);
            Graphics2D g2d=(Graphics2D)g;

            g2d.setColor(Color.black);
            if(MainWindow.picture==null)
            {
                //("C:\\practice\\src\\photo1.jpg");
                file=new File("image.png");
                BufferedImage png = new BufferedImage(400,367,1);//ImageIO.read(file);
                Color color = new Color(0, 0, 0);
                png.setRGB(2, 2, color.getRGB());
                ImageIO.write(png, "png", file);
                file.createNewFile();
                /*
                FileOutputStream f = new FileOutputStream("\\image.png");
                f.write(12);
                f.close();

                */
            }
            else file=MainWindow.picture;
            BufferedImage image= ImageIO.read(this.file);
            //отрисовка изображения
            g2d.drawImage(image, 0, 0, this);


            //заполнение по пикселям
            int h=image.getHeight(), w=image.getWidth(),R,G,B;
            weight=w;
            height=h;
            Pixel[][] all_pxls = new Pixel[w][h];
            Color c = new Color(image.getRGB(100, 100));
            for (int i = 0; i<w;i++) {
                for (int j = 0; j < h; j++) {
                    c = new Color(image.getRGB(i, j));
                    R=c.getRed();
                    G=c.getGreen();
                    B=c.getBlue();
                    all_pxls[i][j] = new Pixel(i, j, R, G, B);
                }
            }

            //System.out.println(image.getHeight());
            //System.out.println(image.getWidth());
            //System.out.println(c.getRed());
            g2d.drawLine(399,366,399,366);
            /*
            int rand=0;   //для рандомных переливаний картинки
            for (int i = 1; i<this.getHeight();i++) {
                for (int j = 1; j < this.getWidth(); j++) {
                    //System.out.println((int)(255*Math.sin(i)));
                    do {
                        R = (int)((i/2)%255);
                    } while (R < 0 || R > 255);
                    do {
                        G =  R;
                    } while (G < 0 || G > 255);
                    do {
                        B =  R;
                    } while (B < 0 || B > 255);

                    all_pxls[i][j] = new Pixel(i, j, R, G, B);

                    //Отрисовка нового пикселя
                    g2d.setColor(new Color(R, G, B));
                    g2d.drawLine(i, j, i, j);
                }
            }

            ///////////////// сюда конец коммента*/
            //g2d.drawString( Integer.toString(all_pxls[100][100].get_B()),50, this.getHeight()-100);
            g2d.setColor(Color.RED);//цвет для графика
            int x1, x2,y1,y2, length;//координаты начала и конца отрезка
            int x_,y_;              //чисто для проверки вхождения пикселей в изображение
            //введение координат отрезка с мышки
            //if(ButtonListener.dot){

            x_=MouseLocation.xPosition1;
            y_=MouseLocation.yPosition1;

            //TO DO сделать проверку координат мышки
            /*if (!((x_>-1&x_<w)&(y_>-1&y_<h))) {
                MouseLocation.pos=!MouseLocation.pos;
                //x_=MouseLocation.xPosition1;
                //y_=MouseLocation.yPosition1;
            }*/
            x1=x_;
            y1=y_;
            //else MouseLocation.pos=!MouseLocation.pos;

            //y1=MouseLocation.yPosition1;

            if(x1>w) x1=w-1;
            if(y1>h) y1=h-1;
            if(x1<0) x1=0;
            if(y1<0) y1=0;
            //}
            //else {
                if (!MouseLocation.pos){
                    /*Point location = MouseInfo.getPointerInfo().getLocation();
                    x2 = (int)location.getX();
                    y2 = (int)location.getY();*/
                    x2=MouseLocation.xPositionNow;
                    y2=MouseLocation.yPositionNow;
                    //System.out.println(x2+" : "+y2);
                    /*if(x1==x2) x2=x1+1;
                    if(y1==y2) y2=y1+1;

                    if(x2>=w) x2=w-1;
                    if(y2>=h) y2=h-1;

                    if(x2<0) x2=0;
                    if(y2<0) y2=0;*/
                }
                else {
                    x2=MouseLocation.xPosition2;
                    y2=MouseLocation.yPosition2;
                }
            if(x2>=w) x2=w-1;
            if(y2>=h) y2=h-1;

            if(x1==x2) x2=x1-1;
            if(y1==y2) y2=y1-1;

            if(x2<0) x2=1;
            if(y2<0) y2=1;

                //System.out.println(x1+" : "+y1);
                //System.out.println(x2+" : "+y2);
                //x2=MouseLocation.xPosition2;
                //y2=MouseLocation.yPosition2;


            //}
            //g2d.drawRect(500,10,150,50);
            //g2d.drawString("Отметить начало отрезка",500,25);

            //g2d.drawRect(500,100,150,50);
            //g2d.drawString("Отметить конец отрезка",500,125);

            if(x1>x2){
                int x3=x1,y3=y1;
                x1=x2;
                y1=y2;
                x2=x3;
                y2=y3;
            }

            double k, b;
            k=(double)(y1-y2)/(x1-x2);
            b=y1-k*x1;
            //length=(Math.sqrt (( Math.pow((x1-x2),2)+Math.pow((y1-y2),2) )));//тута вычисляем длину
            length=Math.max(Math.abs(x1-x2),(Math.abs(y1-y2)));//длина отрезка (кол-во пикселей)
            //System.out.println("длина отрезка "+length);
            Pixel[] pxls = new Pixel[length];//создаю массив пикселей на отрезке

            //отрисовка отрезка с заполнением массива
            if(Math.abs(k)>=1) {
                double x , y=y1;
                for (int i = 0; i < length; i++) {
                    x = (y-b)/k;
                    g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                    pxls[i]=all_pxls[(int)x][(int)y];//заполняю массив пикселей на отрезке
                    //System.out.println(x+":"+y);
                    if (y1<y2)y++;
                    else y--;
                }
            }
            else{
                double x = x1, y;
                for (int i = 0; i < length; i++) {
                    y = k * x + b;
                    g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                    pxls[i]=all_pxls[(int)x][(int)y];//заполняю массив пикселей на отрезке
                    x++;
                }
            }
            //создать новую линию на графике лишь после отрисовки линии.
            if (MouseLocation.pos) {//если не идет выбор конца отрезка
                new Line(pxls);//, lineColor);
            }

            //отрисовка предыдущих линий на картинке
            //if(CanvasGraph.lines.length>0)  //если линии есть, то рисуем их:
            for (int a=0;a<CanvasGraph.lines.length;a++){
                Line l = CanvasGraph.lines[a];
                y1=l.getArray()[0].get_y();
                y2=l.getArray()[l.getArray().length-1].get_y();
                x1=l.getArray()[0].get_x();
                x2=l.getArray()[l.getArray().length-1].get_x();
                k=(double)(y1-y2)/(x1-x2);
                b=y1-k*x1;
                int oldLength=Math.max(Math.abs(x1-x2),(Math.abs(y1-y2)));//длина отрезка (кол-во пикселей)
                //Pixel[] oldPxls = new Pixel[oldLength];//создаю массив пикселей на отрезке
                if(Math.abs(k)>=1) {
                    double x , y=y1;
                    for (int i = 0; i < oldLength; i++) {
                        x = (y-b)/k;
                        g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                        //oldPxls[i]=all_pxls[(int)x][(int)y];//заполняю массив пикселей на отрезке
                        //System.out.println(x+":"+y);
                        if (y1<y2)y++;
                        else y--;
                    }
                }
                else{
                    double x = x1, y;
                    for (int i = 0; i < oldLength; i++) {
                        y = k * x + b;
                        g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                        //oldPxls[i]=all_pxls[(int)x][(int)y];//заполняю массив пикселей на отрезке
                        x++;
                    }
                }
                //super.setFont(new Font("Verdana",Font.PLAIN,18));
                g2d.setFont(new Font("Verdana",Font.PLAIN,18));
                g2d.setColor(new Color(144,9,9));
                g2d.drawString(Integer.toString(a),x1,y1);//подпись номера линии
                g2d.setColor(Color.RED);
                //super.setFont(new Font("Verdana",Font.PLAIN,11));
                g2d.setFont(new Font("Verdana",Font.PLAIN,11));
            }

            //System.out.println(CanvasGraph.lines.length);
            //отрисовка осей графика
            g2d.setColor(Color.RED);
            int deltaH=400;
            g2d.drawLine(50, h+deltaH+50, 50+w, h+deltaH+50);
            g2d.drawLine(50, h+deltaH+50, 50, h+50);
            //


            //поиск максимальной плотности D

            int intensity=255;//максимальная интенсивность фона
            //double[] density = new double[length];
            double D=0;
            for(int a=0;a<length;a++){
                if (pxls[a].get_intensity()<=1){
                    D=2.9;
                    break;
                }
                else if (Math.log10((double)intensity/pxls[a].get_intensity())>D) D=Math.log10((double)intensity/pxls[a].get_intensity());
            }
            //System.out.println(D);


            //подписи оси ординат (плотности)
            int n=20;   //n делений по оси ординат
            int l=(int)D+1;    //максимальное значение оси ординат
            g2d.drawString(Double.toString(0), 15 , (h + deltaH + 50));
            for (int i=1;i<n+1;i++) {
                g2d.drawString(Double.toString((double)l*i/n), 15 , (h + deltaH + 50)-i*deltaH/n);
                //System.out.println(Double.toString((double)i/n));
            }
            //
            //отрисовка графика интенсивности




            //дальше отрисовка графика
            double pxlIntensity;
            int x=0+50,y, x_past, y_past;
            pxlIntensity=pxls[0].get_intensity();
            //y=h+50+deltaH-(int)(deltaH*Math.log10(intensity/pxlIntensity));
            if (pxlIntensity>2) y=h+50+deltaH-(int)((deltaH*Math.log10(intensity/pxlIntensity))/l);
            else y=h+50;
            //y=h+266-(int)pxlIntensity;
            y_past=y;
            g2d.drawLine(x, y, x, y);
            for (int i=1; i<length;i++){
                //g2d.drawString( Integer.toString(Math.abs(y-y_past)),i-1+50, y_past);
                if(Math.abs(y-y_past)>1){

                    for(int p=1;p<Math.abs(y-y_past);p++){
                        if(y_past<y)
                            g2d.drawLine(i-1+50, y_past+p, i-1+50, y_past+p);
                        else
                            g2d.drawLine(i-1+50, y_past-p, i-1+50, y_past-p);
                        //g2d.drawString( "+",i-1+50, y_past-p);
                    }

                }
                y_past=y;
                pxlIntensity=pxls[i].get_intensity();
                x=i+50;
                //y=h+50+deltaH-(int)(deltaH*Math.log10(intensity/pxlIntensity));
                //if (D>1) y=h+50+deltaH-(int)((deltaH*Math.log10(intensity/pxlIntensity))/l);
                if (pxlIntensity>2) y=h+50+deltaH-(int)((deltaH*Math.log10(intensity/pxlIntensity))/l);
                else y=h+50;
                //y=h+266-(int)pxlIntensity;
                g2d.drawLine(x, y, x, y);
                //g2d.drawLine(i+50, this.getHeight()-(int)pxlIntensity, i+50, this.getHeight()-(int)pxlIntensity);
            }
            //pxlIntensity=pxls[1].get_intensity();
            //g2d.drawString( (Integer.toString((int)Math.log10(intensity/pxlIntensity))),50, this.getHeight()-100);
            // g2d.drawString( (Integer.toString((int)(pxls[1].get_intensity()))),50, this.getHeight()-100);
            super.repaint();  /* 	Вызывает обновление себя после завершения рисования	*/
        } catch (IOException ex) {
            // handle exception...
        }
    }
}