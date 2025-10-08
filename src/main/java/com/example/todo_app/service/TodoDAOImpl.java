package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOImpl implements TodoDAO {
    private static final String INSERT_TODOS_SQL =
            "INSERT INTO todos (title, assign,owner_username, description, target_date, is_done) VALUES (?, ?, ?, ?, ?,?)";
    private static final String SELECT_TODOS_BY_ID_SQL =
            "SELECT * FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS_SQL =
            "SELECT * FROM todos WHERE owner_username = ?";
    private static final String DELETE_TODOS_SQL =
            "DELETE FROM todos WHERE id = ?";
    private static final String UPDATE_TODOS_SQL =
            "UPDATE todos SET title = ?, assign = ?,owner_username =?, description = ?, target_date = ?, is_done = ? WHERE id = ?";

    public TodoDAOImpl() {
    };

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        // try-with-resource statement will auto close the connection
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_TODOS_SQL)){
            ps.setString(1,todo.getTitle());//gan ? thu nhat
            ps.setString(2,todo.getAssign());
            ps.setString(3,todo.getOwnerUsername());
            ps.setString(4,todo.getDescription());
            ps.setDate(5,JDBCUtils.getSQLDate(todo.getTargetDate()));
            ps.setBoolean(6, todo.getStatus());
            System.out.println(ps);
            ps.executeUpdate();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }
    @Override
    public Todo selectTodo(long todoId) {
        Todo todo = null;
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_TODOS_BY_ID_SQL)){
            ps.setLong(1,todoId);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                long  id = rs.getLong("id");
                String title = rs.getString("title");
                String assign = rs.getString("assign");
                String ownerUsername = rs.getString("owner_username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new Todo(id, title, assign, ownerUsername,description, targetDate, isDone);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return todo;
    }

    @Override
    public List<Todo> selectAllTodoByUser(String owner) {
        List<Todo> todos = new ArrayList<>();
        try(Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_ALL_TODOS_SQL)) {
            ps.setString(1,owner);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String assign = rs.getString("assign");
                String ownerUsername = rs.getString("owner_username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, title, assign, ownerUsername, description, targetDate, isDone));
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return todos;
    }

    @Override
    public boolean deleteTodo(long todoId) throws SQLException {
        boolean rowDeteled;
        try(Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_TODOS_SQL)){
            ps.setLong(1,todoId);
            System.out.println(ps);
           rowDeteled = ps.executeUpdate() > 0;
        }
        return rowDeteled;
    }

    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;

        try(Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE_TODOS_SQL)){
            ps.setString(1,todo.getTitle());
            ps.setString(2,todo.getAssign());
            ps.setString(3,todo.getOwnerUsername());
            ps.setString(4,todo.getDescription());
            ps.setDate(5,JDBCUtils.getSQLDate(todo.getTargetDate()));
            ps.setBoolean(6, todo.getStatus());
            ps.setLong(7,todo.getId());
            System.out.println(ps);

            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
