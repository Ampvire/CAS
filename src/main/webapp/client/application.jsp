<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
 </head>
<body>
 <h1>Заявки клиентов:</h1>
<div style="width: 300px; display: flex;margin-top: 20px">
<table >
 <colgroup>
        <col span="9" style="background:#DCDCDC">
    </colgroup>
         <tr>
           <th>КЛИЕНТ</th>
           <th>ПРОДУКТ</th>
           <th>СУММА КРЕДИТА</th>
           <th>ЕЖЕМЕСЯЧНЫЙ ПЛАТЕЖ</th>
           <th>СУММА К ВЫПЛАТЕ</th>
           <th>ПРОЦЕНТ</th>
           <th>КОЛ-ВО ЛЕТ</th>
           <th>СТАТУС</th>
           <th>КОММЕНТАРИЙ</th>
           <th></th>
         </tr>
         <c:forEach items="${applications}" var="application">
         <tr>
           <td>${application.clientId.name}</td>
           <td>${application.productId.name}</td>
           <td>${application.sum}</td>
           <td>${application.payment}</td>
           <td>${application.totalAmount}</td>
           <td>${application.percent.percent}</td>
           <td>${application.percent.years}</td>
           <td>${application.status}</td>
               <form action="result" method="post">
           <td>
            <div style="width: 200px; display: flex">
               <input name="reason"  style="width: 200px; margin-right: auto">
            </div>
           </td>
           <td>

              <input name="id" value ="${application.id}" style="visibility:hidden">
                      <div style="width: 200px; display: flex">
                          <select name="result" style="width: 200px; margin-left: auto">
                              <c:forEach items="${results}" var="res">
                                  <option>${res}</option>
                              </c:forEach>
                          </select>
                          <input type="submit" value="Отправить" style="width: 100px; margin-left: auto">
                  </div>
              </form>
           </td>
         </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>