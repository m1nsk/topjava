package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.Meal;

import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal create(Meal meal){
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int id){
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal){
        service.update(meal, AuthorizedUser.id());
    }

    public Meal get(int id){
        return service.get(id, AuthorizedUser.id());
    }

    public List<MealWithExceed> getList(){
        return service.getAllMealWithExceed(AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

}