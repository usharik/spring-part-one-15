package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/*

GET /servlet-app/first_servlet 1.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp
Header-name: header-value; sdgsd; sdfsdfsd;

<h1>fsdfsdfsdfsdg<h1>

 */
@WebServlet(urlPatterns = "/first_servlet")
public class FirstServlet implements Servlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println("<h1>Привет от сервлета!!!</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
