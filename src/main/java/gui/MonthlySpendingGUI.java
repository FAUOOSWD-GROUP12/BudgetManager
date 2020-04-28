package main.java.gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import main.java.application.Item;
import main.java.application.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Panel that displays information about user's spending for a specific month
 */
public class MonthlySpendingGUI extends JFrame{

    /**
     * Initializer that creates three tables: item table, budget table, and category spending table.
     * @param thisMonth used to get info about current month
     * @param budget monthly budget
     */
    public MonthlySpendingGUI(Month thisMonth, Double budget){
        super(thisMonth.getMonthName() + " Spending Summary");
        monthNameLabel.setText(thisMonth.getMonthName() + " Spending Summary: ");
        ArrayList<Item> monthlyItems = thisMonth.getAllItems();

        // creates table for all Items purchased this month
        itemSpendingTable.setModel(getItemTable(monthlyItems));
        categorySpendingTable.setModel(getCategoryTable(thisMonth));
        budgetTable.setModel(getBudgetTable(thisMonth,budget));

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Creates and returns a table with breakdown of all Items (Category, Item, Price) for the month
     * @param monthlyItems ArrayList containing all items for the month
     * @return a TableModel with Category,Item,Price breakdown of all monthly Items
     */
    public TableModel getItemTable(ArrayList<Item> monthlyItems){

        TableModel dataModel= new

                AbstractTableModel() {
            @Override
            public int getRowCount() {
                return monthlyItems.size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                if(column == 0){return "Category";}
                if(column == 1){return "Item";}
                return "Price";
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex == 0){ return monthlyItems.get(rowIndex).getCategory();}
                if(columnIndex == 1){ return monthlyItems.get(rowIndex).getName();}
                return monthlyItems.get(rowIndex).getPrice();
            }
        };
        return dataModel;
    }

    /**
     * Gets hashMap from thisMonth, converts it to a 2D array, and uses 2D array to create table
     * @param thisMonth the current month items will be used from
     * @return Table containing categories and totals for each category
     */
    public TableModel getCategoryTable(Month thisMonth){

        HashMap<String, Double> monthlyCategorySpending = new HashMap<>();
        thisMonth.getMonthlyCategorySpending(monthlyCategorySpending);
        String[][] categorySpendingStrArr = hashMapTo2DArray(monthlyCategorySpending);


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
                        return categorySpendingStrArr.length;
                    }

                    public Object getValueAt(int row, int col) {
                        return categorySpendingStrArr[row][col];
                    }
        };
        return dataModel;
    }

    /**
     * Creates a table comparing budget and total spendings
     * @param thisMonth
     * @return
     */
    public TableModel getBudgetTable(Month thisMonth, Double budget){

        Double totalSpending = thisMonth.getMonthlySpending();
        String[][] budgetArr = new String[1][4];
        budgetArr[0][0] = totalSpending.toString();
        budgetArr[0][1] = budget.toString();
        if(budget == 0.0){
            budgetArr[0][2] = "Budget Not Set";
            budgetArr[0][3] = "N/A";
        }
        else if(totalSpending > budget){    // if budget is not met
            budgetArr[0][2] = "" +  (budget - totalSpending);
            budgetArr[0][3] = "No";
        }
        else if(totalSpending <= budget){
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

    /**
     * Coverts HashMap containing category:spending pairs to a 2D array to be used by a TableModel object
     * @param categorySpendingHashMap contains category:spending
     * @return 2D array
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

    private JPanel mainPanel;
    private JPanel middlePanel;
    private JLabel monthNameLabel;
    private JPanel bottomPanel;
    private JScrollPane categorySpendingScrollPane;
    private JTable categorySpendingTable;
    private JTable itemSpendingTable;
    private JScrollPane itemSpendingScrollPane;
    private JTable budgetTable;
    private JPanel topPanel;
    private JScrollPane budgetScrollPane;
}
