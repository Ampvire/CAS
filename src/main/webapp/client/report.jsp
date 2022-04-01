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
    <form action="/logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
    <form action="../../../user/info" method="get">
        <button class="btn">На главную</button>
    </form>
    <form action="../getAllClients" method="get">
        <button class="btn">Мои клиенты</button>
    </form>
    <form action="../newClient" method="get">
        <button class="btn">Создать клиента</button>
    </form>
    <form action="../application" method="get">
        <button class="btn">Заявки на продукты Банка:</button>
    </form>
</div>
<div class="divSecondBody">
    <h2>Финансовые показатели</h2>
    <table class="myTable">
        <colgroup>
            <col span="8">
        </colgroup>
        <tr>
            <th>REVENUE</th>
            <th>STAF</th>
            <th>COST_PRICE</th>
            <th>ASSETS</th>
            <th>RESERVES</th>
            <th>PROFIT</th>
            <th>LOANS</th>
            <th>DATE</th>
            <hr>
        </tr>
        <c:forEach items="${finances}" var="client">
            <tr>
                <td>${client.revenue}</td>
                <td>${client.staf}</td>
                <td>${client.costPrice}</td>
                <td>${client.assets}</td>
                <td>${client.reserves}</td>
                <td>${client.profit}</td>
                <td>${client.loans}</td>
                <td>${client.date}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Отчет за прошедший год</h2>
    <table class="myTable">
        <colgroup>
            <col span="4">
        </colgroup>
        <tr>
            <th>PROFITABILITY_SALE</th>
            <th>INVENTORY_SALE</th>
            <th>QUICK_LIQUIDITY</th>
            <th>DATE</th>
            <hr>
        </tr>
        <c:forEach items="${report}" var="report">
            <tr>
                <td>${report.profitabilitySale}</td>
                <td>${report.inventoryTurnover}</td>
                <td>${report.quickLiquidity}</td>
                <td>${report.date}</td>
            </tr>
        </c:forEach>
    </table>
    <table class="myTable">
        <colgroup>
            <col span="4">
        </colgroup>
        <tr>
            <th>Подключенные продукты</th>
            <hr>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>