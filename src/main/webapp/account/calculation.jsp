<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Client panel</title>
</head>
<body>
<h1>Рассчет кредита</h1>
<form action="" method="post">
    <div style="width: 300px; display: flex">
        <label for="sum">Сумма кредита:</label>
        <input id="sum" name="sum"  value="${sum}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="year">Количество лет:</label>
        <input id="year" name="year" value="${years}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="percent">Процент:</label>
        <input id="percent" name="percent" value="${percent}" style="width: 200px; margin-left: auto">
    </div>

         <div style="width: 200px; display: flex; margin-top: 20px">
            <input id="request" type="submit" value="Пересчитать" style="width: 200px; margin-left: auto">
        </div>


        <div style="width: 300px; display: flex;margin-top: 20px">
            <label for="loans">Сумма к выплате:</label>
            <input id="loans" name="loans"  value="${loans}" style="width: 200px; margin-left: auto">
        </div>
        <div style="width: 300px; display: flex; margin-top: 20px">
            <label for="payment">Ежемесячный платеж:</label>
            <input id="payment" name="payment" value="${payment}" style="width: 200px; margin-left: auto">
        </div>
         <div style="width: 200px; display: flex; margin-top: 20px">
            <input id="request" type="submit" value="Отправить запрос" style="width: 200px; margin-left: auto">
        </div>
</form>
<form action="" method="post">

</form>

</body>
</html>