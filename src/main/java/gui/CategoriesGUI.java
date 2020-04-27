package main.java.gui;

import main.java.application.YearBudget;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CategoriesGUI {
    public CategoriesGUI(YearBudget year) {

        String columnName = "Categories";

        categoryTable.setModel(getCategoryTable(year));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entry = categoryTextField.getText();
                if (entry.equals("")){
                    errorLabel.setText("The category is empty!");
                }else{
                    errorLabel.setText("");
                    year.addSavedCategory(categoryTextField.getText());
                    categoryTable.setModel(getCategoryTable(year));
                    categoryTextField.setText("");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    errorLabel.setText("");
                    year.removeSavedCategory((String) categoryTable.getValueAt(categoryTable.getSelectedRow(),
                            categoryTable.getSelectedColumn()));
                    categoryTable.setModel(getCategoryTable(year));
                } catch (ArrayIndexOutOfBoundsException ex){
                    errorLabel.setText("You must select a category to remove it.");
                }
            }
        });
    }

    private JPanel categoryManagerGUI;
    private JTextField categoryTextField;
    private JLabel errorLabel;
    private JButton addButton;
    private JButton removeButton;
    private JTable categoryTable;

    public TableModel getCategoryTable(YearBudget yearReference) {

        ArrayList<String> yearCategorySpendingArrayList = yearReference.getSavedCategories();

        return new

                AbstractTableModel() {

                    @Override
                    public String getColumnName(int column) {
                        return "Category";
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        return yearCategorySpendingArrayList.get(rowIndex);
                    }

                    public int getColumnCount() {
                        return 1;
                    }

                    public int getRowCount() {
                        return yearCategorySpendingArrayList.size();
                    }
                };
    }
    public JPanel getCategoryManagerGUI(){
        return categoryManagerGUI;
    }
}
