<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Client panel</title>
</head>
<body>
<h1>Рассчет кредита</h1>
<form action="calculation/${inn}" method="post">
    <div style="width: 300px; display: flex">
        <label for="sum">Сумма кредита:</label>
        <input id="sum" name="sum" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="year">Количество лет:</label>
        <select onchange="handleClick('${percents}', this.selectedIndex)"
          id="year" name="years" style="width: 200px; margin-left: auto">
            <c:forEach items="${percents}" var="percent">
                <option>${percent.years}</option>
            </c:forEach>
        </select>

    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
         <label for="percent">Процент:</label>
         <input id="percent" name="percent" value=${percent.years} style="width: 200px; margin-left: auto">
    </div>
        <div style="width:200px; display: flex; margin-top: 20px">
              <input id="request" type="submit" value="Рассчитать" style="width: 200px; margin-right: auto">
        </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
<script type="text/javascript">
var per = ${percents}
  function handleClick(percents,selectedIndex)
  {

console.log(per)
  }
</script>
</html>