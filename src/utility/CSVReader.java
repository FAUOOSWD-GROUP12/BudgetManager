package utility;

import java.io.BufferedReader;
import java.io.IOException;

public class CSVReader {
    /**
     * Utility function to read a line from csv file
     * @param r reader attached to file
     * @return a string array
     * @throws IOException
     */
    public static String[] readLine(BufferedReader r) throws IOException {
        String csvDay = r.readLine();
        String splitByComma = ",";
        String[] aDay = csvDay.split(splitByComma);
        return aDay;
    }

}
