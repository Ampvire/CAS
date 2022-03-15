<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>All new clients</h2>
<div style="width: 300px; display: flex;margin-top: 20px">
<table>
 <colgroup>
        <col span="4" style="background:#DBD7D2">
    </colgroup>
            <tr>
               <th>NANE</td>
               <th>TYPE</td>
               <th>INN</td>
               <th>OGRN</td>
               <th>SEGMENT</td>
           </tr>
    <c:forEach items="${list}" var="client">
           <tr>
               <td>${client.name}</td>
               <td>${client.type}</td>
               <td>${client.inn}</td>
               <td>${client.ogrn}</td>
                <td>
                    <form action="getReport" method="get">
                        <div style="width: 300px; display: flex; margin-top: 20px">
                            <input type="submit" value="Report" style="width: 100px; margin-right: auto">
                        </div>
                    </form>
                </td>
           </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>