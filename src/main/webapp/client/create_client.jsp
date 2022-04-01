<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
</head>
<body>
<div class="sidenav">
    <form action="getAllClients" method="get">
        <button class="btn">Мои клиенты</button>
    </form>
    <form action="../../user/info" method="get">
        <button class="btn">На главную</button>
    </form>
    <form action="getNewClients" method="get">
        <button class="btn" type="submit">Незакрепленные клиенты по сегменту:</button>
        <select class="btn-select" name="segment">
            <c:forEach items="${segments}" var="seg">
                <option>${seg.segment}</option>
            </c:forEach>
        </select>
    </form>
    <form action="client/application" method="get">
        <button class="btn">Заявки на продукты Банка:</button>
    </form>
</div>
<div class="divSecondBody">
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
</div>
</body>
</html>