package ru.javawebinar.topjava.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class MealValidator {
    public static LocalDateTime validateDateTime(String dateTime, Map<String, String> errors) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e){
            errors.put("dateTime", "wrong dateTime value");
        }
        return localDateTime;
    }

    public static String validateDescription(String description, Map<String, String> errors) {
        if(description.isEmpty()){
            errors.put("description", "description can't be empty");
        }
        return description;
    }

    public static int validateCalories(String calories, Map<String, String> errors) {
        int parseCalories = -1;
        try {
            parseCalories = Integer.parseInt(calories);
            if(parseCalories < 0) {
                errors.put("calories", "calories should be positive number");
            }
        } catch (NumberFormatException e){
            errors.put("calories", "wrong calories value");
        }
        return parseCalories;
    }
}
