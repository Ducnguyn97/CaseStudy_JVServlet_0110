package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TodoDAOImpl implements TodoDAO {
    private static final String INSERT_TODOS_SQL =
            "INSERT INTO todos (title, username, description, targetDate, status) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TODOS_BY_ID_SQL =
            "SELECT * FROM todos WHERE ID = ?";
    private static final String SELECT_ALL_TODOS_SQL =
            "SELECT * FROM todos";
    private static final String DELETE_TODOS_SQL =
            "DELETE FROM todos WHERE id = ?";
    private static final String UPDATE_TODOS_SQL =
            "UPDATE todos SET title = ?, username = ?, description = ?, targetDate = ?, status = ? WHERE id = ?";

    public TodoDAOImpl() {
    };

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        // try-with-resource statement will auto close the connection
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_TODOS_SQL)){
            ps.setString(1,todo.getTitle());
            ps.setString(2,todo.getUsername());
            ps.setString(3,todo.getDescription());
            ps.setDate(4,JDBCUtils.getSQLDate(todo.getTargetDate()));
            ps.setBoolean(5, todo.getStatus());
            System.out.println(ps);
            ps.executeUpdate();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }
    @Override
    public Todo selectTodo(long todoId) {
        return null;
    }

    @Override
    public List<Todo> selectAllTodo() {
        return List.of();
    }

    @Override
    public boolean deleteTodo(int todoId) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        return false;
    }
}
