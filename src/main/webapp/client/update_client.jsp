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
        <form action="/user/info" method="get">
            <button class="btn">Мои клиенты</button>
        </form>
        <form action="/user/newClient" method="get">
            <button class="btn">Создать клиента</button>
        </form>
        <form action="/user/application" method="get">
            <button class="btn">Заявки на продукты Банка:</button>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <div class="input-form">
            <h2>Обновление данных по клиенту</h2>
            <form action="success" method="post">
                <div>
                    <label for="name" class="col-25">Название:</label>
                    <input type="text" id="name" name="name" value="${name}" class="col-75">
                </div>
                <div>
                    <label for="inn" class="col-25">ИНН:</label>
                    <input type="text" id="inn" name="inn" value="${inn}" class="col-75">
                </div>
                <div>
                    <label for="ogrn" class="col-25">ОГРН:</label>
                    <input type="text" id="ogrn" name="ogrn" value="${ogrn}" class="col-75">
                </div>
                <div>
                    <label for="type" class="col-25">Тип:</label>
                    <select id="type" name="type" class="col-75">
                        <c:forEach items="${types}" var="t">
                            <option>${t.type}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="segment" class="col-25">Сегмент:</label>
                    <select id="segment" name="segment" class="col-75">
                        <c:forEach items="${segments}" var="seg">
                            <option>${seg.segment}</option>
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