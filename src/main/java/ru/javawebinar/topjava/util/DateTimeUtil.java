package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDays(LocalDate lt, LocalDate startDate, LocalDate endDate) {
        return lt.compareTo(startDate) >= 0 && lt.compareTo(endDate) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }


    public static LocalTime tryParseTime(String value) {
        try {
            return LocalTime.parse(value);
        } catch (DateTimeParseException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static LocalDate tryParseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
