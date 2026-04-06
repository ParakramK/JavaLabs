<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = request.getParameter("username");
    
    if(name != null) {
        // Store in Session
        session.setAttribute("user", name);
        
        // Store in Cookie
        Cookie c = new Cookie("userCookie", name);
        c.setMaxAge(60*60*24); // 24 hours
        response.addCookie(c);
    }
    response.sendRedirect("profile.jsp");
%>