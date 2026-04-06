<%@page import="servlet.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Product</title>
</head>
<body>
    <h2>Update Product</h2>
    
    <%
        Product p = (Product) request.getAttribute("product");
        if(p == null) {
            response.sendRedirect("ProductServlet?action=list");
            return;
        }
    %>
    
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= p.getId() %>">
        
        Product Name: <input type="text" name="name" value="<%= p.getName() %>" required><br><br>
        Price: <input type="number" step="0.01" name="price" value="<%= p.getPrice() %>" required><br><br>
        Quantity: <input type="number" name="quantity" value="<%= p.getQuantity() %>" required><br><br>
        
        <input type="submit" value="Update Product">
        <a href="ProductServlet?action=list">Cancel</a>
    </form>
</body>
</html>