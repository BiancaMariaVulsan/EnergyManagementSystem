package ro.tuc.ds2020.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date truncateDate(Date date) {
        try {
            String truncatedDateString = DATE_FORMAT.format(date);
            return DATE_FORMAT.parse(truncatedDateString);
        } catch (ParseException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
