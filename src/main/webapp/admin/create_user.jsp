<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h1>Admin panel</h1>
<h2>Create user</h2>
<form action="update" method="post">
    <div style="width: 300px; display: flex">
        <label for="login">Login:</label>
        <input id="login" name="login" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="name">Name:</label>
        <input id="name" name="firstName" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="secondName">Second name:</label>
        <input id="secondName" name="secondName" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="password">Password:</label>
        <input id="password" name="password" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="category">Category:</label>
        <select id="category" name="category" style="width: 200px; margin-left: auto">
            <c:forEach items="${categories}" var="cat">
                <option>${cat.category}</option>
            </c:forEach>
        </select>
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="role">Role:</label>
        <select id="role" name="role" style="width: 200px; margin-left: auto">
            <c:forEach items="${roles}" var="r">
                <option>${r.role}</option>
            </c:forEach>
        </select>
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <input type="submit" value="Send" style="width: 100px; margin-right: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>