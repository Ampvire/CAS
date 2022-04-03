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
    <form action="../admin/allUsers" method="get">
        <button class="btn" type="submit">Все пользователи</button>
    </form>
    <form action="../admin/delete" method="get">
        <button class="btn">Удалить пользователя</button>
    </form>
    <form action="../../../admin/info" method="get">
        <button class="btn" type="submit">На главную</button>
    </form>
    <form action="../../logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
</div>
<div class="divSecondBody">
    <div class="input-form">
        <h2>Создание пользователя</h2>
        <form action="update" method="post">
            <div>
                <label for="login" class="col-25">Логин:</label>
                <input type="text" id="login" name="login" class="col-75">
            </div>
            <div>
                <label for="name" class="col-25">Имя:</label>
                <input type="text" id="name" name="firstName" class="col-75">
            </div>
            <div>
                <label for="secondName" class="col-25">Фамилия:</label>
                <input type="text" id="secondName" name="secondName" class="col-75">
            </div>
            <div>
                <label for="password" class="col-25">Password:</label>
                <input type="text" id="password" name="password" class="col-75">
            </div>
            <div>
                <label for="category" class="col-25">Категория:</label>
                <select id="category" name="category" class="col-75">
                    <c:forEach items="${categories}" var="cat">
                        <option>${cat.category}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="role" class="col-25">Роль:</label>
                <select id="role" name="role" class="col-75">
                    <c:forEach items="${roles}" var="r">
                        <option>${r.role}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="submit" value="Создать" style="width: 400px">
            </div>
        </form>
    </div>
</div>
</body>
</html>