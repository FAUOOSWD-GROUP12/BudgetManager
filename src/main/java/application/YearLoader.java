package main.java.application;
import java.time.Year;

public class YearLoader implements Loader{
    YearLoader(String year){
        Year yearToLoad = java.time.Year.parse(year);
    }
    @Override
    public String readCSV() {
        return null;
    }

    @Override
    public void writeCSV() {

    }
    Year yearToLoad;
}
