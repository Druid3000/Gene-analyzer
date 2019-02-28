package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.services.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/** Class for showing items for user
 *
 */
class Menu {
    private MainService mainService;
    private CanvasLine canvasLine;
    private GraphWindow graphWindow;
    private TableWindow tableWindow;
    private JMenuBar menuBar;
    private File picture;
    private AboutWindow aboutWindow;


    Menu(AboutWindow aw, TableWindow tw, MainService mc, GraphWindow gw, CanvasLine cl) {
        mainService = mc;
        graphWindow = gw;
        tableWindow = tw;
        canvasLine = cl;
        aboutWindow = aw;

        final Font font = new Font("Verdana", Font.PLAIN, 14);
        menuBar = new JMenuBar();

        final JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
        menuBar.add(fileMenu);
        createFileItems(fileMenu, font);

        final JMenu modeMenu = new JMenu("Mode");
        modeMenu.setFont(font);
        menuBar.add(modeMenu);
        createModeItems(modeMenu, font);

        final JMenu settingsMenu = new JMenu("Settings");
        settingsMenu.setFont(font);
        menuBar.add(settingsMenu);
        createSettingsItems(settingsMenu, font, cl);

        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(font);
        menuBar.add(helpMenu);
        createHelpItems(helpMenu, font);

    }

    /** Creating buttons for work with file
     *
     * @param fileMenu file
     * @param font font
     */
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
                    mainService.setPicture(picture);
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

    /** Creating modes for drawing line, area, graph, showing data
     *
     * @param modeMenu mode
     * @param font font
     */
    private void createModeItems(final JMenu modeMenu, final Font font){
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
            }
        });

        JMenuItem showDataItem = new JMenuItem("Show data");
        showDataItem.setFont(font);
        modeMenu.add(showDataItem);
        showDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableWindow.setVisible(true);
                tableWindow.be = true;
            }
        });
    }

    /** Creating items for help for user
     *
     * @param helpMenu help
     * @param font font
     */
    private void createHelpItems(final JMenu helpMenu, final Font font){
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(font);
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {   //opening about window
        public void actionPerformed(ActionEvent e) {
            aboutWindow.setVisible(true);
        }
    });
    }

    private void createSettingsItems(final JMenu settingsMenu, final Font font, final CanvasLine canvasLine){
        JMenuItem setDelta = new JMenuItem("Set Delta");
        setDelta.setFont(font);
        settingsMenu.add(setDelta);
        setDelta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame();
            }
        });

        settingsMenu.addSeparator();

        JMenuItem chooseBackgroundIntensity = new JMenuItem("Set pixel Background Intensity");
        chooseBackgroundIntensity.setFont(font);
        settingsMenu.add(chooseBackgroundIntensity);
        chooseBackgroundIntensity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasLine.setChooseBackgroundIntensity(true);
            }
        });

        JMenuItem chooseDefaultIntensity = new JMenuItem("Set default Background Intensity");
        chooseDefaultIntensity.setFont(font);
        settingsMenu.add(chooseDefaultIntensity);
        chooseDefaultIntensity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainService.setBackgroundIntensity(255.0);
                canvasLine.setChooseBackgroundIntensity(false);
            }
        });

    }

    /** Testing frame
     *
     */
    private void frame(){
        SliderTestFrame frame = new SliderTestFrame(mainService);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    JMenuBar getMenuBar() {
        return menuBar;
    }
}
