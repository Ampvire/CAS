<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <c:if test="${pageContext.request.isUserInRole('Admin')}">
        <% response.sendRedirect("/admin/allUsers"); %>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('Meneger')}">
        <% response.sendRedirect("/user/info"); %>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('Client')}">
        <% response.sendRedirect("/account"); %>
    </c:if>
</body>
</html>