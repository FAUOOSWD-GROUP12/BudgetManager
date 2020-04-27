//package main.java.application;
package main.java.application;

import main.java.application.Day;
import main.java.application.Item;

import java.lang.reflect.Array;
import java.util.*;

public class Month {

    /**
     * @param daysInMonth Number of days in the month
     * @param monthName   name of the month (January, February...)
     */
    public Month(int daysInMonth, String monthName) {
        this.monthName = monthName;
        this.daysInMonth = daysInMonth;
        this.days = new Day[daysInMonth + 1];
        for (int i = 1; i < daysInMonth + 1; i++) {
            days[i] = new Day();
        }
    }

    /**
     * Iterates through each day in the month, and adds up total spending per category in a HashMap provided.
     *
     * @param HashMapParam contains category:spending, whose values will be updates for the entire month
     * @return HashMap containing spending for each category
     */
    public void getMonthlyCategorySpending(HashMap<String, Double> HashMapParam) {
        for (int i = 1; i <= daysInMonth; i++) {
            if (days[i] != null) {
                days[i].getDailyCategorySpending(HashMapParam);
            }
        }
    }

    /**
     * getMonthlySpending() will get all of the days aggregated inside the month and accumulate their costs.
     * @return the cost of all the days in this month. The cost of the month.
     */
    public Double getMonthlySpending() {
        Double total = 0.0;
        for (int i = 1; i <= daysInMonth; i++) {
            if (days[i] != null) {
                total += days[i].getCostOfPurchases();
            }
        }
        return total;
    }

    /**
     * Add item to specified day. If day is null, initialize day and then add item
     *
     * @param item - item object to add.
     * @param dayNum - day of the month
     */
    public void addItemToDay(String category, Item item, int dayNum) {

        if (days[dayNum] != null) {
            days[dayNum].addItem(category, item);
        } else {
            days[dayNum] = new Day();
            days[dayNum].addItem(category, item);
        }
    }

    /**
     * getDaysInMonth() will return the number of days in the month.
     * @return int number of days in the month.
     */
    public int getDaysInMonth() {
        return this.daysInMonth;
    }

    /**
     * getMonthName() returns the name of the month.
     * @return the String name of the month.
     */
    public String getMonthName() {
        return this.monthName;
    }

    /**
     * Will go through the Days, collect their category keys, and remove duplicates to get a master key to
     * access all the items purchased in each Day of the Month.
     * @return master key list to access all of Month's Days.
     */
    public ArrayList<String> getCategoriesInMonth(){
        ArrayList<String> categories = new ArrayList<>();
        for(int i = 1; i < daysInMonth; i++){
            categories.addAll(days[i].getCategories());
        }
        Set<String> s = new LinkedHashSet<>(categories);
        categories = new ArrayList<>(s);
        return categories;
    }

    /**
     * Returns a List object referencing all the Day objects in Month.
     * @return allDays - every day in this month.
     */
    public List<Day> daysInThisMonth() {
        ArrayList<Day> allDays = new ArrayList<>(Arrays.asList(days).subList(1, days.length));
        return allDays;
    }

    public ArrayList<Item> getItemsFromDay(int dayIndex){
        ArrayList<Item> items = new ArrayList<>();
        if(!days[dayIndex].categoriesIsEmpty()){
            for (String c: days[dayIndex].getCategories()){
                {
                    ArrayList<Item> temp = days[dayIndex].getItems(c);
                    items.addAll(temp);
                }
            }
        }
        return items;
    }
    private final Day[] days;
    private final int daysInMonth;
    private final String monthName;
}