
package main.java.application;

import java.time.Year;

/**
 *
 * @author study_work
 */
public class YearBudget {
    /**
     * Initializes 12 months with corresponding number of days for the current year
     */
    public YearBudget(){
        currentYear = Year.now();
        Months = new Month[13];
        String tempName;
        int tempCount = 0;
        for(int i = 1; i < 13;i++){
            tempName = currentYear.atMonth(i).getMonth().toString();
            tempCount = currentYear.atMonth(i).lengthOfMonth();
            Months[i] = new Month(tempCount,tempName);
        }
    }
    public static void main(String [] args){

    }
    /**
     * Prints months with corresponding number of days
     */
    public void testMonths(){

        for(int i = 1;i < 13; i++){
            System.out.println(this.Months[i].getMonthName());
            System.out.println(this.Months[i].getDaysInMonth());
        }
    }

    private Year currentYear;
    private Month[] Months;
}
