package application;

import utility.CSVReader;
import utility.CSVWriter;
import utility.DayFormatter;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to load data(items, budgets, categories) from CSV and load a YearBudget object, and save YearBudget's object's data.
 */
public class YearLoader {
    YearLoader(Year yearToManage) {
        this.yearToLoad = yearToManage;
        CSVFile = new File("src/resources/Year" + yearToLoad.toString() + ".csv");
        CategoryFile = new File("src/resources/Categories" + yearToLoad.toString() + ".csv");
        BudgetFile = new File("src/resources/Budget" + yearToLoad.toString() + ".csv");
    }

    /**
     * Gets yearly information (items, budgets, categories) from a save file
     * @param yearToLoad Year object used to get data about current year
     * @return YearBudget info that returns all data loaded onto it
     */
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
                    for (int d = 1; d <= loadedYear.getMonth(m).getDaysInMonth(); d++) {
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
        } else {
            System.out.println("YearLoader.java: " + CSVFile + " does not exist.");
        }
        if (CategoryFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(CategoryFile))) {
                String[] cat = CSVReader.readLine(br);
                if (cat[0].equals("")) {
                    loadedYear.setSavedCategories(new ArrayList<>());
                } else {
                    ArrayList<String> categoriesToLoad = new ArrayList<>(Arrays.asList(cat));
                    loadedYear.setSavedCategories(categoriesToLoad);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (BudgetFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(BudgetFile))) {
                String[] budget = CSVReader.readLine(br);
                if (Double.parseDouble(budget[0]) > 0) {
                    loadedYear.setYearlyBudget(Double.parseDouble(budget[0]));
                }
                if (Double.parseDouble(budget[1]) > 0) {
                    loadedYear.setMonthlyBudget(Double.parseDouble(budget[1]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return loadedYear;
    }

    /**
     * Saves YearBudget object to a CSV file, saving items ,categories, and budget
     */
    public void saveYearToFile() {
        List<Day> daysToSave = loadedYear.getAllDays();
        try {
            CSVFile.createNewFile();
            CategoryFile.createNewFile();
            BudgetFile.createNewFile();
            try {
                FileWriter yearWriter = new FileWriter(CSVFile);
                for (Day d : daysToSave) {
                    CSVWriter.writeLine(yearWriter, DayFormatter.formatDayToCSV(d));
                }
                yearWriter.flush();
                yearWriter.close();
                if (!(loadedYear.getSavedCategories().contains("") && loadedYear.getSavedCategories().size() == 1)) {
                    FileWriter categoryWriter = new FileWriter(CategoryFile);
                    CSVWriter.writeLine(categoryWriter, loadedYear.getSavedCategories());
                    categoryWriter.flush();
                    categoryWriter.close();
                }
                FileWriter budgetWriter = new FileWriter(BudgetFile);
                ArrayList<String> budgetList = new ArrayList<>();
                if (loadedYear.getYearlyBudget() == 0) {
                    budgetList.add("0");
                } else {
                    budgetList.add(Double.toString(loadedYear.getYearlyBudget()));
                }
                if (loadedYear.getMonthlyBudget() == 0) {
                    budgetList.add("0");
                } else {
                    budgetList.add(Double.toString(loadedYear.getMonthlyBudget()));
                }
                CSVWriter.writeLine(budgetWriter, budgetList);
                budgetWriter.flush();
                budgetWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private final File CSVFile;
    private final Year yearToLoad;
    private YearBudget loadedYear;
    private final File CategoryFile;
    private final File BudgetFile;
}
