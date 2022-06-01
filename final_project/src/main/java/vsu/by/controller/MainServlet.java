package vsu.by.controller;


import vsu.by.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Отвечает за перенаправление пользователей согласно их роли
 * если пользователь не авторизован, то перенаправляет на /login.html
 *
 * @author Kovzov Denis
 */
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMIN:
                        req.getRequestDispatcher("/WEB-INF/jsp/roles/admin.jsp").forward(req, resp);
                        return;
                    case DISPATCHER:
                        req.getRequestDispatcher("/WEB-INF/jsp/roles/dispatcher.jsp").forward(req, resp);
                        return;
                    case USER:
                        req.getRequestDispatcher("/WEB-INF/jsp/roles/user.jsp").forward(req, resp);
                        return;

                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
