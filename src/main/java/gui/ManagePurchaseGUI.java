package main.java.gui;

import main.java.application.Item;
import main.java.application.Month;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;



public class ManagePurchaseGUI {

    public ManagePurchaseGUI(Month[] months, ArrayList<String> categories) {

        ComboBoxModel monthModel = new DefaultComboBoxModel(monthComboArray);
        monthComboBox.setModel(monthModel);

        ComboBoxModel categoryModel = new DefaultComboBoxModel(categories.toArray());
        categoryComboBox.setModel(categoryModel);

        monthComboBox.setSelectedIndex(0);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String itemName = itemPurchasedTextField.getText();
                    double itemPrice = Double.parseDouble(itemPriceTextField.getText());
                    String categorySelected = (String) categoryComboBox.getSelectedItem();
                    int monthSelected = monthComboBox.getSelectedIndex() + 1;
                    int daySelected = dayComboBox.getSelectedIndex() + 1;
                    if (categorySelected == null){
                        errorReport.setText("You must create or use a category!");
                    } else if(daySelected == 0){
                        errorReport.setText("Day Selected is not a Day!");
                    } else if(itemName.equals("")){
                        errorReport.setText("Item has to have a name!");
                    }else{
                        months[monthSelected].addItemToDay(categorySelected, new Item(categorySelected, itemName, itemPrice), daySelected);
                        itemPurchasedTextField.setText("");
                        itemPriceTextField.setText("");
                        updateTable(months[monthSelected],daySelected);
                    }
                } catch (NullPointerException ex){
                    errorReport.setText("You left something empty!");
                } catch (NumberFormatException ex){
                    errorReport.setText("The price is not a number.");
                }
            }
        });

        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = monthComboBox.getSelectedIndex() + 1;
                int days = months[index].getDaysInMonth();
                int i = 1;
                ArrayList<Integer> eachDay = new ArrayList();
                while (i <= days) {
                    eachDay.add(i);
                    i++;
                }
                ComboBoxModel dayModel = new DefaultComboBoxModel(eachDay.toArray());
                dayComboBox.setModel(dayModel);
                dayComboBox.setSelectedIndex(0);
                updateTable(months[index],dayComboBox.getSelectedIndex() + 1);

            }
        });


        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monthIndex = monthComboBox.getSelectedIndex() + 1;
                int dayIndex = dayComboBox.getSelectedIndex() + 1;
                Item itemSelected = (Item) itemsInDayTable.getValueAt(itemsInDayTable.getSelectedRow(), 0);
                String key = itemSelected.getCategory();
                months[monthIndex].removeItemInDay(key, dayIndex, itemSelected);
                updateTable(months[monthIndex], dayIndex);
            }
        });

        dayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int monthSelected = monthComboBox.getSelectedIndex() + 1;
                    int daySelected = dayComboBox.getSelectedIndex() + 1;
                    updateTable(months[monthSelected],daySelected);
            }
        });
        totalSpending.addComponentListener(new ComponentAdapter() {
        });
    }

    public TableModel getItemTable(Month month, int day) {

            ArrayList<Item> dayItems = month.getItemsFromDay(day);
            return new AbstractTableModel() {
                @Override
                public int getRowCount() {
                    return dayItems.size();
                }

                @Override
                public int getColumnCount() {
                    return 1;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    return dayItems.get(rowIndex);
                }
            };

    }

    public void updateTable(Month m, int day){
        itemsInDayTable.setModel(getItemTable(m, day));
        totalSpending.setText("Total Spending: $" + Double.toString(m.getDaySpending(day)));
    }

    public JPanel getPurchasesGUI(){
        return addPurchasesGUI;
    }

    private JComboBox monthComboBox;
    private JComboBox dayComboBox;
    private JButton addButton;
    private JTextField itemPurchasedTextField;
    private JTextField itemPriceTextField;
    private JComboBox categoryComboBox;
    private JPanel addPurchasesGUI;
    private JPanel itemValuesPanel;
    private JPanel dateConfirmationPanel;
    private JTable itemsInDayTable;
    private JButton removeItemButton;
    private JLabel totalSpending;
    private JLabel errorReport;
    private String[] monthComboArray = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    private int[] test;

}
