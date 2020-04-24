//package main.java.application;
package main.java.application;
import main.java.application.Day;
import main.java.application.Item;

import java.util.HashMap;
import java.util.Hashtable;

public class Month{

    /**
     *
     * @param daysInMonth Number of days in the month
     * @param monthName name of the month (January, February...)
     */
    public Month(int daysInMonth, String monthName){
        this.monthName = monthName;
        this.daysInMonth = daysInMonth;
        this.days = new Day[daysInMonth + 1];
    }

    /**
     *
     * Iterates through each day in the month, and adds up total spending per category in a HashMap provided.
     * @param HashMapParam contains category:spending, whose values will be updates for the entire month
     * @return HashMap containing spending for each category
     */
    public void getMonthlhyCategorySpending(HashMap<String, Double> HashMapParam){
        for(int i = 1; i <= daysInMonth; i++){
            if(days[i] != null){
                days[i].getDailyCategorySpending(HashMapParam);
            }
        }
    }

    public Double getMonthlySpending(){
        Double total = 0.0;

        for(int i = 1; i <= daysInMonth; i++){
            if(days[i] != null){
                total += days[i].getCostOfPurchases();
            }
        }

        return total;
    }

    /**
     * Add item to specified day. If day is null, initialize day and then add item
     * @param item
     * @param dayNum
     */
    public void addItemToDay(String category, Item item, int dayNum){

        if(days[dayNum] != null){
            days[dayNum].addItem(category, item);
        }
        else{
            days[dayNum] = new Day();
            days[dayNum].addItem(category, item);
        }
    }

    public int getDaysInMonth(){
        return this.daysInMonth;
    }

    public String getMonthName(){
        return this.monthName;
    }

    private Day[] days;
    private int daysInMonth;
    private String monthName;
}