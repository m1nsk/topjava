package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

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
        mealDao.create(meal);
    }

    @Override
    public void removeMeal(int id) {
        mealDao.removeMeal(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealDao.updateMeal(meal);
    }

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public List<Meal> mealList() {
        return mealDao.mealList();
    }

    @Override
    public List<MealWithExceed> mealWithExceedList() {
        return MealsUtil.getFilteredWithExceeded(mealDao.mealList(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}
