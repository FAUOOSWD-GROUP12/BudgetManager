package main.java.utility;

import main.java.application.Day;
import main.java.application.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVWriter {
    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String value : values) {
            if (!first) {
                sb.append(DEFAULT_SEPARATOR);
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }

    public static void main(String[] args) {

        Day dayTest = new Day();
        dayTest.addItem("Groceries", new Item("Bread", 5));
        dayTest.addItem("Education", new Item("Book", 100));
        dayTest.addItem("Amazon", new Item("Toilet Paper", 20));
        dayTest.addItem("Groceries", new Item("Milk", 5));
        Day dayTest1 = new Day();
        dayTest1.addItem("Groceries1", new Item("Bread1", 5));
        dayTest1.addItem("Education1", new Item("Book1", 100));
        dayTest1.addItem("Amazon1", new Item("Toilet Paper1", 20));
        dayTest1.addItem("Groceries1", new Item("Milk1", 5));
        Day dayEmptyTest = new Day();


        String csvFile = "src/main/resources/Test2020.csv";

        try {
            FileWriter writer = new FileWriter(csvFile);
            for (int i = 0; i < 366; i++) {
                Day aDay = new Day();
                aDay.addItem("test", new Item("TESTING", 100));
                CSVWriter.writeLine(writer, DayFormatter.formatDayToCSV(aDay));
            }
            /*
            CSVWriter.writeLine(writer, DayFormatter.formatDayToCSV(dayTest));
            CSVWriter.writeLine(writer,DayFormatter.formatDayToCSV(dayTest1));
            CSVWriter.writeLine(writer,DayFormatter.formatDayToCSV(dayEmptyTest));
            */
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
