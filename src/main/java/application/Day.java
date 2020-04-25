package main.java.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Day {
    public Day() {
        purchases = new HashMap<>();
        categories = new ArrayList<>();
        costOfPurchases = 0.0;
    }

    public void addItem(String category, Item itemPurchased) {
        if (this.purchases.containsKey(category)) {
            this.purchases.get(category).add(itemPurchased);
        } else {
            ArrayList<Item> newItem = new ArrayList<>();
            newItem.add(itemPurchased);
            this.purchases.put(category, newItem);
            this.categories.add(category);
        }
        this.costOfPurchases += itemPurchased.getPrice();
    }

    /**
     * Iterates through all items and adds category:price to the HashMap from Month
     *
     * @param monthHashMap HashMap from class month. All category:spending will be added for the day
     */
    public void getDailyCategorySpending(HashMap<String, Double> monthHashMap) {

        ArrayList<String> categoryList = getCategories();

        for (String category : this.getCategories()) {    // iterate through each category
            for (Item item : this.getItems(category)) {   // iterate through each Item per category

                // if category exists, update the price. otherwise create category
                if (monthHashMap.containsKey(category)) {
                    monthHashMap.put(category, monthHashMap.get(category) + item.getPrice());
                } else {
                    monthHashMap.put(category, item.getPrice());
                }
            }
        }
    }

    public ArrayList<Item> getItems(String category) {
        return purchases.get(category);
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public boolean purchasesIsEmpty() {
        return this.purchases.isEmpty();
    }

    public boolean categoriesIsEmpty() {
        return this.categories.isEmpty();
    }

    public double getCostOfPurchases() {
        return this.costOfPurchases;
    }

    public String toString() {
        return purchases.toString();
    }

    private HashMap<String, ArrayList<Item>> purchases;
    private ArrayList<String> categories;
    private double costOfPurchases;

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
    }
}