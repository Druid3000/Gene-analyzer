package Controllers;

import Model.RtModel;

//класс Контроллера для связи модели таблицы и интерфейса пользователя
public class TableController {

    //ПОЛЯ КЛАССА
    //---------------------------------------

    //поле модели таблицы
    private RtModel tm;



    //КОНСТРУКТОРЫ МОДЕЛИ таблицы
    //---------------------------------------

    //конструктор с получением модели
    public TableController(RtModel tm){
        this.tm = tm;
    }



    //МЕТОДЫ КЛАССА
    //----------------------------------------

    //метод для передачи добавляемых данных
    public void TransferAddData(Double[] new_data, int number_area, int bi){
        tm.setValueAt(new_data, number_area, bi);
    }

    //метод для передачи индекса удаляемой ячейки
    public void TransferDeleteData(int id_row){
        tm.deleteValueAt(id_row);
    }


}
