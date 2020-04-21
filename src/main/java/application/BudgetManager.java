package main.java.application;

import java.time.Year;

//needs to import:
//CSVWriter and CSVReader to read and write csv files
//Loader to take CSV Strings and turn them into/populate Days
//Logger to log what is being done
//Collection of Days that need to be loaded from file.
//
public class BudgetManager {

    public BudgetManager(){
        thisYear = Year.now();

    }
    private void loadYear(){

    }
    public static void main(String[] args) {

        //Check if there is a file to read from and read from that file
        //if not, create a new file for the current year
        //run application-gui
        //finished with application:
        //overwrite old file with new data
        //close application
    }

    private final Year thisYear;
}
