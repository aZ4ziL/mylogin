/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fajhri.mylogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fajhri
 */
public class Models {
    Connection conn;
    ResultSet result;
    PreparedStatement pstmt;
    
    public Models() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/login-javafx", "root", "root");
            System.out.println("Connection DB Successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Create new user to the database
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param password 
     * @return  
     * @throws java.sql.SQLException  
     */
    public Integer createNewUser(String firstName, String lastName, String username, String email, String password) throws SQLException {
        Integer success;
        String sql = "INSERT INTO users (first_name, last_name, username, email, password) VALUES (?, ?, ?, ?, ?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, username);
        pstmt.setString(4, email);
        pstmt.setString(5, password);
        success = pstmt.executeUpdate();
        return success;
    }
    
    /**
     * Get user by passing the `username`
     * @param username
     * @return
     * @throws SQLException 
     */
    public ResultSet getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        result = pstmt.executeQuery();
        return result;
    }
}
