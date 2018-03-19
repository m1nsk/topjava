package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;


    @Test
    public void get() {
        Meal getMeal = service.get(TEST_MEAL_ID_1, USER_ID);
        assertMatch(TEST_MEAL_1, getMeal);
    }

    @Test
    public void delete()  {
        service.delete(TEST_MEAL_ID_1, USER_ID);
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, TEST_MEAL_2, TEST_MEAL_3, TEST_MEAL_4);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> allBetweenDates = service.getBetweenDates(LocalDateTime.parse("2015-05-30T09:00").toLocalDate(),
                LocalDateTime.parse("2015-05-30T12:00").toLocalDate(), USER_ID);
        assertMatch(allBetweenDates, TEST_MEAL_1, TEST_MEAL_2);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> allBetweenDates = service.getBetweenDateTimes(LocalDateTime.parse("2015-05-30T09:00"),
                LocalDateTime.parse("2015-05-30T12:00"), USER_ID);
        assertMatch(allBetweenDates, TEST_MEAL_1);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, TEST_MEAL_1, TEST_MEAL_2, TEST_MEAL_3, TEST_MEAL_4);
    }

    @Test
    public void update() {
        Meal updated = new Meal(TEST_MEAL_1);
        updated.setDescription("Updated description");
        updated.setCalories(777);
        service.update(updated, USER_ID);
        assertMatch(service.get(TEST_MEAL_ID_1, USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "new Meal", 999);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.get(newMeal.getId(), ADMIN_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound()  {
        service.delete(TEST_MEAL_ID_1, ADMIN_ID);
    }


    @Test(expected = NotFoundException.class)
    public void getNotFound()  {
        service.get(TEST_MEAL_ID_1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound()  {
        Meal updated = service.get(TEST_MEAL_ID_1, ADMIN_ID);
        updated.setDescription("Updated description");
        updated.setCalories(777);
        service.update(updated, ADMIN_ID);
    }
}