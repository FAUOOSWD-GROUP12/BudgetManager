package application;

import java.time.Year;
import java.util.*;

/**
 * Contains All data for one year. Contains algorithms for retrieving data from it's Months, such as Item Lists,
 * spending data. Adds and removes data of it's months.
 * Used by GUIs to retrieve data.
 */
public class YearBudget {
    /**
     * Initializes 12 months with corresponding number of days for the current year
     */
    public YearBudget(Year aYear) {
        currentYear = aYear;
        Months = new Month[13];
        yearlyBudget = 0.0;
        monthlyBudget = 0.0;
        String tempName;
        int tempCount = 0;

        for (int i = 1; i < 13; i++) {
            tempName = currentYear.atMonth(i).getMonth().toString();
            tempCount = currentYear.atMonth(i).lengthOfMonth();
            Months[i] = new Month(tempCount, tempName);
        }
        savedCategories = new ArrayList<>();
    }

    /**
     * Craetes a Hashmap where each key is a category, and the value is the total yearly spending in that category.
     * @return HashMap of category:totalSpending pairs
     */
    public HashMap<String, Double> getYearlyCategorySpending() {
        HashMap<String, Double> yearCategorySpending = new HashMap<String, Double>();
        for (int i = 1; i < 13; i++) {
            this.Months[i].getMonthlyCategorySpending(yearCategorySpending);
        }
        return yearCategorySpending;
    }

    /**
     * getYearlySpending will get the cost of the whole year. It iterates through the months accumulating their costs.
     *
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
     * Setter for Double monthlyBudget
     * @param monthlyBudget the new monthlyBudget
     */
    public void setMonthlyBudget(Double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    /**
     * Setter for Double yearlyBudget
     * @param yearlyBudget new yearly budget
     */
    public void setYearlyBudget(Double yearlyBudget) {
        this.yearlyBudget = yearlyBudget;
    }

    /**
     * Returns private Double monthlyBudget
     * @return current monthlyBudget
     */
    public Double getMonthlyBudget() {
        return this.monthlyBudget;
    }

    /**
     * Returns private yearlyBudget
     * @return current yearly budget
     */
    public Double getYearlyBudget() {
        return this.yearlyBudget;
    }

    /**
     * getMonth() returns the month specified by the indexes [1-12]. Index 0 will return null;
     *
     * @param index - month index
     * @return the Month object in the Year
     */
    public Month getMonth(int index) {
        return Months[index];
    }

    /**
     * Returns all Month objects
     * @return Array of all Months
     */
    public Month[] getMonths() {
        return Months;
    }

    /**
     * Returns current year as a string
     * @return current year as a string
     */
    public String getYear() {
        return currentYear.toString();
    }

    /**
     * Returns a list of all days in a year
     * @return List of all days
     */
    public List<Day> getAllDays() {
        List<Day> allDays = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            allDays.addAll(Months[i].daysInThisMonth());
        }
        return allDays;
    }

    /**
     * returns a list of all categories in a year
     * " Has potential to do things" - Curtis WoodWorth 2020
     * @return ArrayList containg all categories in a year
     */
    public ArrayList<String> getAllCategories() {
        allCategories = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            allCategories.addAll(Months[i].getCategoriesInMonth());
        }
        Set<String> s = new LinkedHashSet<>(allCategories);
        allCategories = new ArrayList<>(s);
        return allCategories;
    }

    /**
     * Setter for savedCategories
     * @param categories categories to be saved
     */
    public void setSavedCategories(ArrayList<String> categories) {
        savedCategories = categories;
    }

    /**
     * Returns saved categories by user
     * @return saved categories
     */
    public ArrayList<String> getSavedCategories() {
        return savedCategories;
    }

    /**
     * Adds a new category
     * @param categoryToAdd category to be saved
     */
    public void addSavedCategory(String categoryToAdd) {
        savedCategories.add(categoryToAdd);
    }


    /**
     * Removes a saved category
     * @param categoryToRemove category to be removed
     */
    public void removeSavedCategory(String categoryToRemove) {
        savedCategories.remove(categoryToRemove);
    }
  
    private final Year currentYear;
    private final Month[] Months;
    private ArrayList<String> allCategories; //full category list used to search all items
    private ArrayList<String> savedCategories; //category list containing values user wants to see
    private Double yearlyBudget;
    private Double monthlyBudget;

}
