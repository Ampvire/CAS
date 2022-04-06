<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Мои клиенты</title>
</head>
<body>
<div class="main-wrapper">
    <aside class="sidenav">
        <form action="/user/newClient" method="get">
            <button class="btn">Создать клиента</button>
        </form>
        <form action="/user/getNewClients" method="get">
            <button class="btn" type="submit">Незакрепленные клиенты по сегменту:</button>
            <select class="btn-select" name="segment">
                <c:forEach items="${segments}" var="seg">
                    <option>${seg.segment}</option>
                </c:forEach>
            </select>
        </form>
        <form action="/user/application" method="get">
            <button class="btn">Заявки на продукты Банка</button>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </aside>
    <div class="divSecondBody">
        <article class="elem-wrapper">
            <div class="client-name">
            <form action="/user/instruction" method="get">
                <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
            </form>
            </div>
            <h2>Мои клиенты</h2>
            <div class="table-block">
                <table class="myTable">
                    <tr>
                        <th>Название</th>
                        <th>ИНН</th>
                        <th>ОГРН</th>
                        <th>Сегмент</th>
                        <th>ОПФ</th>
                        <th></th>
                        <th></th>
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
                                    <button class="myTableButton">Отчёт</button>
                                </form>
                            </td>
                            <td>
                                <form action="updateClient/${client.inn}" method="get">
                                    <button class="myTableButton">Изменить</button>
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