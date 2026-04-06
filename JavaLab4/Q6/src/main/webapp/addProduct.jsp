<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Product</title>
</head>
<body>
    <h2>Add New Product</h2>
    
    <%
        String error = request.getParameter("error");
        if(error != null) out.println("<h3 style='color:red'>Error adding product!</h3>");
    %>
    
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="add">
        Product Name: <input type="text" name="name" required><br><br>
        Price: <input type="number" step="0.01" name="price" required><br><br>
        Quantity: <input type="number" name="quantity" required><br><br>
        <input type="submit" value="Add Product">
        <a href="index.jsp">Cancel</a>
    </form>
</body>
</html>