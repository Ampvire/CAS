<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Client panel</title>
 </head>
<body>
 <h1>Приветствуем Вас в Банке!</h1>
 <form action="account/save/${finance.clientId.inn}" method="get">
     <div style="width: 500px; display: flex">
        <label for="info">Заполнить данные о компании: </label>
        <input id="info" type="submit" value="Заполнить" style="width: 100px; margin-left: auto">
     </div>
 </form>
   <div style="width: 500px; display: flex;margin-top: 20px"">
      <label for="all">Всего средств на счетах (руб): </label>
      <input id="all" value="${finance.assets}" disabled style="width: 200px; margin-left: auto">
   </div>
   <div style="width: 500px; display: flex;margin-top: 20px"">
      <label for="loans">Кредитных средств (руб): </label>
      <input id="loans"  value="${finance.loans}" disabled style="width: 200px; margin-left: auto">
   </div>
   <div style="width: 500px; display: flex;margin-top: 20px"">
      <label for="year">Год: </label>
      <input id="year"  value="${finance.date}" disabled style="width: 200px; margin-left: auto">
   </div>
    <div style="width: 500px; display: flex; margin-top: 20px">
           <label for="product">Подключенные услуги:</label>
             <span id="product" name="product" style="width: 200px; margin-left: auto">
                 <c:forEach items="${products}" var="product">
                     <option>${product}</option>
                 </c:forEach>
             </span>
    </div>
<h2>Все продукты Банка:</h2>
<c:forEach items="${banksProducts}" var="bProduct">
  <form action="account/${bProduct.name}" method="get">
     <div style="width: 500px; display: flex; margin-top: 20px">
         <label for="${bProduct.name}">${bProduct.name}:</label>
         <input id="${bProduct.name}" type="submit" value="Подать заявку" style="width: 100px; margin-left: auto">
     </div>
  </form>
</c:forEach>
<h2>Заявки на продукты Банка:</h2>
<table >
 <colgroup>
        <col span="5" style="background:#DCDCDC">
    </colgroup>
            <tr>
               <th>ПРОДУКТ</th>
               <th>СТАТУС</th>
               <th>КОММЕНТАРИЙ</th>
           </tr>
<c:forEach items="${applications}" var="application">
           <tr>
               <td>${application.productId.name}</td>
               <td>${application.status}</td>
               <td>${application.rejectReason}</td>
           </tr>
</c:forEach>
    </table>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>