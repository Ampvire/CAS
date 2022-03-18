<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h1>Admin panel</h1>
<h2>Delete user</h2>
<form action="deleteUser">
    <div style="width: 300px; display: flex">
        <label for="login">Login:</label>
        <input id="login" name="login" style="width: 200px; margin-left: auto">
    </div>

    <div style="width: 300px; display: flex; margin-top: 20px">
        <input type="submit" value="Delete" style="width: 100px; margin-right: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>