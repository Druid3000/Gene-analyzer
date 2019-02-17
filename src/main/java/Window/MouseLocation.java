package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseLocation implements MouseListener, MouseMotionListener {
    private boolean pos=true;//true = 1 точка. false = 2 точка
    private int xPosition1=0;
    private int yPosition1=0;
    private int xPosition2=1;
    private int yPosition2=1;
    private int xPositionNow;
    private int yPositionNow;
    public int getxPosition1(){
        return xPosition1;
    }
    public int getxPosition2(){
        return xPosition2;
    }
    public int getyPosition1(){
        return yPosition1;
    }
    public boolean getPos(){return pos;}
    public int getyPosition2(){
        return yPosition2;
    }
    public int getxPositionNow(){
        return yPosition1;
    }
    public int getyPositionNow(){
        return yPosition2;
    }
    public void setPos(boolean p){
        pos=p;
    }
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
        //if((Event.getX() < CanvasLine.weight)&(Event.getY()< CanvasLine.height)) {
            if (pos) {
                xPosition1 = Event.getX() ;
                yPosition1 = Event.getY() ;
            } else {
                xPosition2 = Event.getX() ;
                yPosition2 = Event.getY() ;
            }
            pos =!pos;
            //System.out.println("x " + xPosition1);
            //System.out.println("y " + yPosition1);
        //}
    }
    public void mouseReleased (MouseEvent Event)
    {
        //xPosition2 = Event.getX() ;
        //yPosition2 = Event.getY() ;
        //pos=true;
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