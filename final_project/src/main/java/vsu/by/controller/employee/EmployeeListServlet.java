package vsu.by.controller.employee;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Employee;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Устанавливает список работников в атрибуты
 * После чего переходит на /employee/list.jsp
 *
 * @author Kovzov Denis
 */
public class EmployeeListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreatorImpl()) {
            EmployeeService employeeService = creator.getEmployeeService();

            List<Employee> employees = employeeService.findAll();

            req.setAttribute("employees", employees);

            req.getRequestDispatcher("/WEB-INF/jsp/employee/list.jsp").forward(req, resp);
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new ServletException(e);
        }
    }
}


