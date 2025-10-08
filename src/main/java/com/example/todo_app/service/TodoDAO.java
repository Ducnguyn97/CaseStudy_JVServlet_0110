package com.example.todo_app.service;

import com.example.todo_app.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDAO {
    void insertTodo(Todo todo) throws SQLException;
    Todo selectTodo(long todoId);
    List<Todo> selectAllTodoByUser(String username);
    boolean deleteTodo(long todoId) throws SQLException;
    boolean updateTodo(Todo todo) throws SQLException;
 }
