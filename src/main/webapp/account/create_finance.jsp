<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Ввод финансовых показателей</title>
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
            <form action="financeInfo" method="post">
                <h2 class="form-title">Заполните финансовые показатели</h2>
                <div class="form-body">
                    <input placeholder="введите выручку" type="text" id="rev" name="revenue">
                    <input placeholder="введите численность" type="text" id="staf" name="staf">
                    <input placeholder="введите себестоимость" type="text" id="per" name="costPrice">
                    <input placeholder="введите активы" type="text" id="assets" name="assets">
                    <input placeholder="введите запасы" type="text" id="res" name="reserves">
                    <input placeholder="введите прибыль" type="text" id="profit" name="profit">
                    <label for="date">Отчетная дата:</label>
                    <select id="date" name="year">
                        <c:forEach items="${years}" var="year">
                            <option>${year}</option>
                        </c:forEach>
                    </select>
                    <input id="request" type="submit" value="Заполнить">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>