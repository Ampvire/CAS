<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
</head>
<body>
<div class="sidenav">
    <a href="../user/user_panel.jsp">На главную</a>
    <a href="./all_clients.jsp">Мои клиенты</a>
    <a href="./create_client.jsp">Создать клиента</a>
    <a href="">Заявки</a>
</div>
<div class="input-form">
    <h2>Создание клиента</h2>
    <form action="create" method="post">
        <div>
            <input type="text" id="name" placeholder="Название организации"/>
        </div>
        <div>
            <input type="text" id="inn" placeholder="ИНН"/>
        </div>
        <div>
            <input type="text" id="ogrn" placeholder="ОГРН"/>
        </div>
        <div>
            <select name="Тип" id="type"/>
            <c:forEach items="${types}" var="t">
                <option>${t.type}</option>
            </c:forEach>
            </select>
        </div>
        <div>
            <select name="Сегмент" id="segment"/>
            <c:forEach items="${segments}" var="seg">
                <option>${seg.segment}</option>
            </c:forEach>
            </select>
        </div>
        <div>
            <input type="submit" value="СОЗДАТЬ">
        </div>
    </form>
</div>
</body>
</html>