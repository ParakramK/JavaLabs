<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Delete Product</title>
</head>
<body>
    <h2>Delete Product by ID</h2>
    
    <form action="ProductServlet" method="get">
        <input type="hidden" name="action" value="delete">
        Product ID: <input type="number" name="id" required>
        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete?')">
    </form>
    <br>
    <a href="index.jsp">Back to Menu</a>
</body>
</html>