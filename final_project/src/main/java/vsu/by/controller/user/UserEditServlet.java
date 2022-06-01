package vsu.by.controller.user;

import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.models.Role;
import vsu.by.models.User;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Получает id, вслучаи валидности устанавливает в атрибуты пользователя
 * с этим id.
 *
 * После чего переходит на /user/edit.jsp
 *
 * @author Kovzov Denis
 */
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id != null) {
            try (ServiceCreator creator = new ServiceCreatorImpl()) {
                UserService userService = creator.getUserService();

                User user = userService.findById(Long.parseLong(id));

                if (user != null) {
                    req.setAttribute("user", user);
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                resp.sendError(404);
                return;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}

