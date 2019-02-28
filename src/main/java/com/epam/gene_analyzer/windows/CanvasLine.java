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


/** Class for building Line.
 * Drawing a segment and filling in an array of pixels.
 * Use: buffered image, array of lines, popup menu.
 * For mode: true - lines, false - areas.
 * For position: true = 1 point, false = 2 point.
 * For chooseBackgroundIntensity: true = mode of selection of intensity. false = normal mode
 *
 */
public class CanvasLine extends JComponent {

    private BufferedImage picture;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private JPopupMenu popupMenu;
    private MainController mainController;
    private boolean mode = true;
    private boolean position = true;
    private boolean chooseBackgroundIntensity = false;
    private MouseLocation mouseLocation;

    CanvasLine(MainController mc) {
        mainController = mc;
        setVisible(true);
        addListener();
        addJPopMenu();
    }


    /** Method for redrawing element inside window when updating
     *
     * @param graphics graphics
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        reloadJPopMenu();
        Graphics2D graphics2D = (Graphics2D) graphics;
        drawPicture(graphics2D);
        drawCurrentLine(graphics2D);
        drawCursorX(graphics2D);
        if (mode) drawLines(graphics2D);
        else {
            drawAreaPerimeter(graphics2D);
        }
        super.repaint();
    }

    /** Method for getting position
     *
     * @return position
     */
    protected boolean getPosition() {
        return position;
    }

    /** Method for changing position
     *
     */
    protected void changePosition() {
        position = !position;
    }

    /** Method for getting mode
     *
     * @return mode
     */
    protected boolean getMode() {
        return mode;
    }

    /** Method for setting mode
     *
     * @param m mode
     */
    protected void setMode(boolean m) {
        mode = m;
    }

    /** Method for getting Background Intensity
     *
     * @return Background Intensity
     */
    protected boolean isChooseBackgroundIntensity() {
        return chooseBackgroundIntensity;
    }

    /** Method for setting Background Intensity
     *
     * @param cbi Background Intensity
     */
    protected void setChooseBackgroundIntensity(boolean cbi) {
        chooseBackgroundIntensity = cbi;
    }

    /** Method for setting lines
     *
     * @param lines lines
     */
    protected void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    /** Method for drawing a picture
     *
     * @param graphics2D graphics2D
     */
    private void drawPicture(Graphics2D graphics2D) {
        picture = mainController.getPicture();
        if (picture != null) {
            this.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
            graphics2D.drawImage(picture, 0, 0, this);
        }
    }

    /** Method for drawing lines.
     * Input coordinates of the segment with the mouse.
     * Label line number.
     *
     * @param graphics2D graphics2D
     */
    private void drawLines(Graphics2D graphics2D) {
        if (picture != null) {
            graphics2D.setColor(Color.RED);
            int x1, x2, y1, y2, length;
            int x_, y_;

            if (lines.size() > 0) {
                for (int i = 0; i < lines.size(); i++) {
                    graphics2D.setColor(Color.RED);
                    x1 = lines.get(i).getArray().get(0).getX();
                    x2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).getX();
                    y1 = lines.get(i).getArray().get(0).getY();
                    y2 = lines.get(i).getArray().get(lines.get(i).getArray().size() - 1).getY();
                    graphics2D.drawLine(x1, y1, x2, y2);
                    graphics2D.setFont(new Font("Verdana", Font.PLAIN, 24));
                    graphics2D.setColor(Color.magenta);
                    graphics2D.drawString(Integer.toString(lines.get(i).getId()), x1, y1);
                }
            }
        }

    }

    /** Method for drawing areas.
     * Label area number.
     *
     * @param graphics2D graphics2D
     */
    private void drawAreas(Graphics2D graphics2D) {
        if (picture != null & mainController.getArea(0) != null) {
            for (int n = 0; n < mainController.getAreas().size(); n++) {
                graphics2D.setColor(Color.RED);
                for (int i = 0; i < mainController.getAreas().get(n).getArea().size(); i++) {
                    graphics2D.drawLine(mainController.getAreas().get(n).getArea().get(i).getX(),
                            mainController.getAreas().get(n).getArea().get(i).getY(),
                            mainController.getAreas().get(n).getArea().get(i).getX(),
                            mainController.getAreas().get(n).getArea().get(i).getY());
                }
                graphics2D.setFont(new Font("Verdana", Font.PLAIN, 18));
                graphics2D.setColor(Color.WHITE);
                graphics2D.drawString(Integer.toString(mainController.getAreas().get(n).getId()),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getX(),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getY());
            }
        }
    }

    /** Method for drawing area perimeters
     *
     * @param graphics2D graphics2D
     */
    private void drawAreaPerimeter(Graphics2D graphics2D) {
        if (picture != null & mainController.getAreaPerimeter(0) != null) {
            for (int n = 0; n < mainController.getAreas().size(); n++) {
                graphics2D.setColor(Color.red);
                for (int i = 0; i < mainController.getAreaPerimeter(n).size(); i++) {
                    graphics2D.drawLine(mainController.getAreaPerimeter(n).get(i).getX(),
                            mainController.getAreaPerimeter(n).get(i).getY(),
                            mainController.getAreaPerimeter(n).get(i).getX(),
                            mainController.getAreaPerimeter(n).get(i).getY());
                }
                graphics2D.setFont(new Font("Verdana", Font.PLAIN, 24));
                graphics2D.setColor(Color.magenta);
                graphics2D.drawString(Integer.toString(mainController.getAreas().get(n).getId()),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getX(),
                        mainController.getAreas().get(n).getArea().get(mainController.getAreas().get(n).getArea().size() / 2).getY());
            }
        }
    }

    /** Method for drawing cursor
     *
     * @param graphics2D graphics2D
     */
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


    /** Method for adding listener of mouse location
     *
     */
    private void addListener() {
        mouseLocation = new MouseLocation(this, mainController);
        addMouseListener(mouseLocation);
        addMouseMotionListener(mouseLocation);}

    /** Method for adding JPop menu.
     * Delete lines, set background intensity by pixel, set background intensity.
     *
     */
    private void addJPopMenu() {
        popupMenu = new JPopupMenu();
        JMenuItem deleteAll = new JMenuItem("Remove all lines");
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

    /** Method for using JPop menu
     *
     */
    private void reloadJPopMenu() {
        if (picture == null) {
            popupMenu.setVisible(false);
        } else {

            if (mode) {
                popupMenu = new JPopupMenu();
                for (int i = 0; i < lines.size(); i++) {
                    JMenuItem cutMenuItem = new JMenuItem("Remove line " + lines.get(i).getId());
                    cutMenuItem.addActionListener(new JPopMenuListener(i) {
                        public void actionPerformed(ActionEvent e) {
                            lines.remove(this.id);
                        }
                    });
                    popupMenu.add(cutMenuItem);
                }
                popupMenu.addSeparator();

                JMenuItem deleteAll = new JMenuItem("Remove all lines");
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
                    JMenuItem cutMenuItem = new JMenuItem("Remove area " + mainController.getArea(i).getId());
                    cutMenuItem.addActionListener(new JPopMenuListener(mainController.getArea(i).getId()) {
                        public void actionPerformed(ActionEvent e) {
                            mainController.removeArea(this.id);
                            mainController.updateData();
                        }
                    });
                    popupMenu.add(cutMenuItem);
                }
                popupMenu.addSeparator();

                JMenuItem deleteAll = new JMenuItem("Remove all areas");
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

    /** Method for drawing line for building graphic
     *
     * @param graphics2D graphics2D
     */
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


}