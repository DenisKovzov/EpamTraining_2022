package vsu.by.controller.brigade;

import org.apache.logging.log4j.LogManager;
import vsu.by.models.Brigade;
import vsu.by.models.Employee;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Получает id бригады и новый список работников, после проверки
 * сохраняет бригаду.
 * После чего переходит на страницу /brigade/list.html
 *
 * @author Kovzov Denis
 */
public class BrigadeSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        List<Long> employeeIds = null;
        String name = req.getParameter("name");

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try {
            String[] ids = req.getParameterValues("employeeId");
            if (ids != null) {
                employeeIds = Arrays.stream(ids).map(Long::valueOf).collect(Collectors.toList());
            } else {
                employeeIds = new ArrayList<>();
            }

        } catch (NumberFormatException e) {
        }

        if (name != null && !name.trim().isEmpty() && employeeIds != null) {
            try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {
                BrigadeService brigadeService = serviceCreator.getBrigadeService();
                Brigade brigade = new Brigade();

                brigade.setId(id);
                brigade.setName(name);

                List<Employee> employees = serviceCreator.getEmployeeService().findAllByIds(employeeIds);
                brigade.setEmployees(employees);

                brigadeService.save(brigade);
            } catch (RuntimeException | ServiceException e) {
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
