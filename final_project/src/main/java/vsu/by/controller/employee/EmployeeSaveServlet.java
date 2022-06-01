package vsu.by.controller.employee;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Employee;
import vsu.by.models.Position;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.EmployeeService;
import vsu.by.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Получает id работника, должность и id бригады , после проверки
 * сохраняет работника.
 * После чего переходит на страницу /employee/list.html
 *
 * @author Kovzov Denis
 */
public class EmployeeSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Position position = null;
        Long brigadeId = null;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try {
            position = Position.valueOf(req.getParameter("position"));
            brigadeId = Long.valueOf(req.getParameter("brigadeId"));
        } catch (NumberFormatException e) {
        }


        if (position != null) {

            Employee employee = new Employee();
            employee.setId(id);
            employee.setPosition(position);
            employee.setBrigadeId(brigadeId);

            try (ServiceCreator creator = new ServiceCreatorImpl()) {
                EmployeeService employeeService = creator.getEmployeeService();

                employeeService.save(employee);

            } catch (RuntimeException | ServiceException e) {
                resp.sendRedirect(req.getContextPath() + "/employee/list.html?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
                LogManager.getLogger().warn(e);
                return;
            } catch (Exception e) {
                LogManager.getLogger().error(e);
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/employee/list.html");
    }
}
