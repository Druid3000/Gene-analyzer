package Window;

import Controllers.GraphController;
import Controllers.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu {
    private MainController mainController;
    private CanvasLine canvasLine;
    private GraphController graphController;
    private GraphWindow graphWindow;
    private JMenuBar menuBar;
    private File picture;
    public Menu(MainController mc, GraphWindow gw, final CanvasLine cl){//, MouseLocation open, MouseLocation coord){//MainController mc, File pict){
            mainController=mc;
            graphWindow =gw;
            canvasLine=cl;
        //picture = pict;
        //TO DO

            final Font font = new Font("Verdana", Font.PLAIN, 14); //стиль менюшных строк
            menuBar = new JMenuBar();                          //меню-строка
            final JMenu fileMenu = new JMenu("File");                      //кнопка меню "file"
            fileMenu.setFont(font);
            JMenu newMenu = new JMenu("New");                       //под-меню в меню "file"
            newMenu.setFont(font);
            fileMenu.add(newMenu);
            JMenuItem txtFileItem = new JMenuItem("Text file");
            txtFileItem.setFont(font);
            newMenu.add(txtFileItem);
            JMenuItem imgFileItem = new JMenuItem("Image file");
            imgFileItem.setFont(font);
            newMenu.add(imgFileItem);

            imgFileItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Открыть файл");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        picture = fileopen.getSelectedFile();
                        cl.drawPicture(picture);
                        //label.setText(file.getName());
                    }

                    //if (panel.isVisible()) {
                        /*CanvasLine canv = new CanvasLine();
                        canv.setPreferredSize(new Dimension(400, 367 + 10));
                        canv.addMouseListener(new MouseLocation());
                        canv.addMouseMotionListener(new MouseLocation());
                        canv.setFont(new Font("Verdana", Font.PLAIN, 11));

                        panel.add(canv);
                        //revalidate();

                        getContentPane().add(panel);*/
                    //}
                    //else
                    //    panel.setVisible(true);


                }
            });

            JMenuItem folderItem = new JMenuItem("Folder");
            folderItem.setFont(font);
            newMenu.add(folderItem);
            JMenuItem openItem = new JMenuItem("Open");
            openItem.setFont(font);
            fileMenu.add(openItem);
            final JMenuItem closeItem = new JMenuItem("Close");
            closeItem.setFont(font);
            fileMenu.add(closeItem);



            JMenuItem closeAllItem = new JMenuItem("Close all");
            closeAllItem.setFont(font);
            fileMenu.add(closeAllItem);
            fileMenu.addSeparator();

            closeAllItem.addActionListener(new ActionListener() {
                //@Override
                public void actionPerformed(ActionEvent e) {
                    //panel.setVisible(false);
                }
            });

            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.setFont(font);
            fileMenu.add(exitItem);


            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            menuBar.add(fileMenu);
            JMenu helpMenu = new JMenu("Help");         //кнопка меню "Help"
            helpMenu.setFont(font);

            JMenuItem aboutItem = new JMenuItem("About");
            aboutItem.setFont(font);
            helpMenu.add(aboutItem);

            aboutItem.addActionListener(new ActionListener() {   //opening new window with main inf
                public void actionPerformed(ActionEvent e) {
                    JFrame newWindow = new AboutWindow();
                    newWindow.setVisible(true);
                }
            });

            menuBar.add(fileMenu);
            JMenu editMenu = new JMenu("Edit");
            editMenu.setFont(font);

            JMenuItem drawItem = new JMenuItem("Draw line");
            drawItem.setFont(font);
            editMenu.add(drawItem);

            final JMenuItem graphItem = new JMenuItem("Get graph");
            graphItem.setFont(font);
            editMenu.add(graphItem);

            graphItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //final GraphWindow graphWindow = new GraphWindow();
                    //final CanvasGraph canvGraph = new CanvasGraph();
                    //canvGraph.setPreferredSize(new Dimension(500,500));
                    //panel.add(canvGraph);
                    //MainWindow.revalidate();
                    //canvGraph.setFont(new Font("Verdana",font.PLAIN,11));
                    //graphWindow.add(canvGraph);
                    //graphWindow.setVisible(true);
                    graphWindow.setVisible(true);
                    closeItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //panel.remove(canvGraph);
                            graphWindow.setVisible(false);
                        }
                    });


                }
            });

            menuBar.add(editMenu);

            menuBar.add(helpMenu);
        }

    //TO DO (create methods for creating menu and sub-menu buttons
    public JMenuBar get_menuBar() {
        return menuBar;
    }
}
