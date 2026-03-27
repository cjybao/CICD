package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableClass {

    // 高危漏洞：SQL 注入（直接拼接用户输入）
    public void vulnerableMethod(String userInput) throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "password";

        // 危险：直接拼接用户输入到 SQL 语句
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 从命令行参数获取用户输入（模拟外部输入）
        if (args.length < 1) {
            System.out.println("Usage: java VulnerableClass <username>");
            return;
        }
        String userInput = args[0];   // 外部输入：用户通过命令行提供

        VulnerableClass vc = new VulnerableClass();
        vc.vulnerableMethod(userInput);
    }
}
