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
    public void add(Meal meal) {
        mealDao.create(meal);
    }

    @Override
    public void remove(int id) {
        mealDao.remove(id);
    }

    @Override
    public void update(Meal meal) {
        mealDao.update(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealDao.getById(id);
    }

    @Override
    public List<Meal> list() {
        return mealDao.list();
    }

    @Override
    public List<MealWithExceed> withExceedList() {
        return MealsUtil.getFilteredWithExceeded(mealDao.list(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}
