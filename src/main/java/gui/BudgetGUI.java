package main.java.gui;

import main.java.application.YearBudget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BudgetGUI {

    private JTextPane currentBudgetTextPane;
    private JTextField monthlyBudgetTextField;
    private JTextField yearlyBudgetTextField;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JLabel yearlyBudgetLabel;
    private JLabel monthlyBudgetLabel;
    private JTextPane ErrorMessageTextPane;
    private JButton setYearlyBudgetButton;
    private JButton setMonthlyBudgetButton;

    public BudgetGUI(YearBudget yearReference) {
        //super("Manage your Budget");
        setCurrentBudgetTextPane(yearReference.getMonthlyBudget(), yearReference.getYearlyBudget());

        setMonthlyBudgetButton.addActionListener(new ActionListener() {
            @Override
            /**
             * Sets new monthly budget if input is valid and is not higher than yearly budget/12
             */
            public void actionPerformed(ActionEvent e) throws NullPointerException, NumberFormatException {
                ErrorMessageTextPane.setText("");   // clear ErrorMessageTextPane from last message
                String monthlyBudgetStr = monthlyBudgetTextField.getText();
                Double monthlyBudget = 0.0;

                try {
                    monthlyBudget = Double.parseDouble(monthlyBudgetStr);

                    if (willMonthlyBudgetFit(monthlyBudget, yearReference.getYearlyBudget())) {// if budget fits, enter it
                        yearReference.setMonthlyBudget(monthlyBudget);
                        setCurrentBudgetTextPane(yearReference.getMonthlyBudget(), yearReference.getYearlyBudget());
                        monthlyBudgetTextField.setText("");
                    } else {   // if budget doesn't fit, notify user
                        ErrorMessageTextPane.setText("Monthly budget does not fit within the yearly budget!\n " +
                                "Monthly budget can be at most: $" + (yearReference.getYearlyBudget() / 12) +
                                "\n to fit within the current yearly budget.");
                    }


                } catch (NullPointerException exception) {
                    ErrorMessageTextPane.setText("Please enter a monthly budget!");
                } catch (NumberFormatException exception) {
                    ErrorMessageTextPane.setText("Please enter a valid number!");
                } catch (Exception E) {
                    ErrorMessageTextPane.setText("Please enter valid input");
                }

            }
        });

        setYearlyBudgetButton.addActionListener(new ActionListener() {
            @Override
            /**
             * Sets proposed yearly budget if the input is valid and is not lower than the monthly budgets * 12
             */
            public void actionPerformed(ActionEvent e) throws NullPointerException, NumberFormatException {
                ErrorMessageTextPane.setText("");   // clear ErrorMessageTextPane from last message
                String yearlyBudgetStr = yearlyBudgetTextField.getText();
                Double yearlyBudget = 0.0;
                try {
                    yearlyBudget = Double.parseDouble(yearlyBudgetStr);
                    if (willYearlyBudgetFit(yearlyBudget, yearReference.getMonthlyBudget())) {
                        yearReference.setYearlyBudget(yearlyBudget);
                        setCurrentBudgetTextPane(yearReference.getMonthlyBudget(), yearReference.getYearlyBudget());
                        yearlyBudgetTextField.setText("");
                    } else {
                        ErrorMessageTextPane.setText("Yearly budget is too low!\n " +
                                "Yearly budget must be at least: $" + (yearReference.getMonthlyBudget() * 12) +
                                "\n to allow for the current monthly budget.");
                    }
                } catch (NullPointerException exception) {
                    ErrorMessageTextPane.setText("Please enter a yearly budget!");
                } catch (NumberFormatException exception) {
                    ErrorMessageTextPane.setText("Please enter a valid number!");
                } catch (Exception E) {
                    ErrorMessageTextPane.setText("Please enter valid input");
                }

            }
        });
    }

    /**
     * Verifies if current yearly budget allows for proposed monthly budget
     *
     * @param newMonthlyBudget
     * @param currentYearlyBudget
     * @return true if it will fit(or year is not set), false if it will not
     */
    public boolean willMonthlyBudgetFit(Double newMonthlyBudget, Double currentYearlyBudget) {
        if (currentYearlyBudget > 0) {  // if yearly budget is set, compare it w/ newMonthlyBudget
            if (newMonthlyBudget <= currentYearlyBudget / 12) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if new monthly budget will fit within the current yearly budget(if it's set)
     *
     * @param newYearlyBudget
     * @param currentMonthlyBudget
     * @return true if monthly budget will fit (or yearlyBudget is not set), false if it will not fit
     */
    public boolean willYearlyBudgetFit(Double newYearlyBudget, Double currentMonthlyBudget) {
        if (currentMonthlyBudget > 0) {
            if (newYearlyBudget >= currentMonthlyBudget * 12) {
                return true;
            } else {
                return false;
            }
        }
        return true;

    }


    /**
     * Displays budgets set for month and year, or "Budget not set"  if no budget is set
     */
    public void setCurrentBudgetTextPane(Double monthlyBudget, Double yearlyBudget) {
        String budgetMessage = "";

        if (yearlyBudget != 0.0) {
            budgetMessage = "Current Yearly Budget:  $" + yearlyBudget.toString() + "\n";
        } else {
            budgetMessage = "Yearly Budget Not Set!" + "\n";
        }

        if (monthlyBudget != 0.0) {
            budgetMessage += "Current Monthly Budget: $" + monthlyBudget.toString();
        } else {
            budgetMessage += "Monthly Budget Not Set!";
        }

        currentBudgetTextPane.setText(budgetMessage);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}