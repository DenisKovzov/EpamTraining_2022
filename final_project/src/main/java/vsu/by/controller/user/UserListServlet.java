package vsu.by.controller.user;

import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.models.User;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Устанавливает список пользователей в атрибуты
 * После чего переходит на /user/list.jsp
 *
 * @author Kovzov Denis
 */
public class UserListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            UserService userService = creator.getUserService();

            List<User> users = userService.findAll();

            req.setAttribute("users", users);

            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

