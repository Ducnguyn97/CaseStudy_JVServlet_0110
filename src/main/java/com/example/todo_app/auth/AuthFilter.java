package com.example.todo_app.auth;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//Filter URLs that need to be protected
@WebFilter(urlPatterns = {"/todos/*", "/todo/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Get current session, false means do not create new session if not already there
        HttpSession session = request.getSession(false);
        // Check login status: session and username attributes
        boolean loggedIn = (session!= null && session.getAttribute("username")!= null);
        if (loggedIn) {
            //Login then allow continue to todoController
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
