package main.java.gui;

import main.java.application.BudgetManager;
import main.java.application.YearBudget;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuGUI extends JFrame{

    public MainMenuGUI(String title, YearBudget year){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Closing");
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new MainMenuButtons(year.getMonths()).getButtons());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {

        //JFrame main = new MainMenuGUI("Budget Manager");


    }

    private JPanel mainMenu;
}
