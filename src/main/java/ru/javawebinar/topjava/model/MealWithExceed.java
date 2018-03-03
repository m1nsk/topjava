package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MyDateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealWithExceed {
    private final LocalDateTime dateTime;

    private final DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String description;

    private final int calories;

    private final boolean exceed;

    private final int id;

    public MealWithExceed(int id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFormatedDateTime() {
        return dateTime.format(MyDateTimeFormatter.getMyDateTimeFormatter());
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }
}