<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>All clients</h2>
 <br>
 <br>
<c:forEach items="${list}" var="user">
    id:${user}
    <br>
     <form action="getReport" method="get">
     <input type="submit" value="Report">
     </form>
     <br>
    <hr>
</c:forEach>
</body>
</html>