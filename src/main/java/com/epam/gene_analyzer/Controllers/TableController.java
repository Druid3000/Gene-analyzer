package com.epam.gene_analyzer.Controllers;

import com.epam.gene_analyzer.Model.RtModel;

//класс Контроллера для связи модели таблицы и интерфейса пользователя
public class TableController {

    //ПОЛЯ КЛАССА
    //---------------------------------------

    //поле модели таблицы
    private RtModel rtModel;


    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор с получением модели
    public TableController(RtModel rtModel) {
        this.rtModel = rtModel;
    }


    //МЕТОДЫ КЛАССА
    //----------------------------------------

    //метод для передачи добавляемых данных
    /*public void transferAddData(Double[] new_data, double bi){
        rtModel.setValueAt(new_data, bi);
    }
    */
    //метод для передачи индекса удаляемой ячейки
    public void transferDeleteData(int idRow) {
        //rtModel.deleteValueAt(idRow);
    }


}
