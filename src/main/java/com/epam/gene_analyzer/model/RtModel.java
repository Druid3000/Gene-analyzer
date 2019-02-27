package com.epam.gene_analyzer.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RtModel extends AbstractTableModel {

    //ПОЛЯ МОДЕЛИ таблицы
    //---------------------------------------

    //поле массива с названием столбцов таблицы
    private static String[] headings = {"№", "Average OD", "Total OD", "Area", "B.Intensity"};
    //поле массива с данными для таблицы
    private ArrayList<Object[]> data = new ArrayList<Object[]>();
    //поле с количеством строк
    private byte numRows = 0;
    //поле номера строки в таблице//отключил, т.к. используется номер области
    private byte numberArea = 1;


    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор без заполнения первой строки
    public RtModel() {
        super();
    }

    //МЕТОДЫ МОДЕЛИ таблицы
    //---------------------------------------

    //вывод количества строк
    public int getRowCount() {
        return numRows;
    }

    //вывод количества столбцов
    public int getColumnCount() {
        return 5;
    }

    //вывод имени столбца по заданному индексу
    public String getColumnName(int column) {
        return headings[column];
    }

    // тип данных, хранимых в столбце
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

    //получение данных из заданной ячейки
    public Object getValueAt(int row, int column) {
        // разные данные для разных стобцов
        return data.get(row)[column];
    }

    //добавление новой строки
    public void setValueAt(int id, Double[] newData, double bi) {
        data.add(setData(id, newData, bi));
        numRows++;
        numberArea++;
    }

    public void removeRows() {
        if (data.size() > 0) data.clear();
        numberArea = (byte) ((int) numberArea - (int) numRows);
        numRows = 0;
    }

    //получение входных данных и их преобразование к табличным значениям
    private Object[] setData(int id, Double[] newData, double bi) {
        byte r0 = (byte) id;
        double r1 = 0, r2 = 0, r4;
        int r3;
        //находим Average OD
        for (int i = 0; i < newData.length; i++) {
            r1 += newData[i];
        }
        r1 = Math.log10(bi / (r1 / newData.length));
        if (r1 < 0) r1 = 0;
        //находим Total OD
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

    //удаление строки с заданным индексом
    /*private void deleteValueAt(int id){
        for (int i=0; i<data.size();i++){
            if ((data.get(i)[0].toString()) == Integer.toString(id)){
                data.remove(i);
                numRows--;
                break;
            }
        }
        fireTableDataChanged();
    }*/

}
