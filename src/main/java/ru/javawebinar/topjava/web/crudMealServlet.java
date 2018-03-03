package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.validators.MealValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


public class crudMealServlet extends HttpServlet {
    private MealService mealService = new MealServiceImpl();
    private static String INSERT_OR_EDIT = "/create-meal.jsp";
    private static String LIST_USER = "/meals.jsp";

    private static final Logger log = getLogger(MealByIdServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("mealId"));
            mealService.removeMeal(userId);
            forward = LIST_USER;
            request.setAttribute("mealList", mealService.mealWithExceedList());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal mealItem = mealService.getMealById(mealId);
            request.setAttribute("mealItem", mealItem);
        } else if (action.equalsIgnoreCase("mealList")){
            forward = LIST_USER;
            request.setAttribute("mealList", mealService.mealWithExceedList());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        String dateTime = request.getParameter("dateTime");
        Map<String, String> errors = new HashMap<>();
        int parseId = -1;
        try{
            parseId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int parseCalories = MealValidator.validateCalories(calories, errors);
        LocalDateTime localDateTime = MealValidator.validateDateTime(dateTime, errors);
        description = MealValidator.validateDescription(description, errors);

        if (errors.isEmpty()) {

            Meal newMeal = new Meal(localDateTime, description, parseCalories);
            if(parseId < 0) {
                mealService.addMeal(newMeal);
            } else {
                mealService.updateMeal(newMeal);
            }
            request.setAttribute("mealList", mealService.mealWithExceedList());
            request.getRequestDispatcher(LIST_USER).forward(request, response);
        } else {
            request.setAttribute("errorItem", errors);
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        }
    }
}
