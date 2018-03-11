package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<MealIndex, Meal> repository = new ConcurrentHashMap<>();
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
            repository.put(new MealIndex(meal.getId(), meal.getUserId()), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(new MealIndex(meal.getId(), meal.getUserId()), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.remove(new MealIndex(id, userId)) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(new MealIndex(id, userId));
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

    class MealIndex{
        int id;
        int userId;

        public MealIndex(int id, int userId) {
            this.id = id;
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MealIndex mealIndex = (MealIndex) o;
            return id == mealIndex.id &&
                    userId == mealIndex.userId;
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, userId);
        }
    }
}

