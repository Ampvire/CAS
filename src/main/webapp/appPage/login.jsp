<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Вход в систему</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="wrapper-input-form">
    <div class="input-form">
        <form method="POST" action="/login">
            <h2 class="form-title">Вход в систему</h2>
            <div class="form-body">
                <input class="text-field" name="username" type="text" placeholder="Введите логин"
                       autofocus="true"/>
                <input class="text-field" name="password" type="password" placeholder="Введите пароль"/>
                <input type="submit" value="Войти" style="width: 334px; height: 47px;">
            </div>
        </form>
    </div>
</div>


</body>
</html>