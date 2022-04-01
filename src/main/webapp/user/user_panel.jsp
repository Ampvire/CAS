<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
</head>
<body>
<div class="sidenav">
    <form action="/logout" method="get">
        <button class="btn" type="submit">Выход</button>
    </form>
    <form action="client/getAllClients" method="get">
        <button class="btn">Мои клиенты</button>
    </form>
    <form action="client/newClient" method="get">
        <button class="btn">Создать клиента</button>
    </form>
    <form action="client/getNewClients" method="get">
        <button class="btn" type="submit">Незакрепленные клиенты по сегменту:</button>
        <select class="btn-select" name="segment">
            <c:forEach items="${segments}" var="seg">
                <option>${seg.segment}</option>
            </c:forEach>
        </select>
    </form>
</div>
<div>
</div>
</body>
</html>