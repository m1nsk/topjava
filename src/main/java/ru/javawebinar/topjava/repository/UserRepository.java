package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    default User getWithMeal(int id) {
        return null;
    };

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}