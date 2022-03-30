<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>User panel</title>
</head>
<body>
<h1>User panel</h1>
<h2>Create client</h2>
<form action="create" method="post">
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="name">Client name:</label>
        <input id="name" name="name" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="inn">INN:</label>
        <input id="inn" name="inn" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="ogrn">OGRN:</label>
        <input id="ogrn" name="ogrn" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="type">Type:</label>
         <select id="type" name="type" style="width: 200px; margin-left: auto">
             <c:forEach items="${types}" var="t">
                 <option>${t.type}</option>
             </c:forEach>
         </select>
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
         <label for="segment">Segment:</label>
          <select id="segment" name="segment" style="width: 200px; margin-left: auto">
              <c:forEach items="${segments}" var="seg">
                  <option>${seg.segment}</option>
              </c:forEach>
          </select>
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <input type="submit" value="Create" style="width: 100px; margin-right: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>