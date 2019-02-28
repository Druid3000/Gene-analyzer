package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.services.MainService;

import javax.swing.*;
import java.awt.*;

/** Class based on JFrame for creating main window
 * Using lines and menu.
 *
 */
public class MainWindow extends JFrame {

    private CanvasLine canvasLine;
    private Menu menu;

    /** Method for calling services.
     *
     */
    public MainWindow() {
        MainService mainService = new MainService();
        GraphWindow graphWindow = new GraphWindow(mainService);
        AboutWindow aboutWindow = new AboutWindow();
        TableWindow tableWindow = new TableWindow(mainService);

        canvasLine = new CanvasLine(mainService);
        menu = new Menu(aboutWindow, tableWindow, mainService, graphWindow, canvasLine);
    }

    /** Method for showing window
     *
     */
    public void showWindow(){
        setTitle("Optical density determination");
        setIconImage(getToolkit().getImage("src/main/resources/iconMain.gif"));
        setLocation(60, 70);
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu.getMenuBar());
        add(canvasLine);
        pack();
        setVisible(true);
    }
}
