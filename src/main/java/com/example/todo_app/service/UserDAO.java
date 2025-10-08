package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public int registerUser(User user) throws ClassNotFoundException{
        String INSERT_USER_QUERY = "INSERT INTO users(first_name, last_name, username, password) VALUES (?,?,?,?)";
        int result= 0;
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_USER_QUERY)){
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            System.out.println(ps);

            result = ps.executeUpdate();

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return result;
    }

    public boolean checkUsernameExists(String username) throws SQLException {
        String COUNT_USERNAME_QUERY = "SELECT COUNT(*) FROM users WHERE username = ?";
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(COUNT_USERNAME_QUERY)){
            ps.setString(1, username);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) >0;// tra ve true neu ton tai
            }
        }
        return false;
    }
}
