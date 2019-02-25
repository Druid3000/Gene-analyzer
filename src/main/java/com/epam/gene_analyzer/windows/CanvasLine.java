package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;
import com.epam.gene_analyzer.model.Line;
import com.epam.gene_analyzer.model.Pixel;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/* Класс с отрисовкой отрезка и заполнением массива пикселей */
public class CanvasLine extends JComponent {

    private BufferedImage picture;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private JPopupMenu popupMenu;//=new JPopupMenu();
    private MainController mainController;
    private boolean mode = true;  //true - линии, false - области


    protected CanvasLine(MainController mainController) {
        addListener();
        addJPopMenu();
        this.mainController = mainController;
    }


    private void drawPicture(Graphics2D graphics2D) {//отрисовка изображения
        picture = mainController.getPicture();
        if (picture != null) {
            this.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
            graphics2D.drawImage(picture, 0, 0, this);
        }
    }

    private void drawLines(Graphics2D graphics2D) {
        if (picture != null) {
            graphics2D.setColor(Color.RED); //цвет для графика
            int x1, x2, y1, y2, length; //координаты начала и конца отрезка
            int x_, y_;              //чисто для проверки вхождения пикселей в изображение
            //введение координат отрезка с мышки
            if (lines.size() > 0) {
                for (int i = 0; i < lines.size(); i++) {
                    graphics2D.setColor(Color.RED);//цвет для графика
                    x1 = lines.get(i).getArray().get(0).getX();
                    x2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).getX();
                    y1 = lines.get(i).getArray().get(0).getY();
                    y2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).getY();
                    graphics2D.drawLine(x1, y1, x2, y2);
                    graphics2D.setFont(new Font("Verdana", Font.PLAIN, 18));
                    graphics2D.setColor(new Color(144, 9, 9));
                    graphics2D.drawString(Integer.toString(lines.get(i).getId()), x1, y1);//подпись номера линии
                }
            }
        }

    }

    private void drawAreas(Graphics2D graphics2D) {// отрисовка всех областей
        if (picture != null & mainController.getArea(0) != null) {
            for (int n = 0; n < mainController.getAreas().size(); n++) {
                graphics2D.setColor(Color.RED);//цвет для области
                for (int i = 0; i < mainController.getAreas().get(n).getArea().size(); i++) {
                    graphics2D.drawLine(mainController.getAreas().get(n).getArea().get(i).getX(),
                            mainController.getAreas().get(n).getArea().get(i).getY(),
                            mainController.getAreas().get(n).getArea().get(i).getX(),
                            mainController.getAreas().get(n).getArea().get(i).getY());
                }
                graphics2D.setFont(new Font("Verdana", Font.PLAIN, 18));//номер области
                graphics2D.setColor(Color.WHITE);
                graphics2D.drawString(Integer.toString(mainController.getAreas().get(n).getId()),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getX(),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getY());
            }
        }
    }

    private void drawAreaPerimeter(Graphics2D graphics2D) {
        if (picture != null & mainController.getAreaPerimeter(0) != null) {
            for (int n = 0; n < mainController.getAreas().size(); n++) {
                graphics2D.setColor(Color.red);//цвет для области
                for (int i = 0; i < mainController.getAreaPerimeter(n).size(); i++) {
                    graphics2D.drawLine(mainController.getAreaPerimeter(n).get(i).getX(),
                            mainController.getAreaPerimeter(n).get(i).getY(),
                            mainController.getAreaPerimeter(n).get(i).getX(),
                            mainController.getAreaPerimeter(n).get(i).getY());
                }
                graphics2D.setFont(new Font("Verdana", Font.PLAIN, 18));//номер области
                graphics2D.drawString(Integer.toString(mainController.getAreas().get(n).getId()),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getX(),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getY());
            }
        }
    }

    protected void setMode(boolean m) {
        mode = m;
    }

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        reloadJPopMenu();
        Graphics2D graphics2D = (Graphics2D) graphics;
        drawPicture(graphics2D);
        if (mode) drawLines(graphics2D);
        else {
            drawAreaPerimeter(graphics2D);//или drawArea(graphics2D) - закрасит область
        }
        super.repaint();
    }

    private void addListener() {
        addMouseListener(new MouseLocation() {

            private boolean position = true;//true = 1 точка. false = 2 точка
            private int xPosition1 = 0;
            private int yPosition1 = 0;
            private int xPosition2 = 1;
            private int yPosition2 = 1;
            private int xPositionNow;
            private int yPositionNow;

            public int getXPosition1() {
                return xPosition1;
            }

            public int getXPosition2() {
                return xPosition2;
            }

            public int getYPosition1() {
                return yPosition1;
            }

            public int getYPosition2() {
                return yPosition2;
            }

            public int getXPositionNow() {
                return xPositionNow;
            }

            public int getYPositionNow() {
                return yPositionNow;
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    xPositionNow = event.getX();
                    yPositionNow = event.getY();
                    if (position) {
                        xPosition1 = event.getX();
                        yPosition1 = event.getY();
                    } else {
                        xPosition2 = event.getX();
                        yPosition2 = event.getY();

                        /////////метод из мэйнвиндоу//////////
                        //оставил работу с геттерами а не с полями напрямую для того, чтоб потом вынести
                        // слушатель в контроллер при желании
                        if (getXPositionNow() < getWidth() & getYPositionNow() < getHeight()) {
                            if (mode) {
                                if (picture != null) {
                                    int x1 = getXPosition1();
                                    int y1 = getYPosition1();
                                    Pixel p1 = new Pixel();
                                    p1.setX(x1);
                                    p1.setY(y1);
                                    int x2 = getXPosition2();
                                    int y2 = getYPosition2();
                                    Pixel p2 = new Pixel();
                                    p2.setX(x2);
                                    p2.setY(y2);
                                    if (!(x1 == x2 & y1 == y2)) {
                                        if (mainController.getLines().size() == 10)
                                            JOptionPane.showMessageDialog(null, "Удалите хотя бы одну линию, чтобы добавить новую!");
                                        else
                                            mainController.addLine(p1, p2);
                                        lines = mainController.getLines();
                                    }
                                }
                            }

                        }
                    }
                    if (!mode) {
                        int x = getXPositionNow(), y = getYPositionNow();
                        Pixel p = new Pixel();
                        p.setX(x);
                        p.setY(y);
                        p.setR((new Color(picture.getRGB(x, y)).getRed()));
                        p.setG((new Color(picture.getRGB(x, y)).getGreen()));
                        p.setB((new Color(picture.getRGB(x, y)).getBlue()));
                        mainController.addArea(p);//set -> add (в массив)

                        //Аня: проверяем открыта ли таблица, если да, то передаем область в обработку
                        //UPD (Макс): теперь области автоматом будут в таблицу попадать, это нам не нужно
                    }
                    position = !position;
                }
            }

            public void mouseReleased(MouseEvent event) {
            }
        });
    }

    private void addJPopMenu() {
        popupMenu = new JPopupMenu();
        JMenuItem deleteAll = new JMenuItem("Удалить все линии");
        popupMenu.add(deleteAll);
        setComponentPopupMenu(popupMenu);
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                reloadJPopMenu();
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

    private void reloadJPopMenu() {
        if (mode) {
            popupMenu = new JPopupMenu();
            for (int i = 0; i < lines.size(); i++) {
                JMenuItem cutMenuItem = new JMenuItem("Удалить линию номер " + lines.get(i).getId());
                cutMenuItem.addActionListener(new JPopMenuListener(i) {
                    public void actionPerformed(ActionEvent e) {
                        lines.remove(this.id);
                    }
                });
                popupMenu.add(cutMenuItem);
            }
            popupMenu.addSeparator();

            JMenuItem deleteAll = new JMenuItem("Удалить все линии");
            deleteAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lines.clear();
                }
            });
            popupMenu.add(deleteAll);
            setComponentPopupMenu(popupMenu);
        } else {
            popupMenu = new JPopupMenu();
            for (int i = 0; i < mainController.getAreas().size(); i++) {
                JMenuItem cutMenuItem = new JMenuItem("Удалить область номер " + mainController.getArea(i).getId());
                cutMenuItem.addActionListener(new JPopMenuListener(mainController.getArea(i).getId()) {
                    public void actionPerformed(ActionEvent e) {
                        mainController.removeArea(this.id);
                        mainController.updateData();
                    }
                });
                popupMenu.add(cutMenuItem);
            }
            popupMenu.addSeparator();

            JMenuItem deleteAll = new JMenuItem("Удалить все области");
            deleteAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainController.removeAreas();
                    mainController.updateData();
                }
            });
            popupMenu.add(deleteAll);
            setComponentPopupMenu(popupMenu);
        }
    }
}