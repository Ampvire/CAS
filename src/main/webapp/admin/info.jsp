<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Admin panel</title>

 </head>
<body>
 <h1>Admin panel</h1>
<form action="admin/allUsers" method="get">
   <div style="width: 300px; display: flex; margin-top: 20px">
       <label for="all">Get all users:</label>
       <input id="all" type="submit" value="Get" style="width: 200px; margin-right: auto">
   </div>
</form>
<form action="admin/delete" method="get">
   <div style="width: 300px; display: flex; margin-top: 20px">
         <label for="del">Delete user:</label>
       <input id="del" type="submit" value="Delete" style="width: 200px; margin-right: auto">
   </div>
</form>
<form action="admin/newUser" method="get">
    <div style="width: 300px; display: flex; margin-top: 20px">
     <label for="create">Create user:</label>
        <input id="create" type="submit" value="Create" style="width: 200px; margin-right: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 200px; margin-right: auto">
    </div>
</form>
</body>
</html>