package Model;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RtModel extends AbstractTableModel/*, DefaultTableModel*/ {

    //ПОЛЯ МОДЕЛИ таблицы
    //---------------------------------------

    //поле массива с названием столбцов таблицы
    private static String[] headings = {"№", "Average OD", "Total OD", "Area", "B.Intensity"};
    //поле массива с данными для таблицы
    private ArrayList<Object[]> data = new ArrayList<Object[]>();
    //поле с количеством строк
    private byte numRows = 0;
    //поле номера строки в таблице//отключил, т.к. используется номер области
    private byte number_area = 1;


    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор без заполнения первой строки
    public RtModel() {
        super();
    }

    //конструктор с заполнением первой строки
    /*public RtModel(Double[] data, double bi) {
        this.data.add(setData(data, bi));
        numRows++;
        number_area++;
    }*/


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

    //получение входных данных и их преобразование к табличным значениям
    public Object[] setData(int id, Double[] new_data, double bi) {
        byte r0 = (byte) id;
        double r1 = 0, r2 = 0, r4;
        int r3;
        //находим Average OD
        for (int i = 0; i < new_data.length; i++) {
            r1 += new_data[i];
        }
        r1 = Math.log10(bi / (r1 / new_data.length));
        //находим Total OD
        for (int i = 0; i < new_data.length; i++) {
            r2 += Math.log10(bi / new_data[i]);
        }
        r3 = new_data.length;
        r4 = bi;

        Object[] row = {r0, r1, r2, r3, r4};
        return row;
    }

    //добавление новой строки
    public void setValueAt(int id, Double[] new_data, double bi) {
        data.add(setData(id, new_data, bi));
        numRows++;
        number_area++;
        //fireTableDataChanged();
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
    public void removeRows() {
        if (data.size() > 0)
            data.clear();
        /*
        for (int i = 0; i < data.size(); i++) {

            System.out.println("data "+(data.get(i-1)[0].toString()));
            System.out.println("int "+Integer.toString(i));
            //if ((data.get(i)[0].toString()) == Integer.toString(i)+1) {
                data.remove(i);
                numRows--;
                //break;
                //}
            //}
        }*/
        number_area = (byte) ((int) number_area - (int) numRows);
        numRows = 0;
        //number_area=
    }
}
