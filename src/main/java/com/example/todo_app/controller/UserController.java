package com.example.todo_app.controller;

import com.example.todo_app.model.User;
import com.example.todo_app.service.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "UserController", urlPatterns = "/register")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
    }
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
       response.sendRedirect("register/register.jsp");
    }
    private void register(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        try {
            int result = userDAO.registerUser(user);

            if(result ==1){
                RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
                dispatcher.forward(request, response);
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("message", "User registration failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
