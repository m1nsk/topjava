package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    private static List<Meal> mealData;
    private static final Logger log = getLogger(MealDaoImpl.class);
    static {
        mealData = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
    }

    @Override
    public void addMeal(Meal meal) {
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
            if (mealData.get(i).equals(meal)) {
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
