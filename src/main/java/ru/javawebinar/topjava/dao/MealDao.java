package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;
import java.util.Map;

public interface MealDao {
    void addMeal(Meal meal);
    void removeMeal(Meal meal);
    void updateMeal(Meal meal);
    Meal getMealById(int id);
    List<Meal> mealList();
    List<MealWithExceed> mealWithExceed();
}
