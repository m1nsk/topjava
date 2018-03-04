package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealService {
    void add(Meal meal);
    void remove(int id);
    void update(Meal meal);
    Meal getById(int id);
    List<Meal> list();
    List<MealWithExceed> withExceedList();
}
