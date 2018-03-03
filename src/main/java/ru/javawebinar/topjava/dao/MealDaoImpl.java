package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    AtomicInteger id_counter = new AtomicInteger(0);
    private static List<Meal> mealData = new ArrayList<>();
    private static final Logger log = getLogger(MealDaoImpl.class);

    @Override
    public void addMeal(Meal meal) {
        meal.setId(id_counter.incrementAndGet());
        mealData.add(meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        for(int i = 0; i < mealData.size(); i++){
            if (mealData.get(i).equals(meal)) {
                mealData.remove(i);
                break;
            }
        }
    }

    @Override
    public void updateMeal(Meal meal) {
        for(int i = 0; i < mealData.size(); i++){
            if (mealData.get(i).getId() == meal.getId()) {
                mealData.set(i, meal);
                break;
            }
        }
    }

    @Override
    public Meal getMealById(int id) {
        for(int i = 0; i < mealData.size(); i++){
            if (mealData.get(i).getId() == id) {
                return mealData.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Meal> mealList() {
        List<Meal> resultMeal = new ArrayList<>();
        for(Meal meal: mealData) {
            try {
                resultMeal.add((Meal) meal.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return resultMeal;
    }

    @Override
    public List<MealWithExceed> mealWithExceed() {
        List<Meal> resultMeal = new ArrayList<>();
        for(Meal meal: mealData) {
            try {
                resultMeal.add((Meal) meal.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return MealsUtil.getFilteredWithExceeded(resultMeal, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
    }
}
