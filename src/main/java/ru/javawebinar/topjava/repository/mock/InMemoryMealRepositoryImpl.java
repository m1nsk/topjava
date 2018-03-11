package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(n ->
                MealsUtil.MEALS.forEach(m -> save(
                        new Meal(m.getDateTime(), m.getDescription(), m.getCalories()),
                        n.getId()
                )));
    }

    @Override
    public Meal save(final Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUserId(userId);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        Meal result = repository.computeIfPresent(meal.getId(), (id, oldMeal) ->
                oldMeal.getUserId().equals(meal.getUserId()) ? meal : oldMeal);
        if (meal == null)
            return null;
        return result.getUserId().equals(userId) ? result : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = repository.getOrDefault(id, null);
        if (meal == null || !meal.getUserId().equals(userId))
            return false;
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.getOrDefault(id, null);
        if (meal == null)
            return null;
        return meal.getUserId().equals(userId) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getFilteredByDate(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.values().stream()
                .filter(item -> item.getUserId().equals(userId) && DateTimeUtil.isBetween(item.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).thenComparingInt(Meal::getId))
                .collect(Collectors.toList());
    }
}

