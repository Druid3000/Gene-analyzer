package com.epam.gene_analyzer.windows;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SliderTestFrame extends JFrame {
    SliderTestFrame()
    {
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
                delta = source.getValue();
                setDelta(delta);
            }
        };

        // Добавление простого регулятора.

        JSlider slider = new JSlider(0,100,10);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        addSlider(slider, "Set Delta value");
        // Добавление регулятора с числовыми метками.
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        add(sliderPanel, BorderLayout.CENTER);

    }
    /*
     * Добавление регулятора в панель и связывание его со слушателем.
     * @param s Регулятор
     * @param description Описание регулятора
     */
    public void addSlider(JSlider s, String description)
    {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        sliderPanel.add(panel);
        JFrame.setDefaultLookAndFeelDecorated(true);
    }
    public void setDelta(int delta){this.delta = delta;}
    public int getDelta(){return delta;}

    private int delta;
    private JPanel sliderPanel;
    private ChangeListener listener;
}
