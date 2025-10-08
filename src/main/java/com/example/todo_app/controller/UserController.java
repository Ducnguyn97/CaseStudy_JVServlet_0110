package com.example.todo_app.controller;

import com.example.todo_app.model.User;
import com.example.todo_app.service.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

@WebServlet(name = "UserController", urlPatterns = "/register")
public class UserController extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        register(request, response);
    }
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
       response.sendRedirect("register/register.jsp");
    }
    private void register(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException{
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String error = validateRegister(firstName,lastName,username,password);

        if(error != null){
            request.setAttribute("error",error);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register/register.jsp");
            rd.forward(request,response);
            return;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        try {
            int result = userDAO.registerUser(user);

            if(result == 1){
                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("NOTIFICATION", "User registration failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }
    private boolean isEmpty(String value) {
        return value == null|| value.trim().isEmpty();
    }
    private String validateRegister(String firstName, String lastName,
                                    String username, String password) {
        if (isEmpty(firstName) || isEmpty(username) || isEmpty(password)||isEmpty(lastName)) {
            return "All fields are required";
        }
        if(password.length() < 6) {
            return "Password length should be at least 6 characters";
        }
        try{
            if(userDAO.checkUsernameExists(username)){
                return "Username already exists";
            }
        }catch(SQLException e){
            e.printStackTrace();
            return "Error checking username in database";
        }

        return null;
    }

}
