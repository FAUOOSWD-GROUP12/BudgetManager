package main.java.application;

import main.java.utility.CSVReader;
import main.java.utility.CSVWriter;
import main.java.utility.DayFormatter;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YearLoader {
    YearLoader(Year yearToManage) {
        this.yearToLoad = yearToManage;
        CSVFile = new File("src/main/resources/Test" + yearToLoad.toString() + ".csv");
        CategoryFile = new File("src/main/resources/TestCategory" + yearToLoad.toString() + ".csv");
    }

    public YearBudget getYearFromFile(Year yearToLoad) {
        // Create a new, empty YearBudget
        loadedYear = new YearBudget(yearToLoad);
        //if the file is there
        if (CSVFile.exists()) {
            //open the file
            try (BufferedReader br = new BufferedReader(new FileReader(CSVFile))) {
                //for each month in year
                for (int m = 1; m < 13; m++) {
                    //for each day in month
                    for (int d = 1; d < loadedYear.getMonth(m).getDaysInMonth(); d++) {
                        //read a day (single row/line) from the file in a String array format
                        String[] aDay = CSVReader.readLine(br);
                        //if the string array has a category, item name, price
                        if (aDay.length % 3 == 0) {
                            // take an entry and load it int a day
                            for (int i = 0; i < aDay.length; i++) {
                                String category = aDay[i];
                                i++;
                                String itemName = aDay[i];
                                i++;
                                double itemPrice = Double.parseDouble(aDay[i]);
                                loadedYear.getMonth(m).addItemToDay(category, new Item(category, itemName, itemPrice), d);
                                //System.out.println(category + itemName + itemPrice);
                            }

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("YearLoader.java: " + CSVFile + " does not exist.");
        }
        if(CategoryFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(CategoryFile))){
                String[] cat = CSVReader.readLine(br);
                if (cat[0].equals("")){
                    loadedYear.setSavedCategories(new ArrayList<>());
                } else{
                    ArrayList<String> categoriesToLoad = new ArrayList<>(Arrays.asList(cat));
                    loadedYear.setSavedCategories(categoriesToLoad);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return loadedYear;
    }

    public void saveYearToFile() {

        System.out.println("Saving Year: " + yearToLoad.toString() + "to" + CSVFile.toString());
        List<Day> daysToSave = loadedYear.getAllDays();
        try {
            CSVFile.createNewFile();
            CategoryFile.createNewFile();
            try {
                FileWriter yearWriter = new FileWriter(CSVFile);
                for (Day d : daysToSave) {
                    CSVWriter.writeLine(yearWriter, DayFormatter.formatDayToCSV(d));
                }
                if (!(loadedYear.getSavedCategories().contains("") && loadedYear.getSavedCategories().size() == 1)){
                    FileWriter categoryWriter = new FileWriter(CategoryFile);
                    CSVWriter.writeLine(categoryWriter, loadedYear.getSavedCategories());
                    categoryWriter.flush();
                    categoryWriter.close();
                }
                yearWriter.flush();
                yearWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File CSVFile;
    private Year yearToLoad;
    private YearBudget loadedYear;
    private File CategoryFile;
}
