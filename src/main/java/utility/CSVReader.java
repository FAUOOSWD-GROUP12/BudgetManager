package main.java.utility;

import main.java.application.Day;
import main.java.application.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static String[] readLine(BufferedReader r) throws IOException{
        String csvDay = r.readLine();
        String splitByComma = ",";
        String[] aDay = csvDay.split(splitByComma);
        return aDay;
    }

    public static void main(String[] args) {
        /*
        String csvFile = "src/main/resources/Test.csv";
        String row = "";
        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while((row = CSVReader.readLine(br)) != null){
                System.out.println(i);
                System.out.println(row);
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

         */
    }
}
