package ru.javawebinar.topjava.util;

import java.time.format.DateTimeFormatter;

public class MyDateTimeFormatter {
    private static final DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DateTimeFormatter getMyDateTimeFormatter() {
        return myDateTimeFormatter;
    }
}
