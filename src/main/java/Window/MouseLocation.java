package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseLocation implements MouseListener, MouseMotionListener {
    private boolean pos = true;//true = 1 точка. false = 2 точка
    private int xPosition1 = 0;
    private int yPosition1 = 0;
    private int xPosition2 = 1;
    private int yPosition2 = 1;
    private int xPositionNow;
    private int yPositionNow;

    public int getxPosition1() {
        return xPosition1;
    }

    public int getxPosition2() {
        return xPosition2;
    }

    public int getyPosition1() {
        return yPosition1;
    }

    public int getyPosition2() {
        return yPosition2;
    }

    public int getxPositionNow() {
        return xPositionNow;
    }

    public int getyPositionNow() {
        return yPositionNow;
    }

    public void mouseClicked(MouseEvent Event) {
    }

    public void mouseEntered(MouseEvent Event) {
    }

    public void mouseExited(MouseEvent Event) {
    }

    public void mousePressed(MouseEvent Event) {
        if (pos) {
            xPosition1 = Event.getX();
            yPosition1 = Event.getY();
        } else {
            xPosition2 = Event.getX();
            yPosition2 = Event.getY();
        }
        pos = !pos;
    }

    public void mouseReleased(MouseEvent Event) {
    }

    public void mouseMoved(MouseEvent e) {
        xPositionNow = e.getPoint().x;
        yPositionNow = e.getPoint().y;
    }

    public void mouseDragged(MouseEvent e) {
    }
}