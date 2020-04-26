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
        JFrame frame = new JFrame();
        frame.setContentPane(categoryManagerGUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String columnName = "Categories";

        categoryTable.setModel(getCategoryTable(year));

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year.addSavedCategory(categoryTextField.getText());
                categoryTable.setModel(getCategoryTable(year));
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year.removeSavedCategory((String) categoryTable.getValueAt(categoryTable.getSelectedRow(),
                        categoryTable.getSelectedColumn()));
                categoryTable.setModel(getCategoryTable(year));
            }
        });
    }

    private JPanel categoryManagerGUI;
    private JButton cancelButton;
    private JTextField categoryTextField;
    private JLabel categoryJLabel;
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

}
