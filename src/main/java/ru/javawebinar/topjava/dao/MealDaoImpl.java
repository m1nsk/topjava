package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    AtomicInteger id_counter = new AtomicInteger(0);
    private static Map<Integer, Meal> mealData = new ConcurrentHashMap<>();
    private static final Logger log = getLogger(MealDaoImpl.class);
    {
        List<Meal >mealData = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        mealData.forEach(item -> this.create(item));
    }

    @Override
    public Meal create(Meal mealItem) {
        int id = id_counter.incrementAndGet();
        mealItem.setId(id);
        return mealData.put(id, mealItem);
    }

    @Override
    public void removeMeal(int id) {
        mealData.remove(id);
    }

    @Override
    public void updateMeal(Meal mealItem) {
        mealData.put(mealItem.getId(), mealItem);
    }

    @Override
    public Meal getMealById(int id) {
        if (mealData.containsKey(id)) {
            Meal loadedMeal = mealData.get(id);
            return new Meal(loadedMeal.getDateTime(), loadedMeal.getDescription(),
                    loadedMeal.getCalories(), loadedMeal.getId());
        }
        return null;
    }

    @Override
    public List<Meal> mealList() {
        List<Meal> resultMeal = new ArrayList<>();
        for(Meal aMeal: mealData.values()) {
            Meal loadedMeal = aMeal;
            resultMeal.add(new Meal(loadedMeal.getDateTime(), loadedMeal.getDescription(),
                    loadedMeal.getCalories(), loadedMeal.getId()));
        }
        return resultMeal;
    }
}
