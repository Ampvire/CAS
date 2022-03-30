<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Клиенты</title>
 </head>
<body>
 <h1>База клиентов</h1>
 <h2>Выберите клиента для закрепления</h2>
<div style="width: 300px; display: flex;margin-top: 20px">
<table>
 <colgroup>
        <col span="5" style="background:#DCDCDC">
    </colgroup>
            <tr>
                <th>ОПФ</th>
               <th>Наименование</th>
               <th>ИНН</th>
               <th>ОГРН</th>
               <th>Сегмент</th>
               <th></th>
               <th></th>
           </tr>
    <c:forEach items="${list}" var="client">
           <tr>
               <td>${client.typeId.type}</td>
               <td>${client.name}</td>
               <td>${client.inn}</td>
               <td>${client.ogrn}</td>
               <td>${client.segmentId.segment}</td>
<%--                <td>--%>
<%--                    <form action="getReport/${client.inn}" method="get">--%>
<%--                        <div style="width: 300px; display: flex">--%>
<%--                            <input type="submit" value="Report" style="width: 100px; margin-right: auto">--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </td>--%>
                <td>

                    <c:if test="${check}">
                        <form action="addManager" method="post">
                            <input id="inn" name="inn" value="${client.inn}" style="visibility: hidden;">
                            <div style="width: 300px; display: flex">
                                <input type="submit" value="Закрепить" style="width: 100px; margin-right: auto">
                            </div>
                        </form>
                    </c:if>

                </td>
           </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>