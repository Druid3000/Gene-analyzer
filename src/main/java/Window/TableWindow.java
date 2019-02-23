package Window;

import Controllers.MainController;
import Controllers.TableController;
import Model.Pixel;
import Model.RtModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.SwingEventMonitor.addTableModelListener;


//класс является View в модели MVC и представляет взаимодействие пользователя или программы с моделью Таблицы
public class TableWindow extends JFrame {

    //ПОЛЯ КЛАССА
    //---------------------------------------

    //поле контроллера (для работы с классом модели)
    //private static TableController table_controller;
    //поля, содержащее таблицу
    private JTable results_table;
    //поле модели столбца
    private TableColumnModel tcm;
    private MainController mainController;
    //поле существования данного окна
    public boolean be = false;


    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор для создания класса без заполнения первой строки таблицы // не используется
    /*public TableWindow(MainController mc){
        super("Table of the optical density");
        mainController = mc;
        //установка диспетчера компоновки
        getContentPane().setLayout(new FlowLayout());
        setBounds(600, 200, 600,400);
        //установка иконки для окна
        setIconImage(getToolkit().getImage("iconTable.gif"));
        //действие при закрытии окна
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        //создаем таблицу
        CreateTable();
        //видимость окна
        updateData();
    }*/

    //конструктор для создания класса с заполнением первой строки таблицы
    public TableWindow(MainController mc) {
        super("Table of the optical density");
        mainController = mc;
        //установка диспетчера компоновки
        getContentPane().setLayout(new FlowLayout());
        setBounds(600, 200, 600, 400);
        //установка иконки для окна
        setIconImage(getToolkit().getImage("iconTable.gif"));
        //действие при закрытии окна
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        //создаем таблицу
        CreateTable();
        //updateData();
    }


    //МЕТОДЫ КЛАССА
    //----------------------------------------

    //метод создания таблицы
    private void CreateTable() {
        //создание таблицы
        RtModel tm = mainController.getTable();
        results_table = new JTable(tm);
        //table_controller = new TableController(tm);
        //включение таблицы в состав панели с прокруткой
        final JScrollPane scrollPane = new JScrollPane(results_table);
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
        results_table.getModel().addTableModelListener(
                new TableModelListener() {
                    public void tableChanged(TableModelEvent evt) {
                        scrollPane.updateUI();
                    }
                });
    }

    //метод для добавления информации (строки) в таблицу
    private void updateData() {

        /*int num = results_table.getRowCount();
        int size = mainController.getAreas().size();
        if (num != size) {
            results_table.setModel(mainController.getTable());
        }
        updateData();*/
        mainController.updateData();

    }


    //метод для удаления указанной строки из таблицы (удалит область)
    public void DeleteData(int id_row) {
        mainController.TransferDeleteData(id_row);
    }

    //метод для сортировки таблицы
    public void SortData(String colomn_name) {
    }

    //метод для сохранения информации (таблицы)
    public void SaveData() {
    }

}
