package vsu.by.controller.flight;

import org.apache.logging.log4j.LogManager;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.FlightService;
import vsu.by.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Получает id рейса и если id валиден, то удаляет его.
 * После чего переходит на страницу с адресом
 * /flight/list.html
 *
 * @author Kovzov Denis
 */
public class FlightDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }


        if (id != null) {
            try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {

                FlightService flightService = serviceCreator.getFlightService();
                flightService.delete(id);

            } catch (ServiceException e) {
                resp.sendRedirect(req.getContextPath() + "/flight/list.html?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                LogManager.getLogger().warn(e);
                return;
            } catch (Exception e) {
                LogManager.getLogger().error(e);
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/flight/list.html");
    }

}
