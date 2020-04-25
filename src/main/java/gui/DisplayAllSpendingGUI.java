import main.java.application.YearBudget;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;
public class DisplayAllSpendingGUI extends JFrame{

    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JLabel titleLabel;
    private JTextPane totalSpendingPane;

    public DisplayAllSpendingGUI(YearBudget yearReference){
        super("All Spending Summary");
        System.out.println("INSIDE DisplayAllSpendingGUI");

        // display total spending
        totalSpendingPane.setText("Total Spending: " + yearReference.getYearlySpending().toString());
        // create and display table
        table.setModel(getCategoryTable(yearReference));

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Accepts hashMap<String, Double> and returns it as a 2D array
     * @return categorySpendingArr - array with categories as col 0 and total price as col 1
     */

    public String[][] hashMapTo2DArray(HashMap<String, Double> categorySpendingHashMap){
        String[][] categorySpendingArr = new String[categorySpendingHashMap.size()][2];
        int row = 0;

        for(Map.Entry<String, Double> categorySpendingEntry : categorySpendingHashMap.entrySet()){
            categorySpendingArr[row][0] = categorySpendingEntry.getKey();
            categorySpendingArr[row][1] = categorySpendingEntry.getValue().toString();
            row++;
        }
        return categorySpendingArr;
    }

    public TableModel getCategoryTable(YearBudget yearReference){

        HashMap<String, Double> yearCategorySpendingHash = yearReference.getYearlyCategorySpending();
        String[][] yearCategorySpendingStrArr =    hashMapTo2DArray(yearCategorySpendingHash);//new String[yearCategorySpending.size()][2];

        TableModel dataModel = new

                AbstractTableModel() {

                    @Override
                    public String getColumnName(int column) {

                        if(column == 0){ return "Category";}
                        else{ return "Total Spending";}
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