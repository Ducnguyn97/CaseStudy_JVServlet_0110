package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public int registerUser(User user) throws ClassNotFoundException{
        String INSERT_USER_QUERY = "INSERT INTO users(firstName, lastName, username, password) VALUES (?,?,?,?)";
        int result= 0;
        try(Connection conn = JDBCUtils.getConnection();
            // Step 2: Create a statement using a connection object
            PreparedStatement ps = conn.prepareStatement(INSERT_USER_QUERY)){
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            System.out.println(ps);
            // Step 3: Execute the query or update query

            result = ps.executeUpdate();

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
