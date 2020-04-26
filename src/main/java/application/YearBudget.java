
package main.java.application;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        yearlyBudget = 0.0;
        monthlyBudget = 0.0;
        String tempName;
        int tempCount = 0;

        for (int i = 1; i < 13; i++) {
            tempName = currentYear.atMonth(i).getMonth().toString();
            tempCount = currentYear.atMonth(i).lengthOfMonth();
            Months[i] = new Month(tempCount, tempName);
        }
    }

    public HashMap<String, Double> getYearlyCategorySpending() {
        HashMap<String, Double> yearCategorySpending = new HashMap<String, Double>();

        for (int i = 1; i < 13; i++) {
            this.Months[i].getMonthlyCategorySpending(yearCategorySpending);
        }
        return yearCategorySpending;
    }

    public Double getYearlySpending() {
        Double total = 0.0;
        for (int i = 1; i < 13; i++) {
            total += this.Months[i].getMonthlySpending();
        }

        return total;
    }

    public void setMonthlyBudget(Double monthlyBudget){
        this.monthlyBudget = monthlyBudget;
    }

    public void setYearlyBudget(Double yearlyBudget){
        this.yearlyBudget = yearlyBudget;
    }

    public Double getMonthlyBudget(){
        return this.monthlyBudget;
    }

    public Double getYearlyBudget(){
        return this.yearlyBudget;
    }

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
    private Double yearlyBudget;
    private Double monthlyBudget;
}
