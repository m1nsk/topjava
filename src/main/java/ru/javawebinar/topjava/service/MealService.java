package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.to.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal create(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    void update(Meal meal, int userId);

    List<Meal> getAll(int userId);

    List<MealWithExceed> getAllMealWithExceed(int userId, int calories);
}