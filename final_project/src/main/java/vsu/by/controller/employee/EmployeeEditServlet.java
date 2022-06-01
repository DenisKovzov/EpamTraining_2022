package vsu.by.controller.employee;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Employee;
import vsu.by.models.Position;
import vsu.by.models.User;
import vsu.by.factories.ServiceCreator;
import vsu.by.factories.ServiceCreatorImpl;
import vsu.by.services.EmployeeService;
import vsu.by.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Получает id, вслучаи валидности устанавливает в атрибуты работника
 * с этим id, иначе всех сотрудников, которые не являются работниками
 * После чего переходит на /employee/edit.jsp
 *
 * @author Kovzov Denis
 */
public class EmployeeEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {
            if (id != null) {
                EmployeeService employeeService = serviceCreator.getEmployeeService();
                Employee employee = employeeService.findById(id);

                req.setAttribute("employee", employee);
            } else {
                UserService userService = serviceCreator.getUserService();
                List<User> users = userService.findAllIsNotEmployee();

                req.setAttribute("users", users);
            }
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            throw new RuntimeException(e);
        }

        req.setAttribute("positions", Position.values());
        req.getRequestDispatcher("/WEB-INF/jsp/employee/edit.jsp").forward(req, resp);
    }
}
