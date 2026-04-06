<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Product</title>
</head>
<body>
    <h2>Search Product by ID</h2>
    
    <%
        if(request.getParameter("notfound") != null) {
            out.println("<h3 style='color:red'>Product not found!</h3>");
        }
        if(request.getParameter("error") != null) {
            out.println("<h3 style='color:red'>Error searching product!</h3>");
        }
    %>
    
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="search">
        Product ID: <input type="number" name="id" required>
        <input type="submit" value="Search">
    </form>
    <br>
    <a href="index.jsp">Back to Menu</a>
</body>
</html>