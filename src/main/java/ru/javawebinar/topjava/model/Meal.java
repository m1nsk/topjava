package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MyDateTimeFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private int id;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.id = -1;
    }

    public Meal(LocalDateTime dateTime, String description, int calories, int id) {
        this(dateTime, description, calories);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Meal setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

}
