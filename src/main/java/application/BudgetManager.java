package main.java.application;

import main.java.utility.CSVWriter;
import main.java.utility.DayFormatter;
import main.java.gui.*;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

//needs to import:
//CSVWriter and CSVReader to read and write csv files
//Loader to take CSV Strings and turn them into/populate Days
//Logger to log what is being done
//Collection of Days that need to be loaded from file.
//
public class BudgetManager {

    public BudgetManager() {
        /*
        File DEFAULT_FILE = new File("src/main/resources/Test" + DEFAULT_YEAR.toString() + ".csv");
        try {
            if(DEFAULT_FILE.createNewFile()){
                try {
                    FileWriter writer = new FileWriter(DEFAULT_FILE);
                    Day blankDay = new Day();
                    for (int i = 0; i < DEFAULT_YEAR.length(); i++) {
                        CSVWriter.writeLine(writer, DayFormatter.formatDayToCSV(blankDay));
                    }
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

         */
        aYearLoader = new YearLoader(DEFAULT_YEAR);
        aYearBudget = aYearLoader.getYearFromFile(DEFAULT_YEAR);

    }

    public YearLoader getLoader() {
        return this.aYearLoader;
    }


    public YearBudget getYear() {
        return this.aYearBudget;
    }

    public static void main(String[] args) {

        //Check if there is a file to read from and read from that file
        //if not, create a new file for the current year
        //run application-gui
        //finished with application:
        //overwrite old file with new data
        //close application
        BudgetManager bm = new BudgetManager();
        JFrame mainApp = new MainMenuGUI("Budget Manager", bm.getYear(), bm.getLoader());
        bm.aYearLoader.saveYearToFile();

    }

    private YearBudget aYearBudget;
    private YearLoader aYearLoader;
    private static final Year DEFAULT_YEAR = Year.now();

}
