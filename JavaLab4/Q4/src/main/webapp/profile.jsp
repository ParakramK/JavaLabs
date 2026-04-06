<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
</head>
<body>
    <h2>User Profile</h2>
    <%
        // Get from Session
        String sessionUser = (String) session.getAttribute("user");
        
        // Get from Cookie
        String cookieUser = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie ck : cookies) {
                if(ck.getName().equals("userCookie")) {
                    cookieUser = ck.getValue();
                    break;
                }
            }
        }
    %>
    
    <h3>Retrieved Data:</h3>
    <p>From Session: <%= sessionUser %></p>
    <p>From Cookie: <%= cookieUser %></p>
    <p>Session ID: <%= session.getId() %></p>
    <br>
    <a href="login.jsp">Back</a>
    <a href="logout.jsp">Logout</a>
</body>
</html>