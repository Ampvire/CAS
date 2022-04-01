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
    <form action="newClient" method="get">
        <button class="btn">Создать клиента</button>
    </form>
    <form action="../../user/info" method="get">
        <button class="btn">На главную</button>
    </form>
</div>
<div class="divSecondBody">
    <table class="myTable">
        <h2>All new clients</h2>
        <colgroup>
            <col span="5" style="background:white">
        </colgroup>
        <tr>
            <th>NANE</th>
            <th>INN</th>
            <th>OGRN</th>
            <th>SEGMENT</th>
            <th>TYPE</th>
            <th>ОТЧЕТ</th>
            <th>ОБНОВИТЬ</th>
        </tr>
        <c:forEach items="${list}" var="client">
            <tr>
                <td>${client.name}</td>
                <td>${client.inn}</td>
                <td>${client.ogrn}</td>
                <td>${client.segmentId.segment}</td>
                <td>${client.typeId.type}</td>
                <td>
                    <form action="getReport/${client.inn}" method="get">
                        <div>
                            <button class="myTableButton">Отчёт</button>
                        </div>
                    </form>
                </td>
                <td>
                    <form action="updateClient/${client.inn}" method="get">
                        <div>
                            <button class="myTableButton">Обновить</button>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>