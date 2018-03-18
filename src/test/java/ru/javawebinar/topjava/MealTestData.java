package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int TEST_MEAL_ID_1 = 0;
    public static final int TEST_MEAL_ID_2 = TEST_MEAL_ID_1 + 1;
    public static final int TEST_MEAL_ID_3 = TEST_MEAL_ID_2 + 1;
    public static final int TEST_MEAL_ID_4 = TEST_MEAL_ID_3 + 1;

    public static final Meal TEST_MEAL_1 = new Meal(TEST_MEAL_ID_1, LocalDateTime.parse("2015-05-30T10:00"), "test1", 1000);
    public static final Meal TEST_MEAL_2 = new Meal(TEST_MEAL_ID_2, LocalDateTime.parse("2015-05-30T13:00"), "test1", 2000);
    public static final Meal TEST_MEAL_3 = new Meal(TEST_MEAL_ID_3, LocalDateTime.parse("2015-05-31T10:00"), "test2", 3000);
    public static final Meal TEST_MEAL_4 = new Meal(TEST_MEAL_ID_4, LocalDateTime.parse("2015-05-31T13:00"), "test2", 4000);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
