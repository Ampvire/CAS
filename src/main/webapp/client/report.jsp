<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Отчет</title>
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
        <article class="elem-wrapper">
            <div class="client-name">
                <form action="/user/instruction" method="get">
                    <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
                </form>
            </div>
            <h2>Финансовые показатели</h2>
            <div class="table-block">
                <table class="myTable">
                    <colgroup>
                        <col span="8">
                    </colgroup>
                    <tr>
                        <th>Выручка</th>
                        <th>Численность</th>
                        <th>Себестоимость</th>
                        <th>Активы</th>
                        <th>Запасы</th>
                        <th>Чистая прибыль</th>
                        <th>Кредиты и займы</th>
                        <th>Дата</th>
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
            </div>
            <h2>Анализ финансовых показателей</h2>
            <div class="table-block">
                <table class="myTable">
                    <colgroup>
                        <col span="4">
                    </colgroup>
                    <tr>
                        <th>Рентабельность продаж</th>
                        <th>Оборачиваемость запасов</th>
                        <th>Коэффициент быстрой ликвидности</th>
                        <th>Дата</th>
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
            </div>
            <h2>Подключенные банковские услуги:</h2>
            <div class="table-block">
                <table class="myTable">
                    <colgroup>
                        <col span="4">
                    </colgroup>
                    <tr>
                        <th>Наименование</th>
                    </tr>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </article>
    </div>
</div>
</body>
</html>