package com.epam.gene_analyzer.windows;

import javax.swing.*;
import java.awt.*;

/** Windows class based on JFrame for creating window
 *
 */
class AboutWindow extends JFrame {

    AboutWindow() {
        setTitle("About Program");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(600, 160, 400, 350);
        setIconImage(getToolkit().getImage("src/main/resources/iconMain.gif"));
        JFrame.setDefaultLookAndFeelDecorated(true);
        createGUI();
    }

    /** Creating GUI for window with using html
     *
     */
    private void createGUI(){

        JPanel htmlPanel = new JPanel();
        htmlPanel.setBorder(BorderFactory.createTitledBorder(""));

        String text = "<html><h2>What is Gene Analyzer?</h2>" +
                "<font face=’verdana’ size = 4>" +
                " Gene Analyzer is a application <br>" +
                " for the analysis of the image of protein structures. <br>" +
                " The main function is to determinate the optical density <br>" +
                " within a given area or on a straight line. The task to<br>" +
                " create this project was taken from the real need of <br>" +
                " scientists from the SRI of Physiology. <br>" +
                "<br><br><br>" +
                " 2019 ITMO&EPAM.</html>";

        Font font = new Font(null, Font.PLAIN, 10);

        JLabel htmlLabel = new JLabel();
        htmlLabel.setText(text);
        htmlLabel.setFont(font);
        htmlPanel.add(htmlLabel);

        add(htmlPanel);
    }
}



