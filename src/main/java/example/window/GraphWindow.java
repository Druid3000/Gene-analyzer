package example.window;

import example.model.Line;

class GraphWindow {
    void drawLine(Line line){
        //Вместо draw я просто выведу в консоль координаты.
        System.out.println(line.getPoint1() + " " + line.getPoint2());
    }
}
