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
        <form action="newUser" method="get">
            <button class="btn">Создать пользователя</button>
        </form>
        <form action="delete" method="get">
            <button class="btn">Перевести в архив</button>
        </form>
        <form action="allUsers" method="get">
            <button class="btn">Активные пользователи</button>
        </form>
        <form action="../../logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <article class="elem-wrapper">
            <h2>Список всех неактивных пользователей</h2>
            <div class="table-block">

                    <table class="myTable">
                        <colgroup>
                            <col span="6" style="background: white;">
                        </colgroup>
                        <tr>
                            <th>ID</th>
                            <th>ЛОГИН</th>
                            <th>ИМЯ</th>
                            <th>ФАМИЛИЯ</th>
                            <th>КАТЕГОРИЯ</th>
                            <th>РОЛЬ</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${list}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.categoryId.category}</td>
                                <td>${user.roleId.role}</td>
                                <td>
                                    <form action="updateUser/${user.login}" method="get">
                                        <button class="myTableButton">Обновить</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
            </div>
        </article>
    </div>
</div>
</body>
</html>