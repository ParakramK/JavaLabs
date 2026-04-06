<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Student Records</title>
</head>
<body>
    <h2>Student Records from Database</h2>
    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "root");
          	Statement stmt = conn.createStatement();
          	ResultSet rs = stmt.executeQuery("SELECT * FROM students");
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Roll</th>
            <th>Faculty</th>
            <th>Email</th>
        </tr>
        
        <% while(rs.next()) { %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("roll") %></td>
            <td><%= rs.getString("faculty") %></td>
            <td><%= rs.getString("email") %></td>
        </tr>
        <% }
        rs.close();
        stmt.close();
        conn.close();
        %>
    </table>
    <%
        } catch(Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } 
    %>
</body>
</html>