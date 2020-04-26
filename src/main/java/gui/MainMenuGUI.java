package main.java.gui;

import main.java.application.BudgetManager;
import main.java.application.YearBudget;
import main.java.application.YearLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI(String title, YearBudget year, YearLoader aYearLoader) {
        super(title);

        yl = aYearLoader;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                yl.saveYearToFile();
                System.out.println("Closing");
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(new MainMenuButtons(year).getButtons());
        //this.setContentPane(new MainMenuButtons(year).getButtons());
        this.pack();
        this.setVisible(true);
    }

    private JPanel mainMenu;
    private YearLoader yl;
    private YearBudget year;

}
