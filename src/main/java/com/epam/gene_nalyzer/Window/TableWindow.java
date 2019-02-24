package com.epam.gene_nalyzer.Window;

import com.epam.gene_nalyzer.Controllers.MainController;
import com.epam.gene_nalyzer.Model.RtModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;


//класс является View в модели MVC и представляет взаимодействие пользователя или программы с моделью Таблицы
public class TableWindow extends JFrame {

    //ПОЛЯ КЛАССА
    //---------------------------------------

    //поле контроллера (для работы с классом модели)
    //private static TableController table_controller;
    private MainController mainController;
    //поле существования данного окна
    protected boolean be = false;


    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор для создания класса без заполнения первой строки таблицы // не используется
    /*public TableWindow(com.epam.gene_nalyzer.Controllers.MainController mc){
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
        RtModel tableModel = mainController.getTable();
        JTable resultsTable = new JTable(tableModel);
        //table_controller = new TableController(tableModel);
        //включение таблицы в состав панели с прокруткой
        final JScrollPane scrollPane = new JScrollPane(resultsTable);
        //установка размера области просмотра для прокрутки
        resultsTable.setPreferredScrollableViewportSize(new Dimension(550, 320));
        //добавление полосы прокрутки с таблицей в главное окно
        getContentPane().add(scrollPane);
        //допускается выбор одного элемента
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //запретить выбор строк
        resultsTable.setRowSelectionAllowed(false);
        //разрешить выбор столбцов
        resultsTable.setColumnSelectionAllowed(true);
        //получение ссылки на модель таблицы
        //tableModel = resultsTable.getModel();
        //получение ссылки на модель столбцов
        TableColumnModel tableColumnModel = resultsTable.getColumnModel();
        //изменение размера первого столбца (по модели)
        tableColumnModel.getColumn(0).setPreferredWidth(30);
        //установка минимальных значений ширины столбцов
        tableColumnModel.getColumn(0).setMinWidth(20);
        tableColumnModel.getColumn(1).setMinWidth(75);
        tableColumnModel.getColumn(2).setMinWidth(55);
        tableColumnModel.getColumn(3).setMinWidth(40);
        tableColumnModel.getColumn(4).setMinWidth(75);
        //установка максимального значения для первого и последнего столбцов
        tableColumnModel.getColumn(0).setMaxWidth(50);
        tableColumnModel.getColumn(4).setMaxWidth(100);
        resultsTable.getModel().addTableModelListener(
                new TableModelListener() {
                    public void tableChanged(TableModelEvent evt) {
                        scrollPane.updateUI();
                    }
                });
    }

    //метод для добавления информации (строки) в таблицу
    private void updateData() {

        /*int num = resultsTable.getRowCount();
        int size = mainController.getAreas().size();
        if (num != size) {
            resultsTable.setModel(mainController.getTable());
        }
        updateData();*/
        mainController.updateData();

    }


    //метод для удаления указанной строки из таблицы (удалит область)
    public void deleteData(int idRow) {
        mainController.transferDeleteData(idRow);
    }

    //метод для сортировки таблицы
    public void sortData(String colomn_name) {
    }

    //метод для сохранения информации (таблицы)
    public void saveData() {
    }

}
