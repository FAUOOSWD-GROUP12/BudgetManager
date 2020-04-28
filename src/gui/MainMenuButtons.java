package gui;

import application.YearBudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtons {

    private JButton addItemButton;
    private JButton manageCategoriesButton;
    private JButton manageBudgetButton;
    private JButton checkSpendingButton;
    private JPanel mainMenuButtons;
    private JPanel editorField;


    public MainMenuButtons(YearBudget workingYear) {
        /**
         * Action listener to open the add Item menu.
         */
        CardLayout card = new CardLayout();
        editorField.setLayout(card);
        editorField.add(new ManagePurchaseGUI(workingYear.getMonths(), workingYear.getSavedCategories()).getPurchasesGUI(), "EditPurchases");
        card.show(editorField, "EditPurchases");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorField.add(new ManagePurchaseGUI(workingYear.getMonths(), workingYear.getSavedCategories()).getPurchasesGUI(), "EditPurchases");
                card.show(editorField, "EditPurchases");
                //new ManagePurchaseGUI(workingYear.getMonths(), workingYear.getSavedCategories());

            }
        });
        manageCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorField.add(new CategoriesGUI(workingYear).getCategoryManagerGUI(), "EditCategories");
                card.show(editorField, "EditCategories");

                //new CategoriesGUI(workingYear);
            }
        });
        manageBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorField.add(new BudgetGUI(workingYear).getMainPanel(), "EditBudget");
                card.show(editorField, "EditBudget");
            }
        });
        checkSpendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorField.add(new CheckSpendingGUI(workingYear).getCheckSpendingPanel(), "CheckSpending");
                card.show(editorField, "CheckSpending");
            }
        });
    }

    public JPanel getButtons() {
        return mainMenuButtons;
    }
}
