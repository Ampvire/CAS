<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
 </head>
 <div class="sidenav">
     <a href="../user/user_panel.jsp">На главную</a>
<%--     <a href="../client/all_clients.jsp">Мои клиенты</a>--%>
     <a href="../client/create_client.jsp">Создать клиента</a>
     <a href="">Заявки</a>
 </div>
 <div class="divSecondBody">
     <table class="myTable">
         <colgroup>
             <col span="5" style="background: white;">
         </colgroup>
         <tr>
             <th>НАЗВАНИЕ</th>
             <th>ИНН</th>
             <th>ОГРН</th>
             <th>СЕГМЕНТ</th>
             <th>ТИП</th>
             <th>ОТЧЕТ</th>
             <th>ОБНОВИТЬ</th>
         </tr>
         <c:forEach items="${list}" var="client">
             <tr>
                 <td>${client.name}</td>
                 <td>${client.inn}</td>
                 <td>${client.ogrn}</td>
                 <td>${client.segmentId.segment}</td>
                 <td>${client.typeId.type}</td>
                 <td>
                     <form action="getReport/${client.inn}" method="get">
                         <div>
                             <button class="myTableButton">Report</button>
                         </div>
                     </form>
                 </td>
                 <td>
                     <form action="updateClient/${client.inn}" method="get">
                         <div>
                             <button class="myTableButton">Update</button>
                         </div>
                     </form>
                 </td>
             </tr>
         </c:forEach>
     </table>
 </div>
</body>
</html>