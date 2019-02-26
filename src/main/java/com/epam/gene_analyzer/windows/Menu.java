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

        final Font font = new Font("Verdana", Font.PLAIN, 14); //style of menu
        menuBar = new JMenuBar();                          //create menu bar
        final JMenuItem closeItem = new JMenuItem();


        final JMenu fileMenu = new JMenu("File");                      //button menu "file"
        fileMenu.setFont(font);
        menuBar.add(fileMenu);
        createFileItems(fileMenu, font);

        final JMenu modeMenu = new JMenu("Mode");                   //button menu "mode"
        modeMenu.setFont(font);
        menuBar.add(modeMenu);
        createModeItems(modeMenu, font, closeItem);

        final JMenu helpMenu = new JMenu("Help");         //button menu "Help"
        helpMenu.setFont(font);
        menuBar.add(helpMenu);
        createHelpItems(helpMenu, font);

    }

    private void createFileItems(final JMenu fileMenu, final Font font){
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);
        final JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setFont(font);
        fileMenu.add(closeItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showDialog(null, "Open file");
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
    }

    private void createModeItems(final JMenu modeMenu, final Font font, final JMenuItem closeItem){
        JMenuItem drawLineItem = new JMenuItem("Draw line");
        drawLineItem.setFont(font);
        modeMenu.add(drawLineItem);
        drawLineItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasLine.setMode(true);
            }
        });

        JMenuItem chooseAreaItem = new JMenuItem("Choose area");
        chooseAreaItem.setFont(font);
        modeMenu.add(chooseAreaItem);
        chooseAreaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasLine.setMode(false);
            }
        });

        modeMenu.addSeparator();

        final JMenuItem graphItem = new JMenuItem("Get graph");
        graphItem.setFont(font);
        modeMenu.add(graphItem);
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

        JMenuItem showDataItem = new JMenuItem("Show data");
        showDataItem.setFont(font);
        modeMenu.add(showDataItem);
        showDataItem.addActionListener(new ActionListener() {
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
    }

    private void createHelpItems(final JMenu helpMenu, final Font font){
    JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(font);
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {   //opening new window with main inf
        public void actionPerformed(ActionEvent e) {
            aboutWindow.setVisible(true);
        }
    });
    }
    //TODO (create methods for creating menu and sub-menu buttons
    JMenuBar getMenuBar() {
        return menuBar;
    }
}
