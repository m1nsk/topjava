package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles("datajpa")
public class MealServiceJPADataTest extends MealServiceTest {
    @Test
    public void getWithUser() throws Exception {
        Meal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        assertThat(actual).isEqualToComparingFieldByField(ADMIN_MEAL1);
    }
}