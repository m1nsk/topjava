package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    AuthorizedUser authorizedUser = new AuthorizedUser();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        String userId = request.getParameter("user");
        Integer uId;
        try {
            uId = Integer.parseInt(userId);
        } catch (NumberFormatException e){
            uId = null;
        }
        if (uId != null) {
            authorizedUser.setId(uId);
            response.sendRedirect("meals");
        } else {
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        }
    }
}
