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
                <h2>Расчет кредита</h2>
                <div class="notification">
                <p>Сумма к выплате: ${loans}р.</p>
                <p>Ежемесячный платеж: ${payment}р.</p>
                <p>Процентная ставка: ${percent}%</p>
                <p>Количество лет: ${years}</p>
                <p>Сумма кредита: ${sum}р.</p>
                </div>
            <form action="saveApplication" method="post">
                    <input type="text" id="loans" name="loans" value="${loans}" style="display: none">
                    <input type="text"  id="payment" name="payment" value="${payment}" style="display: none">
                    <input type="text"  id="per" name="percent" value="${percent}" style="display: none">
                    <input type="text"  id="years" name="years" value="${years}" style="display: none">
                    <input type="text"  id="amount" name="sum" value="${sum}" style="display: none">
                    <input id="request" type="submit" value="Отправить запрос">
            </form>
        </div>
    </div>
</div>
</body>
</html>