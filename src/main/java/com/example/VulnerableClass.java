package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableClass {

    // 高危漏洞：SQL 注入，再次测试，再再次测试
    public void vulnerableMethod(String userInput) throws Exception {
        // 假设连接字符串，实际中应从配置获取
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
        VulnerableClass vc = new VulnerableClass();
        // 模拟用户输入
        vc.vulnerableMethod("admin' OR '1'='1");
    }
}
