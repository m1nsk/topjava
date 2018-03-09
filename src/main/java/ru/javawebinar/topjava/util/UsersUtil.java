package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(0,  "Alesey1", "1@a.ru", "password", Role.ROLE_ADMIN),
            new User(1,  "Alesey2", "2@a.ru", "password", Role.ROLE_USER),
            new User(2,  "Alesey3", "3@a.ru", "password", Role.ROLE_USER),
            new User(3,  "Alesey4", "4@a.ru", "password", Role.ROLE_USER)
    );
}