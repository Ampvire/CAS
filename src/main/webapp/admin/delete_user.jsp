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
        <form action="../admin/allUsers" method="get">
            <button class="btn" type="submit">Aктивные пользователи</button>
        </form>
        <form action="../admin/inactive" method="get">
            <button class="btn">Неактивные пользователи</button>
        </form>
        <form action="../../logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <div class="input-form">
            <h2>Поместить пользователя в архив</h2>
            <form action="deleteUser" method="post">
                <div>
                    <label for="login" class="col-25">Логин:</label>
                    <input type="text" id="login" name="login" class="col-75">
                </div>
                <div>
                    <input type="submit" value="В архив" style="width: 400px">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>