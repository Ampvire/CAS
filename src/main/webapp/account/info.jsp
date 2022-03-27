<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Client panel</title>
 </head>
<body>
 <h1>Приветствуем Вас в Банке!</h1>
   <div style="width: 500px; display: flex">
      <label for="all">Всего средств на счетах (руб): </label>
      <p id="all" style="width: 200px; margin-left: auto">${finance.assets}</p>
   </div>
   <div style="width: 500px; display: flex">
      <label for="loans">Кредитных средств (руб): </label>
      <p id="loans" style="width: 200px; margin-left: auto">${finance.loans}</p>
   </div>

    <div style="width: 500px; display: flex; margin-top: 20px">
           <label for="product">Подключенные услуги:</label>
             <span id="product" name="product" style="width: 200px; margin-left: auto">
                 <c:forEach items="${products}" var="product">
                     <option>${product}</option>
                 </c:forEach>
             </span>
    </div>
<form action="account/save/${finance.clientId.inn}" method="get">
    <div style="width: 500px; display: flex">
       <label for="info">Заполнить данные о компании: </label>
       <input id="info" type="submit" value="Заполнить" style="width: 100px; margin-left: auto">
    </div>
</form>
<form action="account/loans/${finance.clientId.inn}" method="get">
    <div style="width: 500px; display: flex; margin-top: 20px">
           <label for="request">Отправить заявку на кредит</label>
          <input id="request" type="submit" value="Отправить" style="width: 100px; margin-left: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>