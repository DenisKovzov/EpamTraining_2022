package vsu.by.controller.flight;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Brigade;
import vsu.by.models.Flight;
import vsu.by.models.Status;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Получает данные рейса, после проверки
 * сохраняет работника.
 * После чего переходит на страницу /flight/list.html
 *
 * @author Kovzov Denis
 */
public class FlightSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Integer numberTickets = null;
        Date departureTime = null;
        Date arrivalTime = null;
        String departurePoint = null;
        String destination = null;
        Status status = null;
        Long brigadeId = null;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (Exception e) {
        }

        try {
            brigadeId = Long.valueOf(req.getParameter("brigadeId"));

            numberTickets = Integer.valueOf(req.getParameter("numberTickets").trim());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

            departureTime = simpleDateFormat.parse(req.getParameter("departureTime"));
            arrivalTime = simpleDateFormat.parse(req.getParameter("arrivalTime"));

            departurePoint = req.getParameter("departurePoint");
            destination = req.getParameter("destination");

            status = Status.valueOf(req.getParameter("status"));

        } catch (Exception e) {
        }

        if (numberTickets != null && departureTime != null && arrivalTime != null && departurePoint != null && destination != null && brigadeId != null) {
            try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {
                Flight flight = new Flight();

                flight.setId(id);
                flight.setNumberTickets(numberTickets);
                flight.setDepartureTime(departureTime);
                flight.setArrivalTime(arrivalTime);
                flight.setDeparturePoint(departurePoint);
                flight.setDestination(destination);
                flight.setStatus(status);

                Brigade brigade = new Brigade();
                brigade.setId(brigadeId);
                flight.setBrigade(brigade);

                serviceCreator.getFlightService().save(flight);

            } catch (RuntimeException | ServiceException e) {
                resp.sendRedirect(req.getContextPath() + "/flight/list.html?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                LogManager.getLogger().warn(e);
                return;
            } catch (Exception e) {
                LogManager.getLogger().error(e);
                throw new ServletException(e);
            }
        }


        resp.sendRedirect(req.getContextPath() + "/flight/list.html");
    }

}
