package main.java.gui;

import main.java.application.Item;
import main.java.application.Month;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddPurchaseGUI {

    public AddPurchaseGUI(Month[] months, String[] categories) {

        JFrame frame = new JFrame("Add Purchase");

        ComboBoxModel monthModel = new DefaultComboBoxModel(monthComboArray);
        monthComboBox.setModel(monthModel);

        ComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
        categoryComboBox.setModel(categoryModel);

        frame.setContentPane(addPurchasesGUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

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
                months[monthSelected].addItemToDay(categorySelected, new Item(itemName, itemPrice), daySelected);
            }
        });

        frame.pack();
        frame.setVisible(true);
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = monthComboBox.getSelectedIndex() + 1;
                int days = months[index].getDaysInMonth();
                int i = 1;
                ArrayList<Integer> eachDay = new ArrayList();
                while (i <= days) {
                    System.out.println(i);
                    eachDay.add(i);
                    i++;
                }

                ComboBoxModel dayModel = new DefaultComboBoxModel(eachDay.toArray());
                dayComboBox.setModel(dayModel);
            }
        });


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
    private String[] monthComboArray = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    private int[] test;


    private void createUIComponents() {
        // TODO: place custom component creation code here
        monthComboBox = new JComboBox();
    }
}
