package vsu.by.controller;

import org.apache.logging.log4j.LogManager;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.factories.ServiceCreatorException;
import vsu.by.models.User;
import vsu.by.services.exceptions.ServiceException;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * При get запросе страницы перенаправляет на /user/list.jsp
 * При post запросе получает логин и пароль после чего проверяет его
 * в случаи валидности переходит на /index.html
 * иначе get запрос на /login.html
 *
 * @author Kovzov Denis
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.trim().isEmpty() && password != null) {
            try (ServiceCreatorImpl creator = new ServiceCreatorImpl()) {
                UserService userService = creator.getUserService();
                User user = userService.login(login, password);

                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    resp.sendRedirect(req.getContextPath() + "/index.html");
                    return;
                }
            } catch (ServiceCreatorException | ServiceException e) {
                LogManager.getLogger().error(e);
                throw new ServletException(e);
            }
        }
        String error = "Логин или пароль не опознаны";
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode(error, "UTF-8"));
        LogManager.getLogger().warn(error);
    }
}
