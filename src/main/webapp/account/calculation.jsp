<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Результаты расчета</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<div class="main-wrapper">
    <div class="sidenav">
        <form action="/account" method="get">
            <button class="btn" type="submit">Главная</button>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <div class="input-form">
            <form action="saveApplication" method="post">
                <h2>Расчет кредита</h2>
                <div>
                    <label for="loans">Сумма к выплате:</label>
                    <input type="text" id="loans" name="loans" value="${loans}">

                    <label for="payment">Ежемесячный платеж:</label>
                    <input type="text"  id="payment" name="payment" value="${payment}">

                    <label for="per">Процентная ставка:</label>
                    <input type="text"  id="per" name="percent" value="${percent}">

                    <label for="years">Количество лет:</label>
                    <input type="text"  id="years" name="years" value="${years}">

                    <label for="amount">Сумма кредита:</label>
                    <input type="text"  id="amount" name="sum" value="${sum}">

                    <input id="request" type="submit" value="Отправить запрос">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>