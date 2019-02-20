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



    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор без заполнения первой строки
    public RtModel(){
        super();
    }

    //конструктор с заполнением первой строки
    public RtModel(Double[] data, int number_area, int bi){
        this.data.add(setData(data, number_area, bi));
        numRows++;
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
            case 0: return Byte.class;
            case 1: return Double.class;
            case 2: return Double.class;
            case 3: return Integer.class;
            case 4: return Integer.class;
            default: return Object.class;
        }
    }

    //получение данных из заданной ячейки
    public Object getValueAt(int row, int column) {
        // разные данные для разных стобцов
        return data.get(row)[column];
    }

    //получение входных данных и их преобразование к табличным значениям
    public Object[] setData(Double[] new_data, int number_area, int bi) {
        Object row[] = new Object[5];
        row[0] = number_area;
        row[3] = new_data.length;
        row[4] = bi;
        for (int i=0; i < new_data.length; i++){
            row[2]=+ new_data[i];
        }
        row[1] = (double)row[2]/(int)row[3];
        row[1] = Math.log((int)row[4]/(double)row[1]);
        row[2] = Math.log((int)row[4]/(double)row[2]);
        return row;
    }

    //добавление новой строки
    public void setValueAt(Double[] new_data, int number_area, int bi){
        data.add(setData(new_data, number_area, bi));
        numRows++;
        fireTableDataChanged();
    }

    //удаление строки с заданным индексом
    public void deleteValueAt(int id){
        for (int i=0; i<data.size();i++){
            if ((int)data.get(i)[0] == id){
                data.remove(i);
                numRows--;
                break;
            }
        }
        fireTableDataChanged();
    }
}
