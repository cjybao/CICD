package com.example;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableClass {
    public void doGet(HttpServletRequest request) throws Exception {
        String userInput = request.getParameter("name");  // 污点源
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "password";
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }
        }
    }
}
