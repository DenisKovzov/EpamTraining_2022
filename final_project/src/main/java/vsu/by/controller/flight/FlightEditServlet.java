package vsu.by.controller.flight;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.*;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Получает id, вслучаи валидности устанавливает в атрибуты рейс
 * с этим id. Устанавливает список бригад
 *
 * После чего переходит на /flight/edit.jsp
 *
 * @author Kovzov Denis
 */
public class FlightEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            FlightService flightService = creator.getFlightService();

            if (id != null) {
                Flight flight = flightService.findById(id);
                req.setAttribute("flight", flight);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

                req.setAttribute("departureTime", simpleDateFormat.format(flight.getDepartureTime()));
                req.setAttribute("arrivalTime", simpleDateFormat.format(flight.getArrivalTime()));
            }

            List<Brigade> brigades = creator.getBrigadeService().findAll();

            req.setAttribute("brigades", brigades);

            req.getRequestDispatcher("/WEB-INF/jsp/flight/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new ServletException(e);
        }


    }
}
