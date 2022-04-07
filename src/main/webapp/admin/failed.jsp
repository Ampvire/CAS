<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<div class="main-wrapper">
    <div class="sidenav">
       <form action="newUser" method="get">
           <button class="btn">Создать пользователя</button>
       </form>
       <form action="delete" method="get">
           <button class="btn">Перевести в архив</button>
       </form>
        <form action="../admin/allUsers" method="get">
            <button class="btn" type="submit">Aктивные пользователи</button>
        </form>
        <form action="inactive" method="get">
            <button class="btn">Неактивные пользователи</button>
        </form>
        <form action="../admin/allUsers" method="get">
            <button class="btn" type="submit">Все пользователи</button>
        </form>
        <form action="../../../logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
</div>
<div class="divSecondBody">
    <div class="elem-wrapper">
        <div style="width: 300px; display: flex">
            <h2>${message}</h2>
        </div>
    </div>
</div>
</div>
</body>
</html>