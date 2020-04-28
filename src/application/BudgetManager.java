package application;

import gui.MainMenuGUI;
import javax.swing.*;
import java.time.Year;

/**
 * BudgetManager class will hold the YearBudget that is being managed and a YearLoader. This class initializer will
 * initialize the year specified (Default the current year) and will use a the loader to read the file
 * and fill the YearBudget. The main method will run the gui and be passed the year loaded from the file and the loader to
 * manipulate year data and save it when the gui terminates.
 */
public class BudgetManager {
    /**
     * Initializer will create a year loader to return a YearBudget for use in the application.
     */
    public BudgetManager() {
        aYearLoader = new YearLoader(DEFAULT_YEAR);
        aYearBudget = aYearLoader.getYearFromFile(DEFAULT_YEAR);
    }

    /**
     * Get loader returns the loader that was created in the construction of BudgetManager()
     *
     * @return YearLoader Class to read and write a file with the YearBudget it contains.
     */

    public YearLoader getLoader() {
        return this.aYearLoader;
    }

    /**
     * getYear() returns a YearBudget class that contains all data loaded from a file, specified by YearLoader.
     *
     * @return the YearBudget stored in BudgetManager.
     */
    public YearBudget getYear() {
        return this.aYearBudget;
    }

    public static void main(String[] args) {
        BudgetManager bm = new BudgetManager();
        JFrame mainApp = new MainMenuGUI("Budget Manager", bm.getYear(), bm.getLoader());
    }

    private final YearBudget aYearBudget;
    private final YearLoader aYearLoader;
    private static final Year DEFAULT_YEAR = Year.now();
    /* The DEFAULT_YEAR could always be used, and another constructor could be added
    to make a BudgetManager that works with a different year.*/
}