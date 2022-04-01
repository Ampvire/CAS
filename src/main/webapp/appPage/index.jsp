<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ page contentType="text/html;charset=utf-8" %>--%>

<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Вход в систему</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"--%>
<%--            crossorigin="anonymous"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<sec:authorize access="!isAuthenticated()">--%>
<%--    <h4><a href="/login">Войти</a></h4>--%>
<%--    <h4><a href="/registration">Зарегистрироваться</a></h4>--%>
<%--</sec:authorize>--%>
<%--<sec:authorize access="isAuthenticated()">--%>

<%--<ul class="nav justify-content-end">--%>
<%--    <li class="nav-item">--%>
<%--        <a class="nav-link active" aria-current="page" href="/admin">Admin</a>--%>
<%--    </li>--%>
<%--</ul>--%>

<%--<div class="container-fluid d-flex h-100 p-0 justify-content-center align-items-center">--%>
<%--    <div class="row bg-white shadow w-25 p-0 m-0">--%>
<%--        <div class="col border rounded p-4">--%>
<%--            <h3 class="text-center mb-4">Выберите вариант входа в систему</h3>--%>
<%--            <div class="mx-auto w-75 m-2">--%>
<%--                <a href="/user/info" class="btn btn-primary  w-100 shadow-sm">Войти как сотрудник</a>--%>
<%--            </div>--%>
<%--            <div class="mx-auto w-75 m-2">--%>
<%--                <a href="/account" class="btn btn-primary w-100 shadow-sm">Войти как клиент</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%--    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">--%>
</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <c:if test="${pageContext.request.isUserInRole('Admin')}">
<%--        User ${pageContext.request.userPrincipal.name} in ADMIN Group--%>
        <% response.sendRedirect("/admin"); %>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('Meneger')}">
<%--        User ${pageContext.request.userPrincipal.name} in Meneger Group--%>
        <% response.sendRedirect("/user/info"); %>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('Client')}">
<%--        User ${pageContext.request.userPrincipal.name} in Client Group--%>
        <% response.sendRedirect("/account"); %>
    </c:if>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
    <h4><a href="/user/info">Новости (только пользователь)</a></h4>
    <h4><a href="/account">Пользователи (только админ)</a></h4>
</div>
</body>
</html>