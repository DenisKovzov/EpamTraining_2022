package vsu.by.controller.filter;

import vsu.by.models.Role;
import vsu.by.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Фильтер вызывающийся при любов переходе и
 * предназначенный для проверки доступа пользователя к страницам.
 *
 * Если пользователю с его ролью разрешено то он проходит проверку иначе
 * переход на контекст
 *
 * @author Kovzov Denis
 */
public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));

        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);

        Set<Role> dispatcher = new HashSet<>();
        dispatcher.add(Role.DISPATCHER);

        permissions.put("/logout", all);
        permissions.put("/index", all);
        permissions.put("/user/edit", all);
        permissions.put("/user/save", all);

        permissions.put("/user/list", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/employee/list", admin);
        permissions.put("/employee/edit", admin);
        permissions.put("/employee/save", admin);
        permissions.put("/employee/delete", admin);

        permissions.put("/flight/list", admin);
        permissions.put("/flight/edit", admin);
        permissions.put("/flight/save", admin);
        permissions.put("/flight/delete", admin);


        permissions.put("/brigade/list", dispatcher);
        permissions.put("/brigade/edit", dispatcher);
        permissions.put("/brigade/save", dispatcher);
        permissions.put("/brigade/delete", dispatcher);


    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();

        int lastIndex = url.lastIndexOf(".html");

        if (lastIndex != -1) {
            url = url.substring(context.length(), lastIndex);
        } else {
            url = url.substring(context.length());
        }

        Set<Role> roles = permissions.get(url);
        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("session_user");
                if (user != null && roles.contains(user.getRole())) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        httpResp.sendRedirect(context);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
