package com.example.todo_app.controller;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.Todo;
import com.example.todo_app.service.TodoDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TodoController", urlPatterns = "/todos")
public class TodoController extends HttpServlet {
    private TodoDAOImpl todoDAOImpl;

    public void init() {
        todoDAOImpl = new TodoDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertTodo(request, response);
                    break;
                case "edit":
                    updateTodo(request, response);
                    break;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
                    deleteItem(request, response);
                    break;
                case "list":
                    listItems(request, response);
                    break;
                default:
                   RequestDispatcher disDispatcher = request.getRequestDispatcher("login/login");
                   disDispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String ownerUsername = (String) request.getSession().getAttribute("username");
        String title = request.getParameter("title");
        String assign = request.getParameter("assign");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String error = validateTodoData(title, String.valueOf(targetDate));
        if (error != null) {
            request.setAttribute("error", error);
            RequestDispatcher disDispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
            disDispatcher.forward(request, response);
            return;
        }
        Todo todo = new Todo(title, assign,ownerUsername, description, targetDate, status);
        todoDAOImpl.insertTodo(todo);
        response.sendRedirect(request.getContextPath() + "/todos?action=list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String assign = request.getParameter("assign");
        String ownerUsername = (String) request.getSession().getAttribute("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String error = validateTodoData(title, String.valueOf(targetDate));
        if (error != null) {
            request.setAttribute("error", error);
            RequestDispatcher disDispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
            disDispatcher.forward(request, response);
            return;
        }
        Todo todo = new Todo(id, title, assign,ownerUsername, description, targetDate, status);
        todoDAOImpl.updateTodo(todo);
        response.sendRedirect(request.getContextPath() + "/todos?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Todo existingTodo = todoDAOImpl.selectTodo(id);
        request.setAttribute("todo", existingTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        long id = Long.parseLong(request.getParameter("id"));
        todoDAOImpl.deleteTodo(id);
        response.sendRedirect(request.getContextPath() + "/todos?action=list");
    }
    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String loggedInUser = (session != null) ? (String) session.getAttribute("username") : null;//lay username tu session
        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        List<Todo> todos = todoDAOImpl.selectAllTodoByUser(loggedInUser);
        request.setAttribute("listTodo", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }
    private boolean isEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    private String validateTodoData(String title, String targetDateStr){
        if(isEmpty(title)){
            return "Title or targetDate is required";
        }else if(title.length() < 5){
            return "Title length should be at least 5";
        }else if(title.length() > 100){
            return "Title length should be at most 100";
        }
        if(isEmpty(targetDateStr)){
            return "TargetDate is required";
        }else{
            LocalDate targetDate = LocalDate.parse(targetDateStr);
            LocalDate today = LocalDate.now();
            if(targetDate.isBefore(today)){
                return "Target date cannot be in the past";
            }
        }
        return null;
    }
}
