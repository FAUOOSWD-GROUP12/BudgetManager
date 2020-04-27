package main.java.gui;

import main.java.application.Item;
import main.java.application.Month;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;



public class ManagePurchaseGUI {

    public ManagePurchaseGUI(Month[] months, ArrayList<String> categories) {

        //JFrame frame = new JFrame("Add Purchase");

        ComboBoxModel monthModel = new DefaultComboBoxModel(monthComboArray);
        monthComboBox.setModel(monthModel);

        ComboBoxModel categoryModel = new DefaultComboBoxModel(categories.toArray());
        categoryComboBox.setModel(categoryModel);

       // frame.setContentPane(addPurchasesGUI);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        monthComboBox.setSelectedIndex(0);

       // cancelButton.addActionListener(new ActionListener() {
       //     @Override
       //     public void actionPerformed(ActionEvent e) {
       //         frame.dispose();
       //     }
       // });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = itemPurchasedTextField.getText();
                double itemPrice = Double.parseDouble(itemPriceTextField.getText());
                String categorySelected = (String) categoryComboBox.getSelectedItem();
                int monthSelected = monthComboBox.getSelectedIndex() + 1;
                int daySelected = dayComboBox.getSelectedIndex() + 1;

                System.out.println("Item name:" + itemName
                        + " Item Price:" + itemPrice
                        + " Category:" + categorySelected
                        + " Month Selected:" + monthSelected
                        + " Day Selected:" + daySelected);
                System.out.println(months[monthSelected].toString());
                months[monthSelected].addItemToDay(categorySelected, new Item(categorySelected, itemName, itemPrice), daySelected);
                updateTable(months[monthSelected],daySelected);
            }
        });

       // frame.pack();
       // frame.setVisible(true);

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
    private JButton cancelButton;
    private JTextField itemPurchasedTextField;
    private JTextField itemPriceTextField;
    private JComboBox categoryComboBox;
    private JPanel addPurchasesGUI;
    private JPanel itemValuesPanel;
    private JPanel dateConfirmationPanel;
    private JTable itemsInDayTable;
    private JButton removeItemButton;
    private JLabel totalSpending;
    private String[] monthComboArray = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    private int[] test;


    private void createUIComponents() {
        // TODO: place custom component creation code here
        monthComboBox = new JComboBox();
    }
}
