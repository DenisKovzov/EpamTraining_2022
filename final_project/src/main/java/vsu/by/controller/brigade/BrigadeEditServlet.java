package vsu.by.controller.brigade;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Brigade;
import vsu.by.models.Employee;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.BrigadeService;
import vsu.by.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Устанавливает в атрибуты свободных работников(которые не состоят в бригаде) и
 * работников этой бригады, если она была передана по id.
 * После чего переходит на /brigade/edit.jsp
 *
 * @author Kovzov Denis
 */
public class BrigadeEditServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            BrigadeService brigadeService = creator.getBrigadeService();

            EmployeeService employeeService = creator.getEmployeeService();

            List<Employee> employees = employeeService.findAllFree();

            if (id != null) {
                Brigade brigade = brigadeService.findById(id);
                employees.addAll(brigade.getEmployees());
                req.setAttribute("brigade", brigade);
            }

            req.setAttribute("employees", employees);

            req.getRequestDispatcher("/WEB-INF/jsp/brigade/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new ServletException(e);
        }


    }
}


