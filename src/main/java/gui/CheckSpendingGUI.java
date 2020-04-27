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
    private JComboBox monthSelectionComboBox;

    public CheckSpendingGUI(YearBudget yearReference) {

        // Displays window with spending for the entire year
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayAllSpendingGUI(yearReference);
            }
        });

        viewMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monthIndex = monthSelectionComboBox.getSelectedIndex() + 1;
                // display info for month
                new MonthlySpendingGUI(yearReference.getMonth(monthIndex));
            }
        });

        setContentPane(CheckSpendingPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
