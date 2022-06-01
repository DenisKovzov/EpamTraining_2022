package vsu.by.controller.user;

import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Получает id пользователя и если id валиден, то удаляет его.
 * После чего переходит на страницу с адресом
 * /user/list.html
 *
 * @author Kovzov Denis
 */
public class UserDeleteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idsStr[] = req.getParameterValues("id");

        if (idsStr != null) {

            List<Long> ids = new ArrayList<>(idsStr.length);
            try (ServiceCreator creator = new ServiceCreatorImpl()) {
                for (String id : idsStr) {
                    ids.add(Long.valueOf(id));
                }
                UserService userService = creator.getUserService();
                userService.delete(ids);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/user/list.html");

    }
}

