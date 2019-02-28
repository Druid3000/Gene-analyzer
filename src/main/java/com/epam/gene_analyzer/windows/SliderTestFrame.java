package com.epam.gene_analyzer.windows;

import com.epam.gene_analyzer.services.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Class for testing slider frame
 * Use general listener for all regulators
 */
public class SliderTestFrame extends JFrame {
    private double delta;
    private JPanel sliderPanel;
    private ChangeListener listener;
    private MainService mainService;
    SliderTestFrame(MainService mainService)
    {
        this.mainService = mainService;
        setTitle("Change Delta Value");
        setSize(350, 150);
        JFrame.setDefaultLookAndFeelDecorated(true);
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        listener = new ChangeListener()
        {
            /** Updating the edit field when changing the value of the service
             * Adding a simple regulator
             * Adding a service with numeric labels
             *
             * @param event e
             */
            public void stateChanged(ChangeEvent event)
            {

                JSlider source = (JSlider) event.getSource();
                delta = (double) source.getValue() / 100;
                setDelta(delta);
            }
        };


        JSlider slider = new JSlider(0,100);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        addSlider(slider, "Delta value (%)");

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

    /** Adding a regulator to the panel and linking it with the listene
     *
     * @param s Regulator
     * @param description Regulator description
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

    /** Setting delta
     *
     * @param delta delta
     */
    private void setDelta(double delta) {
        mainService.setDelta(delta);
    }

}
