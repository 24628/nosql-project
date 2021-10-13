package app.helpers;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateParser {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime date) {
        return formatter.format(date);
    }

    public static LocalDateTime toDate(String date) throws ParseException {
        return LocalDateTime.parse(date, formatter);
    }

}
