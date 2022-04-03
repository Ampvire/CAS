<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Admin panel</title>
</head>
<body>
<div class="sidenav">
    <form action="/admin/allUsers" method="get">
        <button class="btn" type="submit">Все пользователи</button>
    </form>
    <form action="/admin/delete" method="get">
        <button class="btn">Удалить пользователя</button>
    </form>
    <form action="/admin/newUser" method="get">
        <button class="btn">Создать пользователя</button>
    </form>
    <form action="../logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
</div>
<div class="divSecondBody">
</div>
</body>
</html>