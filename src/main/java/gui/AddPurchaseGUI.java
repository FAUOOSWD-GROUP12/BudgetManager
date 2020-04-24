package main.java.gui;

import main.java.application.Month;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPurchaseGUI {
    public AddPurchaseGUI(Month[] months){
        JFrame frame = new JFrame();
        frame.setContentPane(addPurchasesGUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private JComboBox monthComboBox;
    private JComboBox dayComboBox;
    private JButton addButton;
    private JButton cancelButton;
    private JTextField itemPurchasedTextField;
    private JTextField itemPrice$TextField;
    private JComboBox categoryComboBox;
    private JPanel addPurchasesGUI;
    private JPanel itemValuesPanel;
    private JPanel dateConfirmationPanel;
}
