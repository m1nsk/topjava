package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class UserServiceJPADataTest extends UserServiceTest {

    @Test
    public void getWithMeal() throws Exception {
        User user = service.getWithMeal(USER_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER, "registered", "roles");
    }
}