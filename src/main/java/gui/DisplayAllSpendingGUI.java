package main.java.gui;

import main.java.application.YearBudget;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DisplayAllSpendingGUI extends JFrame {

    private JTable categorySpendingTable;
    private JScrollPane categorySpendingScrollPane;
    private JPanel panel;
    private JLabel titleLabel;
    private JTextPane totalSpendingPane;
    private JTable budgetTable;
    private JScrollPane budgetScrollPane;

    public DisplayAllSpendingGUI(YearBudget yearReference) {
        super("All Spending Summary");

        // create and display budget table
        budgetTable.setModel(getBudgetTable(yearReference));
        // create and display category spending table
        categorySpendingTable.setModel(getCategoryTable(yearReference));

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Accepts hashMap<String, Double> and returns it as a 2D array
     *
     * @return categorySpendingArr - array with categories as col 0 and total price as col 1
     */

    public String[][] hashMapTo2DArray(HashMap<String, Double> categorySpendingHashMap) {
        String[][] categorySpendingArr = new String[categorySpendingHashMap.size()][2];
        int row = 0;

        for (Map.Entry<String, Double> categorySpendingEntry : categorySpendingHashMap.entrySet()) {
            categorySpendingArr[row][0] = categorySpendingEntry.getKey();
            categorySpendingArr[row][1] = categorySpendingEntry.getValue().toString();
            row++;
        }
        return categorySpendingArr;
    }

    /**
     * Gets hashMap from yearReference, converts it to a 2D array, and uses 2D array to create table
     * @param yearReference
     * @return table with All categories and corresponding spending
     */
    public TableModel getCategoryTable(YearBudget yearReference) {

        HashMap<String, Double> yearCategorySpendingHash = yearReference.getYearlyCategorySpending();
        String[][] yearCategorySpendingStrArr = hashMapTo2DArray(yearCategorySpendingHash);//new String[yearCategorySpending.size()][2];

        TableModel dataModel = new

                AbstractTableModel() {

                    @Override
                    public String getColumnName(int column) {

                        if (column == 0) {
                            return "Category";
                        } else {
                            return "Total Spending";
                        }
                    }

                    public int getColumnCount() {
                        return 2;
                    }

                    public int getRowCount() {
                        return yearCategorySpendingStrArr.length;
                    }

                    public Object getValueAt(int row, int col) {
                        return yearCategorySpendingStrArr[row][col];
                    }
                };

        return dataModel;
    }

    public TableModel getBudgetTable(YearBudget yearReference){

        Double totalSpending = yearReference.getYearlySpending();
        Double budget = yearReference.getYearlyBudget();
        String[][] budgetArr = new String[1][4];

        budgetArr[0][0] = totalSpending.toString();
        budgetArr[0][1] = budget.toString();
        if(budget == 0.0){
            budgetArr[0][2] = "Budget Not Set";
            budgetArr[0][3] = "N/A";
        }
        else if(totalSpending > budget){    // if budget is not met
            budgetArr[0][2] =  "" + (budget - totalSpending);
            budgetArr[0][3] = "No";
        }
        else if(totalSpending <= budget){ // if budget is met
            budgetArr[0][2] = "+ " + (budget - totalSpending);
            budgetArr[0][3] = "Yes";
        }

        TableModel dataModel = new
                AbstractTableModel() {
                    @Override
                    public int getRowCount() {
                        return 1;
                    }

                    @Override
                    public int getColumnCount() {
                        return 4;
                    }
                    @Override
                    public String getColumnName(int column) {

                        if (column == 0) {
                            return "Total Spent";
                        }
                        if (column == 1) {
                            return "Budget";
                        }
                        if (column == 2) {
                            return "Difference";
                        }
                        return "Budget Met?";

                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        return budgetArr[0][columnIndex];
                    }
                };

        return dataModel;
    }

}