package gui;

import application.YearBudget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel that presents user with option to view spending summary of full year or a specific month
 */
public class CheckSpendingGUI {

    /**
     * Initializer that creates buttons and dropdown to view spending summaries.
     * @param yearReference used to provide spending data
     */
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
            // Displays info for specific month
            public void actionPerformed(ActionEvent e) {
                int monthIndex = monthSelectionComboBox.getSelectedIndex() + 1;
                // display info for month
                new MonthlySpendingGUI(yearReference.getMonth(monthIndex), yearReference.getMonthlyBudget());
            }
        });
    }

    /**
     * returns CheckSpendingPanel
     * @return spending panel
     */
    public JPanel getCheckSpendingPanel(){
        return CheckSpendingPanel;
    }

    private JPanel CheckSpendingPanel;
    private JButton allButton;
    private JButton viewMonthButton;
    private JComboBox monthSelectionComboBox;
}
