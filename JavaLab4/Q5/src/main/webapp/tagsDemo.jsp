<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP Tags Demo</title>
</head>
<body>

    <h1>JSP Tags Demonstration</h1>

    <!-- 1. DIRECTIVE TAG -->
    <h2>1. Directive Tag</h2>
    <p>Line 1: <%@ page language="java" %></p>
    <hr>

    <!-- 2. DECLARATION TAG -->
    <h2>2. Declaration Tag</h2>
    <%! 
        String message = "Hello World"; 
    %>
    <p>Message: <%= message %></p>
    <hr>

    <!-- 3. SCRIPTLET TAG -->
    <h2>3. Scriptlet Tag</h2>
    <%
        for(int i=1; i<=5; i++) {
            out.println(i + " ");
        }
    %>
    <hr>

    <!-- 4. EXPRESSION TAG -->
    <h2>4. Expression Tag</h2>
    <p>Today's Date: <%= new java.util.Date() %></p>
    <hr>

    <!-- 5. ACTION TAGS -->
    <h2>5. Action Tags</h2>
    
    <p>jsp:include - Include date.jsp:</p>
    <jsp:include page="date.jsp" />
    
    <p>jsp:forward </p>
  <jsp:forward page="param.jsp" /> 

</body>
</html>