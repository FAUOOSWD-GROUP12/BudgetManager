package main.java.gui;

import main.java.application.BudgetManager;
import main.java.application.YearBudget;
import main.java.application.YearLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  Creates the window for Budget Manager Application, and saves it's data upon exit
 */
public class MainMenuGUI extends JFrame {
    /**
     * Loads saved data from previous sessions, creates window, and saves data upon window exiting
     * @param title Title of window
     * @param year refernce to year object
     * @param aYearLoader used to save/load data
     */
    public MainMenuGUI(String title, YearBudget year, YearLoader aYearLoader) {
        super(title);
        yl = aYearLoader;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                yl.saveYearToFile();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new MainMenuButtons(year).getButtons());
        this.pack();
        this.setVisible(true);
    }

    private JPanel mainMenu;
    private JPanel buttonPanel;
    private YearLoader yl;
    private YearBudget year;

}
