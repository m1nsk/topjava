package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

public interface MealService {
    void addMeal(Meal meal);
    void removeMeal(int id);
    void updateMeal(int id, Meal meal);
    Meal getMealById(int id);
    Map<Integer, Meal> mealList();
}
