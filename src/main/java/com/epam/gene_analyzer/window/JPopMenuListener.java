package com.epam.gene_analyzer.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPopMenuListener implements ActionListener {
    int id;

    JPopMenuListener(int i) {
        this.id = i;
    }

    //Этот метод должен быть, так как без него не работает. Вопросы к Максиму
    public void actionPerformed(ActionEvent e) {

    }
}
