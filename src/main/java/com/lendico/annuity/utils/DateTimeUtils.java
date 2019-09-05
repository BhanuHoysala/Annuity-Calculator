package com.lendico.annuity.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * Adds Month to the date in string and return the date in String format
     *
     * @param date
     * @return
     */
    public static LocalDateTime addMonthsToGivenLocaleDateTime(String date, int months, String pattern) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        localDateTime = localDateTime.plusMonths(months);
        return localDateTime;
    }
}
