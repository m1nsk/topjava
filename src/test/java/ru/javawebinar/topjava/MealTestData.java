package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int TEST_MEAL_ID_1 = ADMIN_ID + 1;
    public static final int TEST_MEAL_ID_2 = TEST_MEAL_ID_1 + 1;
    public static final int TEST_MEAL_ID_3 = TEST_MEAL_ID_2 + 1;
    public static final int TEST_MEAL_ID_4 = TEST_MEAL_ID_3 + 1;
    public static final int TEST_MEAL_ID_5 = TEST_MEAL_ID_4 + 1;
    public static final int TEST_MEAL_ID_6 = TEST_MEAL_ID_5 + 1;
    public static final int TEST_MEAL_ID_7 = TEST_MEAL_ID_6 + 1;
    public static final int TEST_MEAL_ID_8 = TEST_MEAL_ID_7 + 1;

    public static final Meal TEST_MEAL_1 = new Meal(TEST_MEAL_ID_1, LocalDateTime.parse("2015-05-30T10:00"), "test1", 1000);
    public static final Meal TEST_MEAL_2 = new Meal(TEST_MEAL_ID_2, LocalDateTime.parse("2015-05-30T13:00"), "test1", 2000);
    public static final Meal TEST_MEAL_3 = new Meal(TEST_MEAL_ID_3, LocalDateTime.parse("2015-05-31T10:00"), "test1", 3000);
    public static final Meal TEST_MEAL_4 = new Meal(TEST_MEAL_ID_4, LocalDateTime.parse("2015-05-31T13:00"), "test1", 4000);
    public static final Meal TEST_MEAL_5 = new Meal(TEST_MEAL_ID_5, LocalDateTime.parse("2015-05-30T10:00"), "test2", 1000);
    public static final Meal TEST_MEAL_6 = new Meal(TEST_MEAL_ID_6, LocalDateTime.parse("2015-05-30T13:00"), "test2", 2000);
    public static final Meal TEST_MEAL_7 = new Meal(TEST_MEAL_ID_7, LocalDateTime.parse("2015-05-31T10:00"), "test2", 3000);
    public static final Meal TEST_MEAL_8 = new Meal(TEST_MEAL_ID_8, LocalDateTime.parse("2015-05-31T13:00"), "test2", 4000);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
