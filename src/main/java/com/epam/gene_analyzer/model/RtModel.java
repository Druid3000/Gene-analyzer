package com.epam.gene_analyzer.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/** Model class for creating Table model.
 * Use names of columns, array of table data, number of rows, row number in table, area number.
 * Add and remove rows.
 * Get new data for adding new rows to table.
 */
public class RtModel extends AbstractTableModel {


    private static String[] headings = {"№", "Average OD", "Total OD", "Area", "B.Intensity"};
    private ArrayList<Object[]> data = new ArrayList<Object[]>();
    private byte numRows = 0;
    private byte numberArea = 1;


    /** Constructor without filling the first line
     *
     */
    public RtModel() {
        super();
    }


    /** Method for getting number of rows
     *
     * @return number of rows
     */
    public int getRowCount() {
        return numRows;
    }

    /** Method for getting number of columns
     *
     * @return number of columns
     */
    public int getColumnCount() {
        return 5;
    }


    /** Method for getting name of column with index
     *
     * @param column column number
     * @return name of column
     */
    public String getColumnName(int column) {
        return headings[column];
    }

    /** Method for getting data type from of column
     *
     * @param column column number
     * @return data type
     */
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return Byte.class;
            case 1:
                return Double.class;
            case 2:
                return Double.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    /** Method for getting data from cell of table from different columns
     *
     * @param row row number
     * @param column column number
     * @return data
     */
    public Object getValueAt(int row, int column) {
        return data.get(row)[column];
    }


    /** Method for adding new row
     *
     * @param id of row
     * @param newData data
     * @param bi arg
     */
    public void setValueAt(int id, Double[] newData, double bi) {
        data.add(setData(id, newData, bi));
        numRows++;
        numberArea++;
    }

    /** Method for removing rows
     *
     */
    public void removeRows() {
        if (data.size() > 0) data.clear();
        numberArea = (byte) ((int) numberArea - (int) numRows);
        numRows = 0;
    }

    /** Method for getting new data and transforming data for table values
     *
     * @param id of data
     * @param newData data
     * @param bi arg
     * @return row with new data
     */
    private Object[] setData(int id, Double[] newData, double bi) {
        byte r0 = (byte) id;
        double r1 = 0, r2 = 0, r4;
        int r3;

        for (int i = 0; i < newData.length; i++) {
            r1 += newData[i];
        }
        r1 = Math.log10(bi / (r1 / newData.length));
        if (r1 < 0) r1 = 0;

        for (int i = 0; i < newData.length; i++) {
            r2 += Math.log10(bi / newData[i]);
        }
        if (r2 < 0) r2 = 0;
        r3 = newData.length;
        r4 = bi;
        if (r4 < 0.001) r4 = 0;


        Object[] row = {r0, r1, r2, r3, r4};
        return row;
    }
}
