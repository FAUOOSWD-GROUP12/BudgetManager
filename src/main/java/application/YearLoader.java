package main.java.application;

import main.java.utility.CSVReader;
import main.java.utility.CSVWriter;
import main.java.utility.DayFormatter;

import java.io.*;
import java.time.Year;
import java.util.List;

public class YearLoader {
    YearLoader(Year yearToManage) {
        this.yearToLoad = yearToManage;
        CSVFile = new File("src/main/resources/Test" + yearToLoad.toString() + ".csv");
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
                                loadedYear.getMonth(m).addItemToDay(category, new Item(itemName, itemPrice), d);
                                //System.out.println(category + itemName + itemPrice);
                            }

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("YearLoader.java: " + CSVFile + " does not exist.");
        }

        return loadedYear;
    }

    public void saveYearToFile() {

        System.out.println("Saving Year: " + yearToLoad.toString() + "to" + CSVFile.toString());
        List<Day> daysToSave = loadedYear.getAllDays();
        try {
            if (CSVFile.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(CSVFile);
                    for (Day d : daysToSave) {
                        CSVWriter.writeLine(writer, DayFormatter.formatDayToCSV(d));
                    }
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File CSVFile;
    private Year yearToLoad;
    private YearBudget loadedYear;
    private FileWriter writer;
}
