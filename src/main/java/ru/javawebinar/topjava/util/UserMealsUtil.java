package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 500)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(22,0), 2000));
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate))
                .values()
                .stream()
                .map(meals -> {
                    int sum = meals.stream().mapToInt(m -> m.getCalories()).sum();
                    return meals.stream().map(m -> new UserMealWithExceed(
                                    m.getDateTime(),
                                    m.getDescription(),
                                    m.getCalories(),
                                    sum > caloriesPerDay
                            )
                    ).filter(userMeal ->
                            userMeal.getDateTime().toLocalTime().isAfter(startTime)
                                    && userMeal.getDateTime().toLocalTime().isBefore(endTime)
                    )
                            .collect(Collectors.toList());
                }).flatMap(List::stream)
                .sorted(Comparator.comparing(UserMealWithExceed::getDateTime))
                .collect(Collectors.toList());
    }
}
