<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Ошибка</title>
     <link rel="stylesheet" type="text/css" href="/style.css">
 </head>
<body>
<div class="main-wrapper">
<div class="sidenav">
<%--    <form action="/user/info" method="get">--%>
<%--        <button class="btn">Мои клиенты</button>--%>
<%--    </form>--%>
    <form action="/logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
</div>
<div class="divSecondBody">
    <article class="elem-wrapper">
    <div style="width: 300px; display: flex">
        <h2>${message}</h2>
    </div>
    </article>
</div>
    </div>
</body>
</html>