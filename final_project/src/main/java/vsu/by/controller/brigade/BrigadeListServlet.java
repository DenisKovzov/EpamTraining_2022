package vsu.by.controller.brigade;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Brigade;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.BrigadeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Устанавливает список бригад в атрибуты
 * После чего переходит на /brigade/list.jsp
 *
 * @author Kovzov Denis
 */
public class BrigadeListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            BrigadeService brigadeService = creator.getBrigadeService();

            List<Brigade> brigades = brigadeService.findAll();

            req.setAttribute("brigades", brigades);

            req.getRequestDispatcher("/WEB-INF/jsp/brigade/list.jsp").forward(req, resp);
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new ServletException(e);
        }
    }
}
