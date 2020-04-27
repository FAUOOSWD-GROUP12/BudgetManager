package main.java.application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Day Class manages the items purchased in a day.
 */

public class Day {
    /**
     * Initializer will initialize the HashMap and ArrayList. Cost of purchases will be initialized to 0.
     */
    public Day() {
        purchases = new HashMap<>();
        categories = new ArrayList<>();
        costOfPurchases = 0.0;
    }

    /**
     * addItem(String category, Item itemPurchased) will store keys of categories, and map them to groups of items.
     * A category will be an identifier for the type of item within. EX: Groceries -> (Apples/$5.00), (Cake/$8.00)...
     *
     * @param category      the String identifier to access the item.
     * @param itemPurchased The item to be stored and can be accessed by the category.
     */
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

    /**
     * Returns all items purchased in day
     *
     * @return
     */
    public ArrayList<Item> getDayItems() {
        ArrayList<Item> dayItems = new ArrayList<>();

        for (String category : getCategories()) {
            dayItems.addAll(getItems(category));
        }

        return dayItems;

    }

    /**
     * getItems(String category) will get all the items that are mapped to the key category.
     *
     * @param category - Accessor key to the map holding a group of items.
     * @return an ArrayList<Item> containing the group of items identified by the category.
     * Will return null if the mapping does not contain the category key.
     */
    public ArrayList<Item> getItems(String category) {
        return purchases.get(category);
    }

    /**
     * getCategories() will return an ArrayList of String values that are keys to access
     * the items in the purchases HashMap.
     *
     * @return the key list for the HashMap purchases.
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Will check if the object contains no purchases.
     *
     * @return true if there are no mappings.
     */
    public boolean purchasesIsEmpty() {
        return this.purchases.isEmpty();
    }

    /**
     * Will check if the object contains any keys.
     *
     * @return true if there are no keys.
     */
    public boolean categoriesIsEmpty() {
        return this.categories.isEmpty();
    }

    /**
     * Will return the accumulated cost of the day.
     *
     * @return the total dollar value of this day.
     */
    public double getCostOfPurchases() {
        return this.costOfPurchases;
    }


    public String toString() {
        return purchases.toString();
    }

    public void removePurchase(String key, Item itemToRemove) {
        if (purchases.containsKey(key)) {
            purchases.get(key).remove(itemToRemove);
            costOfPurchases = costOfPurchases - itemToRemove.getPrice();
        }
    }

    private final HashMap<String, ArrayList<Item>> purchases;
    private final ArrayList<String> categories;
    private double costOfPurchases;

}