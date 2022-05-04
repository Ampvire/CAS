<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
</head>
<div class="sidenav">
    <form action="../../user/info" method="get">
        <button class="btn">На главную</button>
    </form>
    <form action="newClient" method="get">
        <button class="btn">Создать клиента</button>
    </form>
</div>
<div class="divSecondBody">
    <article class="elem-wrapper">
    <div class="client-name">
        <form action="/user/instruction" method="get">
            <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
        </form>
    </div>
        </article>
    <table class="myTable">
        <colgroup>
            <col span="5" style="background: white;">
        </colgroup>
        <tr>
            <th>НАЗВАНИЕ</th>
            <th>ИНН</th>
            <th>ОГРН</th>
            <th>СЕГМЕНТ</th>
            <th>ТИП</th>
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