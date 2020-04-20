package main.java.utility;

import main.java.application.Day;
import main.java.application.Item;

import java.util.ArrayList;
import java.util.List;

public class DayFormatter {
    public static List<String> formatDayCSV(Day d){
        List<String> values = new ArrayList<>();
        for(String category: d.getCategories()){
            ArrayList<Item> items = d.getItems(category);
            for(Item item: items){
                values.add(category);
                values.add(item.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(item.getPrice());
                values.add(sb.toString());
            }
        }
        return values;
    }

    public static void main(String[] args) {
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
        System.out.println(formatDayCSV(dayTest));
    }
}
