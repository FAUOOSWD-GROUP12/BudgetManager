package main.java.gui;

import main.java.application.YearBudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckSpendingGUI extends JFrame {

    private JPanel CheckSpendingPanel;
    private JButton allButton;
    private JButton viewMonthButton;
    private JComboBox comboBox1;

    public CheckSpendingGUI(YearBudget yearReference) {

        // Displays window with spending for the entire year
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayAllSpendingGUI(yearReference);
            }
        });
        setContentPane(CheckSpendingPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
