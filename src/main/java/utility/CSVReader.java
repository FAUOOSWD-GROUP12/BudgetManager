package main.java.utility;

import main.java.application.Day;
import main.java.application.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static String[] readLine(BufferedReader r) throws IOException {
        String csvDay = r.readLine();
        String splitByComma = ",";
        String[] aDay = csvDay.split(splitByComma);
        return aDay;
    }

}
