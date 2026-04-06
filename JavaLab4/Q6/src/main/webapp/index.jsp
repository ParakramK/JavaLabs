<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Management System</title>
</head>
<body>
    <h1>Product Management System</h1>
    
    <%
        String msg = request.getParameter("msg");
        if(msg != null) {
            if(msg.equals("added")) out.println("<h3 style='color:green'>Product Added Successfully!</h3>");
            if(msg.equals("updated")) out.println("<h3 style='color:green'>Product Updated Successfully!</h3>");
            if(msg.equals("deleted")) out.println("<h3 style='color:green'>Product Deleted Successfully!</h3>");
        }
    %>
    
    <ul>
        <li><a href="ProductServlet?action=add">Add New Product</a></li>
        <li><a href="ProductServlet?action=list">Display All Products</a></li>
        <li><a href="searchProduct.jsp">Search/Update Product</a></li>
        <li><a href="ProductServlet?action=deleteform">Delete Product</a></li>
    </ul>
</body>
</html>