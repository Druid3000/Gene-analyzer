import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame {
    public static File picture;
    public MainWindow(){
        super("Определение оптической плотности");
        super.setMinimumSize(new Dimension(1000,1000));
        //super.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //super.setLayout (new FlowLayout(FlowLayout.LEFT));

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        //запиливаю менюху!
        {

            Font font = new Font("Verdana", Font.PLAIN, 14); //стиль менюшных строк
            JMenuBar menuBar = new JMenuBar();                          //меню-строка
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
                        //label.setText(file.getName());
                    }


                    CanvasLine canv=new CanvasLine();
                    canv.setPreferredSize(new Dimension(400,367+10));
                    canv.addMouseListener(new MouseLocation());
                    canv.addMouseMotionListener(new MouseLocation());

                    panel.add(canv);
                    //revalidate();

                    getContentPane().add(panel);



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
                    panel.setVisible(false);
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
                    JFrame newWindow = new AboutFrame();
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

            graphItem.addActionListener(new ActionListener() {   //opening new window with main inf
                public void actionPerformed(ActionEvent e) {
                    final GraphWindow graphWindow = new GraphWindow();
                    final CanvasGraph canvGraph = new CanvasGraph();
                    canvGraph.setPreferredSize(new Dimension(500,500));

                    //panel.add(canvGraph);
                    //MainWindow.revalidate();
                    graphWindow.add(canvGraph);
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
            super.setJMenuBar(menuBar);
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
