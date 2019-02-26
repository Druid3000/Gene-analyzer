package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class Menu {
    private MainController mainController;
    private CanvasLine canvasLine;
    private GraphWindow graphWindow;
    private TableWindow tableWindow;
    private JMenuBar menuBar;
    private File picture;
    private AboutWindow aboutWindow;

    Menu(AboutWindow aw, TableWindow tw, MainController mc, GraphWindow gw, CanvasLine cl) {
        mainController = mc;
        graphWindow = gw;
        tableWindow = tw;
        canvasLine = cl;
        aboutWindow = aw;

        final Font font = new Font("Verdana", Font.PLAIN, 14); //стиль менюшных строк
        menuBar = new JMenuBar();                          //меню-строка
        final JMenu fileMenu = new JMenu("File");                      //кнопка меню "file"
        fileMenu.setFont(font);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);
        final JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setFont(font);
        fileMenu.add(closeItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    picture = fileOpen.getSelectedFile();
                    mainController.setPicture(picture);
                    canvasLine.setVisible(true);
                }
                closeItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        canvasLine.setVisible(false);
                    }
                });
            }
        });




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
        JMenu helpMenu = new JMenu("Help");         //кнопка меню "Help"
        helpMenu.setFont(font);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(font);
        helpMenu.add(aboutItem);

        aboutItem.addActionListener(new ActionListener() {   //opening new window with main inf
            public void actionPerformed(ActionEvent e) {
                aboutWindow.setVisible(true);
            }
        });

        menuBar.add(fileMenu);
        JMenu editMenu = new JMenu("Mode");
        editMenu.setFont(font);

        JMenuItem drawLineItem = new JMenuItem("Draw line");
        drawLineItem.setFont(font);
        editMenu.add(drawLineItem);
        drawLineItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasLine.setMode(true);
            }
        });

        JMenuItem drawAreaItem = new JMenuItem("Choose area");
        drawAreaItem.setFont(font);
        editMenu.add(drawAreaItem);
        drawAreaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasLine.setMode(false);
            }
        });

        editMenu.addSeparator();



        final JMenuItem graphItem = new JMenuItem("Get graph");
        graphItem.setFont(font);
        editMenu.add(graphItem);

        graphItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphWindow.setVisible(true);
                closeItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        graphWindow.setVisible(false);
                    }
                });


            }
        });

        final JMenuItem dataItem = new JMenuItem("Show data");
        dataItem.setFont(font);
        editMenu.add(dataItem);

        dataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableWindow.setVisible(true);
                tableWindow.be = true;
                closeItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tableWindow.setVisible(false);
                    }
                });


            }
        });

        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }

    //TODO (create methods for creating menu and sub-menu buttons
    JMenuBar getMenuBar() {
        return menuBar;
    }
}
