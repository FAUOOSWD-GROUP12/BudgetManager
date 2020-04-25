package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesGUI {
    public CategoriesGUI() {
        JFrame frame = new JFrame();
        frame.setContentPane(categoryManagerGUI);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private JPanel categoryManagerGUI;
    private JButton cancelButton;
    private JTextField textField1;
    private JLabel categoryJLabel;
    private JButton addButton;
    private JButton removeButton;
    private JList categoryList;

}
