
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseLocation implements MouseListener, MouseMotionListener {
    public static boolean pos=true;//true = 1 точка. false = 2 точка
    public static int xPosition1=0;
    public static int yPosition1=0;
    public static int xPosition2=1;
    public static int yPosition2=1;
    public static int xPositionNow;
    public static int yPositionNow;
    public void mouseClicked (MouseEvent Event)
    {
        //System.out.println("mouseClicked");
    }
    public void mouseEntered (MouseEvent Event)
    {
        //System.out.println("mouseEntered");
    }
    public void mouseExited (MouseEvent Event)
    {
        //System.out.println("mouseExited");
    }
    public void mousePressed (MouseEvent Event)
    {
        //System.out.println("mousePressed");
        if((Event.getX() < CanvasLine.weight)&(Event.getY()< CanvasLine.height)) {
            if (pos) {
                xPosition1 = Event.getX() ;
                yPosition1 = Event.getY() ;
            } else {
               xPosition2 = Event.getX() ;
                yPosition2 = Event.getY() ;
            }
            pos = !pos;
            //System.out.println("x " + xPosition1);
            //System.out.println("y " + yPosition1);
        }
    }
    public void mouseReleased (MouseEvent Event)
    {
        //System.out.println("mouseReleased");
    }
    //@Override
    public void mouseMoved (MouseEvent e)
    {
        xPositionNow=e.getPoint().x;
        yPositionNow=e.getPoint().y;
    }
    //@Override
    public void mouseDragged (MouseEvent e)
    {
        //
    }
    public MouseLocation() {

        /*addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                repaint();
            }
        });*/

    }
    /*@Override
    public void mouseMoved(MouseEvent e) {
        lbStaticLabel.setText("X: " + e.getX() + ", Y: " + e.getY());

        JLabel lbMoveLabel = null;
        for(Component c :  pnlPanel1.getComponents()) {
            if(c.getClass().getSimpleName().equals("JLabel"))
                lbMoveLabel = (JLabel) c;
        }

        if( lbMoveLabel != null) {
            lbMoveLabel.setText("" + e.getX() + " " + e.getY());
            FontMetrics fm = lbMoveLabel.getGraphics().getFontMetrics();
            lbMoveLabel.setLocation(e.getX(), e.getY() + 20);   // чуть ниже курсора мышки, чтоб было видно
            lbMoveLabel.setSize(fm.stringWidth(lbMoveLabel.getText()), fm.getHeight());
        }

    }*/
    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("X: " + xPosition + ", Y: " + yPosition, xPosition, yPosition);
    }*/
}