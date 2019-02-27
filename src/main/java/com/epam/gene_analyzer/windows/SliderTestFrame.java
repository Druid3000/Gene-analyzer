package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.controllers.MainController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SliderTestFrame extends JFrame {
    private double delta;
    private JPanel sliderPanel;
    private ChangeListener listener;
    private MainController mainController;
    SliderTestFrame(MainController mainController)
    {
        this.mainController=mainController;
        setTitle("Change Delta Value");
        setSize(350, 150);
        JFrame.setDefaultLookAndFeelDecorated(true);
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Общий слушатель для всех регуляторов.
        listener = new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
                // Обновление поля редактирования при
                // изменении значения регулятора.
                JSlider source = (JSlider) event.getSource();
                delta = (double) source.getValue() / 100;
                setDelta(delta);
            }
        };

        // Добавление простого регулятора.

        JSlider slider = new JSlider(0,100);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        addSlider(slider, "Delta value (%)");
        // Добавление регулятора с числовыми метками.
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        add(sliderPanel, BorderLayout.CENTER);


        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
        sliderPanel.add(buttonOK, BorderLayout.CENTER);

    }
    /*
     * Добавление регулятора в панель и связывание его со слушателем.
     * @param s Регулятор
     * @param description Описание регулятора
     */

    private void addSlider(JSlider s, String description)
    {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        sliderPanel.add(panel);
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    private void setDelta(double delta) {
        mainController.setDelta(delta);
    }

}
