<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h1>Admin panel</h1>
<h2>Update user</h2>
<form action="success" method="post">

    <div style="width: 300px; display: flex">
        <label for="login">Login:</label>
        <input id="login" name="login" value="${login}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="name">Name:</label>
        <input id="name" name="firstName" value="${name}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="secondName">Second name:</label>
        <input id="secondName" name="secondName" value="${secondName}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="password">Password:</label>
        <input id="password" name="password"  value="${password}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="category">Category:</label>
        <input id="category" name="category"  value="${category}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="role">Role:</label>
        <input id="role" name="role"  value="${role}" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <input type="submit" value="Update" style="width: 100px; margin-right: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>