package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;
import java.util.Map;

public interface MealDao {
    Meal create(Meal meal);
    void remove(int id);
    void update(Meal meal);
    Meal getById(int id);
    List<Meal> list();
}
