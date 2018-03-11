package ru.javawebinar.topjava;

import org.springframework.stereotype.Component;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

@Component
public class AuthorizedUser {

    public AuthorizedUser() {
    }

    private static int id = 0;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}