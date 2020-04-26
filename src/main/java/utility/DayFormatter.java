package main.java.utility;

import main.java.application.Day;
import main.java.application.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFormatter {
    public static List<String> formatDayToCSV(Day d) {
        List<String> values = new ArrayList<>();
        System.out.println(d.toString());
        for (String category : d.getCategories()) {
            ArrayList<Item> items = d.getItems(category);
            for (Item item : items) {
                values.add(category);
                values.add(item.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(item.getPrice());
                values.add(sb.toString());
            }
        }
        return values;
    }

    public static Day formatCSVtoDay(String csvDay) {
        String splitByComma = ",";
        String[] aDay = csvDay.split(splitByComma);
        Day newDay = new Day();
        if (aDay.length % 3 == 0) {
            for (int i = 0; i < aDay.length; i++) {
                String category = aDay[i];
                i++;
                String itemName = aDay[i];
                i++;
                float itemPrice = Float.parseFloat(aDay[i]);
                newDay.addItem(category, new Item(itemName, itemPrice));
                //System.out.println(category + itemName + itemPrice);
            }
        }
        return newDay;
    }

    public static void main(String[] args) {
        /*
        Day dayTest = new Day();
        System.out.println(dayTest.purchasesIsEmpty());
        dayTest.addItem("A", new Item("food", 20));
        System.out.println(dayTest.purchasesIsEmpty());
        dayTest.addItem("A", new Item("water", 10));
        System.out.println(dayTest.toString());
        dayTest.addItem("B", new Item("Books", 200));
        System.out.println(dayTest.purchasesIsEmpty());
        dayTest.addItem("C", new Item("Pencils", 5));
        System.out.println(dayTest.toString());
        System.out.println(dayTest.getCostOfPurchases());
        System.out.println(formatDayToCSV(dayTest));

        String csvFile = "src/main/resources/Test.csv";
        ArrayList<String> rows = new ArrayList<>();
        String row = "";
        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while((row = CSVReader.readLine(br)) != null){
                rows.add(row);
                System.out.println(i);
                System.out.println(row);
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        for(String line : rows){
            System.out.println(DayFormatter.formatCSVtoDay(line));
        }

         */
    }
}
