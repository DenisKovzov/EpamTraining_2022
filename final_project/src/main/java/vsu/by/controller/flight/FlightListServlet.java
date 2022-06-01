package vsu.by.controller.flight;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Flight;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Устанавливает список рейсов в атрибуты
 * После чего переходит на /flight/list.jsp
 *
 * @author Kovzov Denis
 */
public class FlightListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            FlightService flightService = creator.getFlightService();

            List<Flight> flights = flightService.findAll();

            req.setAttribute("flights", flights);

            req.getRequestDispatcher("/WEB-INF/jsp/flight/list.jsp").forward(req, resp);
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new ServletException(e);
        }
    }
}
