package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

public class MealServiceImpl implements MealService{
    MealDao mealDao;

    public MealServiceImpl() {
        this.mealDao = new MealDaoImpl();
    }

    public void setMealDao(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    @Override
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }

    @Override
    public void removeMeal(int id) {
        mealDao.removeMeal(id);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        mealDao.updateMeal(id, meal);
    }

    @Override
    public Meal getMealById(int id) {
        mealDao.getMealById(id);
    }

    @Override
    public Map<Integer, Meal> mealList() {
        mealDao.mealList();
    }
}
