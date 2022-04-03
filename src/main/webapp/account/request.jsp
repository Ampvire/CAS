<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
    <title>Заявка на кредит</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
    <script src="/script.js"></script>
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
            <form action="calculation" method="post">
                <h2>Заявка на кредит</h2>
                <div class="form-body">
                    <input type="text" placeholder="введите сумму" id="sum" name="sum">
                    <label for="year">Количество лет:</label>
                    <select onchange="handleClick(this.selectedIndex)" id="year" name="years">
                        <c:forEach items="${percents}" var="percent">
                            <option>${percent.years}</option>
                        </c:forEach>
                    </select>
                    <input id="request" type="submit" value="Рассчитать">
                    <input type="text"  placeholder="введите процент" id="per" name="percent" style="display: none;">
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>