package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    private static Map<Integer, Meal> mealData;
    private static final Logger log = getLogger(MealDaoImpl.class);
    private static Integer id;
    static {
        id = 0;
        mealData = new HashMap<>();
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        for (int i = 0; i < mealList.size(); i++) {
            mealData.put(id, mealList.get(i));
        }
    }

    @Override
    public void addMeal(Meal meal) {
        mealData.put(id,meal);
        id++;
    }

    @Override
    public void removeMeal(int id) {
        if (id < mealData.size())
            mealData.remove(id);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        if (mealData.containsKey(id))
            mealData.replace(id, meal);
    }

    @Override
    public Meal getMealById(int id) {
        if (mealData.containsKey(id)) {
            try {
                return (Meal) mealData.get(id).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Meal> mealList() {
        Map<Integer, Meal> resultMap = new HashMap<>();
        mealData.forEach((key, value) -> {
            try {
                Meal meal = (Meal) value.clone();
                resultMap.put(key, meal);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return resultMap;
    }
}
