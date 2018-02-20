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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 500)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(10, 1), LocalTime.of(19, 59), 2000));
        System.out.println(getFilteredWithExceededForLoop(mealList, LocalTime.of(10, 1), LocalTime.of(19, 59), 2000));
//
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate))
                .values()
                .stream()
                .map(meals -> {
                    int sum = meals
                            .stream()
                            .mapToInt(UserMeal::getCalories)
                            .sum();
                    return meals
                            .stream()
                            .filter(userMeal -> userMeal
                                    .getDateTime()
                                    .toLocalTime()
                                    .compareTo(startTime) >= 0
                                    && userMeal
                                    .getDateTime()
                                    .toLocalTime()
                                    .compareTo(endTime) <= 0
                            ).map(m -> new UserMealWithExceed(
                                            m.getDateTime(),
                                            m.getDescription(),
                                            m.getCalories(),
                                            sum > caloriesPerDay
                                    )
                            ).collect(Collectors.toList());
                }).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededForLoop(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> calByDay = new HashMap<>();
        for(UserMeal meal: mealList) {
            calByDay.put(
                    meal.getDate(),
                    calByDay.getOrDefault(meal.getDate(), 0) + meal.getCalories()
            );
        }
        for(UserMeal meal: mealList) {
            if(meal.getDateTime()
                    .toLocalTime()
                    .compareTo(startTime) >= 0
                    && meal.getDateTime()
                    .toLocalTime()
                    .compareTo(endTime) <= 0){
                result.add(
                        new UserMealWithExceed(
                                meal.getDateTime(),
                                meal.getDescription(),
                                meal.getCalories(),
                                calByDay.get(meal.getDate()) > caloriesPerDay
                        )
                );
            }
        }
        return result;
    }
}