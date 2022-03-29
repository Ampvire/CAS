<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>User panel</title>
</head>
<body>
<div id="divBody">
        <h1 class="welcome">User panel</h1>
        <h2>Create client</h2>
        <form action="create" method="post">
            <div class="includedPage">
                <label for="name">Client name:</label>
                <input id="name" name="name" style="width: 200px; margin-left: auto">
            </div>
            <div>
                <label for="inn">INN:</label>
                <input id="inn" name="inn" style="width: 200px; margin-left: auto">
            </div>
            <div>
                <label for="ogrn">OGRN:</label>
                <input id="ogrn" name="ogrn" style="width: 200px; margin-left: auto">
            </div>
            <div>
                <label for="type">Type:</label>
                 <select id="type" name="type" style="width: 200px; margin-left: auto">
                     <c:forEach items="${types}" var="t">
                         <option>${t.type}</option>
                     </c:forEach>
                 </select>
            </div>
            <div>
                 <label for="segment">Segment:</label>
                  <select id="segment" name="segment">
                      <c:forEach items="${segments}" var="seg">
                          <option>${seg.segment}</option>
                      </c:forEach>
                  </select>
            </div>
            <div>
                <input type="submit" value="Create">
            </div>
        </form>
        <form action="/logout" method="get">
            <div>
                <input type="submit" value="Logout">
            </div>
        </form>
</div>
</body>
</html>