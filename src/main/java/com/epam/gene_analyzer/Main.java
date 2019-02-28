package com.epam.gene_analyzer;

import com.epam.gene_analyzer.windows.MainWindow;

/** Class for running tha project
 *
 */
public class Main {
    /** Calling main window
     *
     * @param args args
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.showWindow();
    }
}