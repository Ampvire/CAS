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
<div class="main-wrapper">
    <div class="sidenav">
        <form action="../../admin/allUsers" method="get">
            <button class="btn">Назад</button>
        </form>
        <form action="../../logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <div class="input-form">
            <h2>Обновление данных пользователя</h2>
            <form action="success" method="post">
                <div>
                    <label for="login" class="col-25">Логин:</label>
                    <input type="text" id="login" name="login" value="${login}" class="col-75">
                </div>
                <div>
                    <label for="name" class="col-25">Имя:</label>
                    <input type="text" id="name" name="firstName" value="${name}" class="col-75">
                </div>
                <div>
                    <label for="secondName" class="col-25">Фамилия:</label>
                    <input type="text" id="secondName" name="secondName" value="${secondName}" class="col-75">
                </div>
                <div>
                    <label for="category" class="col-25">Категория:</label>
                    <select id="category" name="category" class="col-75">
                        <c:forEach items="${category}" var="cat">
                            <option>${cat.category}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="role" class="col-25">Роль:</label>
                    <select id="role" name="role" class="col-75">
                        <c:forEach items="${role}" var="r">
                            <option>${r.role}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <input type="submit" value="Обновить" style="width: 400px">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>