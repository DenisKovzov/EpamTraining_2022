package vsu.by.controller.user;

import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.models.Role;
import vsu.by.models.User;
import vsu.by.services.exceptions.ServiceException;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Получает данные пользователя, после проверки
 * сохраняет его.
 * После чего переходит на страницу /user/list.html
 *
 * @author Kovzov Denis
 */
public class UserSaveServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = null;
        try {
            role = Role.valueOf(req.getParameter("role"));
        } catch (NumberFormatException e) {
        }


        if (login != null && !login.trim().isEmpty() && password != null && !password.trim().isEmpty() && role != null) {
            Long id = null;
            try {
                id = Long.valueOf(req.getParameter("id"));
            } catch (NumberFormatException e) {
            }

            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);

            try (ServiceCreator creator = new ServiceCreatorImpl()) {
                UserService userService = creator.getUserService();
                userService.save(user);

            } catch (ServiceException | RuntimeException e) {
                resp.sendRedirect(req.getContextPath() + "/user/list.html?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                return;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");

    }
}

