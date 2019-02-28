package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.services.MainService;
import com.epam.gene_analyzer.model.RtModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;


/** Class for user or program interaction with the table model
 * Class - view for displaying table
 *
 */
public class TableWindow extends JFrame {


    private MainService mainService;
    boolean be = false;


    /** Constructor for creating a class with the filling of the first row of the table
     *
     * @param mc main Service
     */
    TableWindow(MainService mc) {
        super("Table of the optical density");
        mainService = mc;

        getContentPane().setLayout(new FlowLayout());
        setBounds(680, 40, 600, 400);
        setResizable(false);

        setIconImage(getToolkit().getImage("src/main/resources/iconTable.gif"));

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        CreateTable();

    }


    /** Method for creating a table.
     * Inclusion of the table in the panel with scrolling.
     */
    private void CreateTable() {

        RtModel tableModel = mainService.getTable();
        JTable resultsTable = new JTable(tableModel);

        final JScrollPane scrollPane = new JScrollPane(resultsTable);

        resultsTable.setPreferredScrollableViewportSize(new Dimension(550, 320));

        getContentPane().add(scrollPane);

        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resultsTable.setRowSelectionAllowed(false);

        resultsTable.setColumnSelectionAllowed(true);

        TableColumnModel tableColumnModel = resultsTable.getColumnModel();

        tableColumnModel.getColumn(0).setPreferredWidth(30);

        tableColumnModel.getColumn(0).setMinWidth(20);
        tableColumnModel.getColumn(1).setMinWidth(75);
        tableColumnModel.getColumn(2).setMinWidth(55);
        tableColumnModel.getColumn(3).setMinWidth(40);
        tableColumnModel.getColumn(4).setMinWidth(75);

        tableColumnModel.getColumn(0).setMaxWidth(50);
        tableColumnModel.getColumn(4).setMaxWidth(100);
        resultsTable.getModel().addTableModelListener(
                new TableModelListener() {
                    public void tableChanged(TableModelEvent evt) {
                        scrollPane.updateUI();
                    }
                });
    }



    /** Method for adding data in table
     *
     */
    private void updateData() {
        mainService.updateData();
    }

    /** Method for deleting data from table
     *
     * @param idRow data
     */
    public void deleteData(int idRow) {
        mainService.transferDeleteData(idRow);
    }


    /** Method for table sorting
     *
     * @param colomn_name column name
     */
    public void sortData(String colomn_name) {
    }

    /** Method for saving information in table
     *
     */
    public void saveData() {
    }
}
