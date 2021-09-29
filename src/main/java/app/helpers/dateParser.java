package app.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateParser {

    private static SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");

    public static String toString(Date date) throws ParseException {
        return formatter.format(date);
    }

    public static Date toDate(String date) throws ParseException {
        return formatter.parse(date);
    }
}
