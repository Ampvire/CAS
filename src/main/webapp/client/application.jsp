<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Заявки клиентов</title>
</head>
<body>
<div class="main-wrapper">
    <div class="sidenav">
        <form action="/user/info" method="get">
            <button class="btn">Мои клиенты</button>
        </form>
        <form action="/user/newClient" method="get">
            <button class="btn">Создать клиента</button>
        </form>
        <form action="/user/getNewClients" method="get">
            <button class="btn" type="submit">Незакрепленные клиенты по сегменту:</button>
            <select class="btn-select" name="segment">
                <c:forEach items="${segments}" var="seg">
                    <option>${seg.segment}</option>
                </c:forEach>
            </select>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>

    <div class="divSecondBody">
       <article class="elem-wrapper">
            <div class="client-name">
                <form action="/user/instruction" method="get">
                    <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
                </form>
            </div>
            <h2>Список заявок</h2>
            <table id="applications-list">
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
                    <th>СОГЛАСОВАНИЕ</th>
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
                           <input placeholder="введите комментарий" id="note" name="reason" style="width: 150px; height: 50px;">
                        </td>
                        <td>
                             <input name="id" value="${application.id}" style="visibility:hidden">
                             <label for="status">
                                  <select id="status" name="result" style="width: 150px; height: 50px;">
                                     <c:forEach items="${results}" var="res">
                                         <option>${res}</option>
                                     </c:forEach>
                                 </select>
                             </label>
                            <input type="submit" value="Отправить" style="width: 150px; background: lite grey; height: 30px; cursor: pointer; border: solid 1px black;">
                       </td>
                            </form>
                    </tr>
                </c:forEach>
            </table>
  </article>
    </div>
</div>
</body>
</html>