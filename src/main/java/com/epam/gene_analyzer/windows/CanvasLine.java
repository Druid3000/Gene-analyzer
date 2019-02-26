package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;
import com.epam.gene_analyzer.model.Line;
import com.epam.gene_analyzer.windows.MouseLocation;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/* Класс с отрисовкой отрезка и заполнением массива пикселей */
public class CanvasLine extends JComponent {

    private BufferedImage picture;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private JPopupMenu popupMenu;//=new JPopupMenu();
    private MainController mainController;
    private boolean mode = true;  //true - линии, false - области
    private boolean position = true;//true = 1 точка. false = 2 точка
    private boolean chooseBackgroundIntensity = false;//true = режим выбора интенсивности. false = обычный режим
    private MouseLocation mouseLocation;

    CanvasLine(MainController mc) {
        mainController = mc;
        setVisible(true);
        addListener();
        addJPopMenu();
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

    private void drawCursorX(Graphics2D graphics2D) {
        if (chooseBackgroundIntensity) {
            graphics2D.setColor(Color.magenta);
            int x = mouseLocation.getXPositionNow();
            int y = mouseLocation.getYPositionNow();
            for (int i = -10; i < 11; i++) {
                if (x > 0 & x < getWidth() & y > 0 & y < getHeight())
                    graphics2D.drawLine(x + i, y, x + i, y);
            }
            for (int i = -10; i < 11; i++) {
                if (x > 0 & x < getWidth() & y > 0 & y < getHeight())
                    graphics2D.drawLine(x, y + i, x, y + i);
            }
        }
    }

    /*Метод, перерисовывающий элемент внутри окна
     *при обновлении*/
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        reloadJPopMenu();
        Graphics2D graphics2D = (Graphics2D) graphics;
        drawPicture(graphics2D);
        drawCurrentLine(graphics2D);
        drawCursorX(graphics2D);
        if (mode) drawLines(graphics2D);
        else {
            drawAreaPerimeter(graphics2D);//или drawArea(graphics2D) - закрасит область
        }
        super.repaint();
    }

    private void addListener() {
        mouseLocation = new MouseLocation(this, mainController);
        addMouseListener(mouseLocation);
        addMouseMotionListener(mouseLocation);
    }

    private void addJPopMenu() {
        popupMenu = new JPopupMenu();
        JMenuItem deleteAll = new JMenuItem("Удалить все линии");
        popupMenu.add(deleteAll);

        popupMenu.addSeparator();


        JMenuItem chooseBackgroundIntensity = new JMenuItem("Установить интенсивность фона по пикселю");
        chooseBackgroundIntensity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChooseBackgroundIntensity(true);
            }
        });
        popupMenu.add(chooseBackgroundIntensity);

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
        if (picture == null) {
            popupMenu.setVisible(false);
        } else {
            //popupMenu.setVisible(true);
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
                //----
                popupMenu.addSeparator();
                //----
                JMenuItem chooseBackgroundIntensity = new JMenuItem("Установить интенсивность фона по пикселю");
                chooseBackgroundIntensity.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setChooseBackgroundIntensity(true);
                    }
                });
                popupMenu.add(chooseBackgroundIntensity);

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
                //----
                popupMenu.addSeparator();
                //----
                JMenuItem chooseBackgroundIntensity = new JMenuItem("Установить интенсивность фона по пикселю");
                chooseBackgroundIntensity.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setChooseBackgroundIntensity(true);

                    }
                });
                popupMenu.add(chooseBackgroundIntensity);

                setComponentPopupMenu(popupMenu);
            }
        }
    }

    private void drawCurrentLine(Graphics2D graphics2D) {
        int x1 = mouseLocation.getXPosition1();
        int y1 = mouseLocation.getYPosition1();

        if (mode & !position & picture != null & !chooseBackgroundIntensity) {
            int x2 = mouseLocation.getXPositionNow();
            int y2 = mouseLocation.getYPositionNow();
            graphics2D.setColor(Color.red);
            graphics2D.drawLine(x1, y1, x2, y2);
        }
    }

    protected boolean getPosition() {
        return position;
    }

    protected void changePosition() {
        position = !position;
    }

    protected boolean getMode() {
        return mode;
    }

    protected void setMode(boolean m) {
        mode = m;
    }

    protected boolean isChooseBackgroundIntensity() {
        return chooseBackgroundIntensity;
    }

    protected void setChooseBackgroundIntensity(boolean cbi) {
        chooseBackgroundIntensity = cbi;
    }

    protected void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }


}