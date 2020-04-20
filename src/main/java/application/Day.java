package main.java.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Day {
    public Day() {
        purchases = new HashMap<>();
        categories = new ArrayList<>();
    }

    public void addItem(String category, Item itemPurchased) {
        if(this.purchases.containsKey(category)){
            ArrayList<Item> categoryList = this.purchases.get(category);
            categoryList.add(itemPurchased);
            this.categories.add(category);
        } else{
            ArrayList<Item> newItem = new ArrayList<>();
            newItem.add(itemPurchased);
            this.purchases.put(category, newItem);
            this.categories.add(category);
        }
    }

    public ArrayList<String> getCategories() {
        return (ArrayList<String>) categories.clone();
    }

    public boolean purchasesIsEmpty() {
        return this.purchases.isEmpty();
    }

    public boolean categoriesIsEmpty(){
        return this.categories.isEmpty();
    }

    public String toString(){
        return purchases.toString();
    }
    private HashMap<String, ArrayList<Item>> purchases;
    private ArrayList<String> categories;

    public static void main(String[] args) {
        Day dayTest = new Day();
        System.out.println(dayTest.purchasesIsEmpty());
        dayTest.addItem("A", new Item("food", 20));
        System.out.println(dayTest.purchasesIsEmpty());
        dayTest.addItem("A", new Item("water", 5));
        System.out.println(dayTest.toString());
    }
}