package main.java.gui;

import main.java.application.Month;

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

    public MainMenuButtons(Month[] months) {
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Item Button Pressed");
                new AddPurchaseGUI(months);
            }
        });
        manageCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Categories Button Pressed");
            }
        });
        manageBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Budget Button Pressed");
            }
        });
        checkSpendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Spending Button Pressed");
            }
        });

        profileSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Profile Button Pressed");
            }
        });

    }
    public JPanel getButtons(){
        return mainMenuButtons;
    }
}