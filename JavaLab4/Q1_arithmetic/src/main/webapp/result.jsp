<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Calculation Result</title>
</head>
<body>
    <h2>Calculation Results</h2>
    
    <%
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");
        
        double a = Double.parseDouble(n1);
        double b = Double.parseDouble(n2);
        
        double sum = a + b;
        double sub = a - b;
        double mul = a * b;
    %>
    
    <h3>First Number: <%= a %></h3>
    <h3>Second Number: <%= b %></h3>
    <hr>
    <h3>Addition: <%= a %> + <%= b %> = <%= sum %></h3>
    <h3>Subtraction: <%= a %> - <%= b %> = <%= sub %></h3>
    <h3>Multiplication: <%= a %> * <%= b %> = <%= mul %></h3>
    <h3>Division: 
        <% 
            if(b != 0) {
                double div = a / b;
                out.print(a + " / " + b + " = " + div);
            } else {
                out.print("Cannot divide by zero!");
            }
        %>
    </h3>
    <br>
    <a href="index.jsp">Calculate Again</a>
</body>
</html>