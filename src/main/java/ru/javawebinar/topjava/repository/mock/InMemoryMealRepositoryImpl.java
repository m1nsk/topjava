package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.to.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(n ->
                MealsUtil.MEALS.forEach(m -> save(m, n.getId())));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUserId(userId);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        meal = repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        return meal.getUserId().equals(userId) ? meal : null;
    }

    @Override
    public void delete(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal == null || !meal.getUserId().equals(userId))
            return;
        repository.remove(id);
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        return meal.getUserId().equals(userId) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream().filter(item -> item.getUserId().equals(userId)).sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());
    }
}

