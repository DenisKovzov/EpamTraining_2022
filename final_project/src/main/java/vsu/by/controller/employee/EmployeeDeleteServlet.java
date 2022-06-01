package vsu.by.controller.employee;

import org.apache.logging.log4j.LogManager;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Получает список id работников и если id валидны, то удаляет их.
 * После чего переходит на страницу с адресом
 * /employee/list.html
 *
 * @author Kovzov Denis
 */
public class EmployeeDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");

        if (idsStr != null) {

            List<Long> ids = new ArrayList<>(idsStr.length);
            try (ServiceCreator creator = new ServiceCreatorImpl()) {
                for (String id : idsStr) {
                    ids.add(Long.valueOf(id));
                }
                EmployeeService employeeService = creator.getEmployeeService();
                employeeService.delete(ids);
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
