<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>Client report</h2>
  <br>
  <br>
<c:forEach items="${report}" var="rep">
    рентабельность продаж:${rep}
    коэф. оборачиваемости запасов:${rep}
    коэф. быстрой ликвидности:${rep}
    дата: ${date}
    <hr>
</c:forEach>
</body>
</html>