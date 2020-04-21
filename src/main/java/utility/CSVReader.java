package main.java.utility;

import javax.imageio.stream.ImageInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVReader {
    public static String readLine(BufferedReader r){
        try{
            return r.readLine();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
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
    }
}
