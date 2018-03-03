package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;
import java.util.Map;

public interface MealDao {
    Meal create(Meal meal);
    void removeMeal(int id);
    void updateMeal(Meal meal);
    Meal getMealById(int id);
    List<Meal> mealList();
}
