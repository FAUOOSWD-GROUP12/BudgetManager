package main.java.gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import main.java.application.Item;
import main.java.application.Month;
import java.util.ArrayList;
import javax.swing.*;

public class MonthlySpendingGUI extends JFrame{

    public MonthlySpendingGUI(Month thisMonth){
        super(thisMonth.getMonthName() + " Spending Summary");
        monthNameLabel.setText(thisMonth.getMonthName() + " Spending Summary: ");
        ArrayList<Item> monthlyItems = thisMonth.getAllItems();


        // creates table for all Items purchased this month
        itemSpendingTable.setModel(getItemTable(monthlyItems));





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


    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel monthNameLabel;
    private JPanel bottomPanel;
    private JScrollPane categorySpendingScrollPane;
    private JTable categorySpendingTable;
    private JTable itemSpendingTable;
    private JScrollPane itemSpendingScrollPane;
}
