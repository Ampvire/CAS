<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>All clients</h2>
<div style="width: 300px; display: flex;margin-top: 20px">
<table >
 <colgroup>
        <col span="5" style="background:#DCDCDC">
    </colgroup>
            <tr>
               <th>NANE</th>
               <th>INN</th>
               <th>OGRN</th>
               <th>SEGMENT</th>
               <th>TYPE</th>
               <th></th>
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
                        <div style="width: 200px; display: flex">
                            <input type="submit" value="Report" style="width: 100px; margin-left: auto">
                        </div>
                    </form>
                </td>
                <td>
                   <form action="updateClient/${client.inn}" method="get">
                       <div style="width: 200px; display: flex">
                           <input type="submit" value="Update" style="width: 100px; margin-left: auto">
                       </div>
                   </form>
                </td>
           </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>