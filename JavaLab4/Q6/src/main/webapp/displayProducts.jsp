<%@page import="servlet.Product"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product List</title>
</head>
<body>
    <h2>All Products</h2>
    
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        
        <%
            List<Product> list = (List<Product>) request.getAttribute("productList");
            if(list != null) {
                for(Product p : list) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
            <td><%= p.getQuantity() %></td>
            <td>
                <a href="ProductServlet?action=edit&id=<%= p.getId() %>">Edit</a>
                <a href="ProductServlet?action=delete&id=<%= p.getId() %>" 
                   onclick="return confirm('Delete this product?')">Delete</a>
            </td>
        </tr>
        <% 
                }
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Back to Menu</a>
</body>
</html>