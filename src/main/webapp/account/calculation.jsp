<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Client panel</title>
</head>
<body>
<h1>Рассчет кредита</h1>
<h2>Create user</h2>
<form action="calculation" method="post">
    <div style="width: 300px; display: flex">
        <label for="sum">Сумма кредита:</label>
        <input id="sum" name="sum" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="year">Количество лет:</label>
        <input id="year" name="year" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="percent">Процент:</label>
        <input id="percent" name="percent" style="width: 200px; margin-left: auto">
    </div>
</form>
<form action="calculation" method="post" >
    <div style="width: 500px; display: flex; margin-top: 20px">
          <input id="request" type="submit" value="Рассчитать" style="width: 100px; margin-left: auto">
    </div>
</form>
<form action="calculation" method="post" >

</form>
</body>
</html>