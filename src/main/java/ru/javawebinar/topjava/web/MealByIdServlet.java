package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealByIdServlet extends HttpServlet {
    private MealService mealService = new MealServiceImpl();

    private static final Logger log = getLogger(MealByIdServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            int parseId = Integer.parseInt(id);
            Meal loadedMeal = mealService.getMealById(parseId);
            if (loadedMeal == null)
                throw  new ServletException("Meal with this ID don't exists");
            request.setAttribute("mealItem", mealService.getMealById(parseId));
            request.getRequestDispatcher("/meal-by-id.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("meal ID param is not a number");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            int parseId = Integer.parseInt(id);
            mealService.removeMeal(parseId);
            response.sendRedirect("/meals");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("meal ID param is not a number");
        }
    }
}
