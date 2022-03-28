<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
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
           <td>${application.rejectReason}</td>
         </tr>
                 </c:forEach>
    </table>

</div>
</body>
</html>