package com.example.todo_app.service;

import com.example.todo_app.DBconnect.JDBCUtils;
import com.example.todo_app.model.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {//kiem tra dang nhap
        boolean status = false;//trang thai dang nhap

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement
                    ("SELECT * FROM users WHERE username = ? AND password = ?")){
            ps.setString(1, loginBean.getUsername());//gan gia tri cho ? thư nhat
            ps.setString(2, loginBean.getPassword());//gan gia tri cho ? thu hai
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();//thuc hien tra ve ket qua cua ps
            status = rs.next();//kiem tra xem rs co chua du lieu

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}
