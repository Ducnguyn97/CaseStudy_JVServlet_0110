package com.example.todo_app.service;

import com.example.todo_app.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDAO {
    void insertTodo(Todo todo) throws SQLException;
    Todo selectTodo(long todoId);
    List<Todo> selectAllTodo();
    boolean deleteTodo(int todoId) throws SQLException;
    boolean updateTodo(Todo todo) throws SQLException;
 }
