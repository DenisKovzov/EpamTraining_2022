package vsu.by.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтер вызывающийся при любов переходе и
 * предназначенный для установки кодировки на основе параметра
 *
 * @author Kovzov Denis
 */
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {

    }
}
