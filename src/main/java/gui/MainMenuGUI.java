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
        this.setContentPane(new MainMenuButtons(year).getButtons());
        this.pack();
        this.setVisible(true);
    }

    private JPanel mainMenu;
    private YearLoader yl;
    private YearBudget year;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout(0, 0));
        mainMenu.setBackground(new Color(-12828863));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainMenu;
    }
}
