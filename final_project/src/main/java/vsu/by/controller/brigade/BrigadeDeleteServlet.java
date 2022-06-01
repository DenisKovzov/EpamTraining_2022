package vsu.by.controller.brigade;

import org.apache.logging.log4j.LogManager;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.BrigadeService;
import vsu.by.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Получает id бригады и если id валидно, то удаляет ее.
 * После чего переходит на страницу с адресом
 * /brigade/list.html
 *
 * @author Kovzov Denis
 */
public class BrigadeDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }


        if (id != null) {
            try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {

                BrigadeService brigadeService = serviceCreator.getBrigadeService();
                brigadeService.delete(id);

            } catch (ServiceException e) {
                resp.sendRedirect(req.getContextPath() + "/brigade/list.html?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                LogManager.getLogger().warn(e);
                return;
            } catch (Exception e) {
                LogManager.getLogger().error(e);
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/brigade/list.html");
    }
}
