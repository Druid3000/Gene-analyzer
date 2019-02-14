import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow(){
        super("Определение оптической плотности");
        super.setMinimumSize(new Dimension(1000,1000));
        //super.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //super.setLayout (new FlowLayout(FlowLayout.LEFT));
        //запиливаю менюху!
        {
            Font font = new Font("Verdana", Font.PLAIN, 14); //стиль менюшных строк
            JMenuBar menuBar = new JMenuBar();                          //меню-строка
            JMenu fileMenu = new JMenu("File");                      //кнопка меню "file"
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
            JMenuItem folderItem = new JMenuItem("Folder");
            folderItem.setFont(font);
            newMenu.add(folderItem);
            JMenuItem openItem = new JMenuItem("Open");
            openItem.setFont(font);
            fileMenu.add(openItem);
            JMenuItem closeItem = new JMenuItem("Close");
            closeItem.setFont(font);
            fileMenu.add(closeItem);
            JMenuItem closeAllItem = new JMenuItem("Close all");
            closeAllItem.setFont(font);
            fileMenu.add(closeAllItem);
            fileMenu.addSeparator();
            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.setFont(font);
            fileMenu.add(exitItem);

            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            menuBar.add(fileMenu);
            JMenu settingsMenu = new JMenu("Settings");         //кнопка меню "Settings"
            settingsMenu.setFont(font);
            menuBar.add(settingsMenu);
            super.setJMenuBar(menuBar);
            super.setVisible(true);
        }

    }
}
