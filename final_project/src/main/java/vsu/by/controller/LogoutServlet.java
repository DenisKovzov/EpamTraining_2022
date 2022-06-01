package vsu.by.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Пытается взять существующую сессию если получилось, то
 * удаляет атрибут и уничтожает сессию после чего переходит на
 * контекст
 *
 * @author Kovzov Denis
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("session_user");
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath());
    }
}
