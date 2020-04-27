package main.java.gui;

import main.java.application.YearBudget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtons {

    private JButton addItemButton;
    private JButton manageCategoriesButton;
    private JButton manageBudgetButton;
    private JButton checkSpendingButton;
    private JButton profileSettingsButton;
    private JPanel mainMenuButtons;

    public MainMenuButtons(YearBudget workingYear) {
        /**
         * Action listener to open the add Item menu.
         */

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Item Button Pressed");
                new ManagePurchaseGUI(workingYear.getMonths(), workingYear.getSavedCategories());
            }
        });
        manageCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CategoriesGUI(workingYear);
                System.out.println("Categories Button Pressed");
            }
        });
        manageBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Budget Button Pressed");
                new BudgetGUI(workingYear);
            }
        });
        checkSpendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Spending Button Pressed");
                new CheckSpendingGUI(workingYear);
            }
        });

        profileSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Profile Button Pressed");
            }
        });

    }

    public JPanel getButtons() {
        return mainMenuButtons;
    }

}
