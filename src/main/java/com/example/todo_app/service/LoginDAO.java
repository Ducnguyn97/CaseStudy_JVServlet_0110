package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement
                    ("SELECT * FROM users WHERE username = ? AND password = ?")){
            ps.setString(1, loginBean.getUsername());
            ps.setString(2, loginBean.getPassword());
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}
