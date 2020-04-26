
package main.java.application;

import java.time.Year;
import java.util.*;

/**
 * @author study_work
 */
public class YearBudget {
    /**
     * Initializes 12 months with corresponding number of days for the current year
     */
    public YearBudget(Year aYear) {
        currentYear = aYear;
        Months = new Month[13];
        String tempName;
        int tempCount = 0;
        for (int i = 1; i < 13; i++) {
            tempName = currentYear.atMonth(i).getMonth().toString();
            tempCount = currentYear.atMonth(i).lengthOfMonth();
            Months[i] = new Month(tempCount, tempName);
        }
        savedCategories = new ArrayList<>();
    }


    public HashMap<String, Double> getYearlyCategorySpending() {
        HashMap<String, Double> yearCategorySpending = new HashMap<String, Double>();
        for (int i = 1; i < 13; i++) {
            this.Months[i].getMonthlyCategorySpending(yearCategorySpending);
        }
        return yearCategorySpending;
    }

    /**
     * getYearlySpending will get the cost of the whole year. It iterates through the months accumulating their costs.
     * @return the total cost of the year.
     */
    public Double getYearlySpending() {
        Double total = 0.0;
        for (int i = 1; i < 13; i++) {
            total += this.Months[i].getMonthlySpending();
        }
        return total;
    }

    /**
     * getMonth() returns the month specified by the indexes [1-12]. Index 0 will return null;
     * @param index - month index
     * @return the Month object in the Year
     */
    public Month getMonth(int index) {
        return Months[index];
    }

    public Month[] getMonths() {
        return Months;
    }

    public String getYear() {
        return currentYear.toString();
    }

    public List<Day> getAllDays() {
        List<Day> allDays = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            allDays.addAll(Months[i].daysInThisMonth());
        }
        return allDays;
    }

    public ArrayList<String> getAllCategories(){
        allCategories = new ArrayList<>();
        for(int i = 1; i < 13; i++){
            allCategories.addAll(Months[i].getCategoriesInMonth());
        }
        Set<String> s = new LinkedHashSet<>(allCategories);
        allCategories = new ArrayList<>(s);
        return allCategories;
    }

    public void setSavedCategories(ArrayList<String> categories){
        savedCategories = categories;
    }
    public ArrayList<String> getSavedCategories(){
        return savedCategories;
    }
    public void addSavedCategory(String categoryToAdd){
        savedCategories.add(categoryToAdd);
    }
    public void removeSavedCategory(String categoryToRemove){
        savedCategories.remove(categoryToRemove);
    }
    public static void main(String[] args) {
    }

    /**
     * Prints months with corresponding number of days
     */
    public void testMonths() {

        for (int i = 1; i < 13; i++) {
            System.out.println(this.Months[i].getMonthName());
            System.out.println(this.Months[i].getDaysInMonth());

        }
    }

    private Year currentYear;
    private Month[] Months;
    private ArrayList<String> allCategories; //full category list used to search all items
    private ArrayList<String> savedCategories; //category list containing values user wants to see
}
