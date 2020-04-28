package utility;

import application.Day;
import application.Item;

import java.util.ArrayList;
import java.util.List;

public class DayFormatter {
    public static List<String> formatDayToCSV(Day d) {
        List<String> values = new ArrayList<>();
        for (String category : d.getCategories()) {
            ArrayList<Item> items = d.getItems(category);
            for (Item item : items) {
                values.add(category);
                values.add(item.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(item.getPrice());
                values.add(sb.toString());
            }
        }
        return values;
    }
}
