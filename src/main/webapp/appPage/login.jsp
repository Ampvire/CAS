<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ page contentType="text/html;charset=utf-8" %>--%>

<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Авторизация</title>--%>
<%--&lt;%&ndash;    <link rel="stylesheet" type="text/css" href="/main.css">&ndash;%&gt;--%>
<%--</head>--%>
<%--<body>--%>
<%--<sec:authorize access="isAuthenticated()">--%>
<%--    <% response.sendRedirect("/"); %>--%>
<%--</sec:authorize>--%>
<%--<div id="autorisation">--%>
<%--    <div class="outer">--%>
<%--        <div class="middle">--%>
<%--            <div class="inner">--%>
<%--                <div class="login-wr">--%>
<%--                    <h2>Войти в систему</h2>--%>
<%--                    <form class="form" action="/login" method="post">--%>
<%--                        <input name="username" type="text" placeholder="Логин">--%>
<%--                        <input name="password" type="password" placeholder="Пароль">--%>
<%--                        <button class="button"> Авторизация </button>--%>
<%--                    </form>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <button type="submit">Log In</button>
            <h4><a href="/registration">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>

</body>
</html>