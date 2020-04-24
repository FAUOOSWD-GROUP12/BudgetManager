package main.java.application;
import main.java.utility.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;

public class YearLoader{
    YearLoader(){
    }

    public YearBudget getYearFromFile(Year yearToLoad){
        // Create a new, empty YearBudget
        YearBudget loadedYear = new YearBudget(yearToLoad);
        // File to read from
        File CSVFile = new File("src/main/resources/Test" + loadedYear.getYear() + ".csv");
        //Open the file
        //if the file is there
        if(CSVFile.exists()){
            //open the file
            try(BufferedReader br = new BufferedReader(new FileReader(CSVFile))){
                //for each month in year
                for(int m = 1; m < 13; m++){
                    //for each day in month
                    for (int d = 1; d < loadedYear.getMonth(m).getDaysInMonth(); d++){
                        //read a day (single row/line) from the file in a String array format
                        String[] aDay = CSVReader.readLine(br);
                        //if the string array has a category, item name, price
                        if (aDay.length % 3 == 0){
                            // take an entry and load it int a day
                            for(int i = 0; i < aDay.length; i++){
                                String category = aDay[i];
                                i++;
                                String itemName = aDay[i];
                                i++;
                                float itemPrice = Float.parseFloat(aDay[i]);
                                loadedYear.getMonth(m).addItemToDay(category, new Item(itemName, itemPrice), d);
                                //System.out.println(category + itemName + itemPrice);
                            }
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Something went wrong in YearLoader.java, " + CSVFile + " does not exist." );
        }

        return loadedYear;
    }

    public void saveYearToFile(){

    }


    private Year yearToLoad;
    private File CSVFile;
}
