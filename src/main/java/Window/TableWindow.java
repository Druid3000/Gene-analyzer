import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

//класс является View в модели MVC и представляет взаимодействие пользователя или программы с моделью Таблицы
public class TableWindow extends JFrame {

    //ПОЛЯ КЛАССА
    //---------------------------------------

    //поле контроллера (для работы с классом модели)
    private TableController table_controller;
    //поля, содержащее таблицу
    private JTable results_table;
    //поле модели столбца
    private TableColumnModel tcm;



    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор для создания класса без заполнения первой строки таблицы
    public TableWindow(){
        super("Table of the optical density");
        //установка диспетчера компоновки
        getContentPane().setLayout(new FlowLayout());
        setBounds(600, 200, 600,400);
        //установка иконки для окна
        setIconImage(getToolkit().getImage("iconTable.gif"));
        //действие при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //создаем таблицу
        CreateTable();
        //видимость окна
        setVisible(true);
    }

    //конструктор для создания класса с заполнением первой строки таблицы
    public TableWindow(Double[] new_data, int number_area, int bi){
        super("Table of the optical density");
        //установка диспетчера компоновки
        getContentPane().setLayout(new FlowLayout());
        setBounds(600, 200, 600,400);
        //установка иконки для окна
        setIconImage(getToolkit().getImage("iconTable.gif"));
        //действие при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //создаем таблицу
        CreateTable();
        AddData(new_data, number_area, bi);
        setVisible(true);
    }



    //МЕТОДЫ КЛАССА
    //----------------------------------------

    //метод создания таблицы
    private void CreateTable(){
        //создание таблицы
        RtModel tm = new RtModel();
        results_table = new JTable(tm);
        table_controller = new TableController(tm);
        //включение таблицы в состав панели с прокруткой
        JScrollPane scrollPane = new JScrollPane(results_table);
        //установка размера области просмотра для прокрутки
        results_table.setPreferredScrollableViewportSize(new Dimension(550, 320));
        //добавление полосы прокрутки с таблицей в главное окно
        getContentPane().add(scrollPane);
        //допускается выбор одного элемента
        results_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //запретить выбор строк
        results_table.setRowSelectionAllowed(false);
        //разрешить выбор столбцов
        results_table.setColumnSelectionAllowed(true);
        //получение ссылки на модель таблицы
        //tm = results_table.getModel();
        //получение ссылки на модель столбцов
        tcm = results_table.getColumnModel();
        //изменение размера первого столбца (по модели)
        tcm.getColumn(0).setPreferredWidth(30);
        //установка минимальных значений ширины столбцов
        tcm.getColumn(0).setMinWidth(20);
        tcm.getColumn(1).setMinWidth(75);
        tcm.getColumn(2).setMinWidth(55);
        tcm.getColumn(3).setMinWidth(40);
        tcm.getColumn(4).setMinWidth(75);
        //установка максимального значения для первого и последнего столбцов
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(4).setMaxWidth(100);

    }

    //метод для добавления информации (строки) в таблицу
    public void AddData(Double[] new_data, int number_area, int bi){
        table_controller.TransferAddData(new_data, number_area, bi);
    }

    //метод для удаления указанной строки из таблицы
    public void DeleteData(int id_row){
        table_controller.TransferDeleteData(id_row);
    }

    //метод для сортировки таблицы
    public void SortData(String colomn_name){}

    //метод для сохранения информации (таблицы)
    public  void SaveData(){}

}
