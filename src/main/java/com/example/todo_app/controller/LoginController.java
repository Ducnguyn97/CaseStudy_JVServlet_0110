package com.example.todo_app.controller;

import com.example.todo_app.model.LoginBean;
import com.example.todo_app.service.LoginDAO;
import com.example.todo_app.service.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private LoginDAO loginDAO;
    private UserDAO userDAO;

    public void init(){
        loginDAO = new LoginDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.sendRedirect("login/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authenticate(request,response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = validateLogin(username,password);

        if(error != null){
            request.setAttribute("error",error);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login/login.jsp");
            rd.forward(request,response);
            return;
        }

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try{
            if (loginDAO.validate(loginBean)){
                //tao session khi login thanh cong
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath() + "/todos?action=list");
            }else{
                request.setAttribute("error","Invalid username or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                dispatcher.forward(request,response);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private boolean isEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    private String validateLogin(String username, String password){
        if(isEmpty(username) || isEmpty(password)){
            return "Username or password are required";
        }
        try{
            if(!userDAO.checkUsernameExists(username)){
                return "Username does not exist";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
