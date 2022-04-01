<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Заявки клиентов</title>
</head>
<body>
<div class="sidenav">
    <form action="/user/info" method="get">
        <button class="btn">Мои клиенты</button>
    </form>
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
    <form action="/logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
</div>
<div class="divSecondBody">
    <h1>Список заявок</h1>
    <div>
        <table id="applications-list">
            <tr>
                <th>Клиент</th>
                <th>Продукт</th>
                <th>Сумма кредита</th>
                <th>Ежемесячный платеж</th>
                <th>Сумма к выплате</th>
                <th>Процент</th>
                <th>Кол-во лет</th>
                <th>Статус</th>
                <th></th>
            </tr>
            <c:forEach items="${applications}" var="application">
                <tr>
                    <td>${application.clientId.name}</td>
                    <td>${application.productId.name}</td>
                    <td>${application.sum}</td>
                    <td>${application.payment}</td>
                    <td>${application.totalAmount}</td>
                    <td>${application.percent.percent}</td>
                    <td>${application.percent.years}</td>
                    <td>${application.status}</td>
                    <td>
                        <form action="result" method="post">
                            <label for="note">Комментарий
                                <input id="note" name="reason" style="width: 100%;">
                            </label>
                            <input name="id" value="${application.id}" style="visibility:hidden">
                            <label for="status">
                                Выбрать статус:
                                <select id="status" name="result" style="width: 100%;">
                                    <c:forEach items="${results}" var="res">
                                        <option>${res}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <input type="submit" value="Отправить"
                                   style="width: 100%; background: white; height: 70px; cursor: pointer; border: solid 1px black;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>