<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Ошибка</title>
     <link rel="stylesheet" type="text/css" href="/style.css">
 </head>
<body>
<div class="sidenav">
    <form action="/user/info" method="get">
        <button class="btn">Мои клиенты</button>
    </form>
    <form action="/user/newClient" method="get">
        <button class="btn">Создать клиента</button>
    </form>
    <form action="/user/application" method="get">
        <button class="btn">Заявки на продукты Банка</button>
    </form>
    <form action="/logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
</div>
<div class="divSecondBody">
    <div style="width: 300px; display: flex">
        <h2>${message}</h2>
    </div>
</div>
</body>
</html>