package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

public interface MealDao {
    void addMeal(Meal meal);
    void removeMeal(int id);
    void updateMeal(int id, Meal meal);
    Meal getMealById(int id);
    Map<Integer, Meal> mealList();
}
