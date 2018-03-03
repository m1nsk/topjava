package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealService {
    void addMeal(Meal meal);
    void removeMeal(int id);
    void updateMeal(Meal meal);
    Meal getMealById(int id);
    List<Meal> mealList();
    List<MealWithExceed> mealWithExceedList();
}
