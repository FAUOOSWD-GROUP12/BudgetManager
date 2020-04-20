package main.java.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class CSVWriter {
    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String value : values){
            if (!first){
                sb.append(DEFAULT_SEPARATOR);
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }

    public static void main(String[] args) {
        String csvFile = "src/main/resources/Test.csv";

        try {
            FileWriter writer = new FileWriter(csvFile);
            CSVWriter.writeLine(writer, Arrays.asList("a", "b", "c", "d"));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
