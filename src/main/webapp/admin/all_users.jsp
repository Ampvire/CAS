<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h1>Admin panel</h1>
<h2>All users</h2>
<form action="allUsers" method="get">
<table>
             <colgroup>
                    <col span="6" style="background:#DCDCDC">
             </colgroup>
            <tr>
               <th>ID</th>
               <th>LOGIN</th>
               <th>FIRST_NAME</th>
               <th>LAST_NAME</th>
               <th>CATEGORY</th>
               <th>ROLE</th>
               <th></th>
               <th></th>
           </tr>
    <c:forEach items="${list}" var="user">
           <tr>
               <td>${user.id}</td>
               <td>${user.login}</td>
               <td>${user.firstName}</td>
               <td>${user.lastName}</td>
               <td>${user.categoryId.category}</td>
               <td>${user.roleId.role}</td>
               <td>
                   <form action="updateUser/${user.login}" method="get">
                       <input type="submit" value="Update" style="width: 100px; margin-right: auto">
                   </form>
               </td>
               <td>
                   <form action="deleteUser/${user.login}" method="post">
                       <input type="submit" value="Delete" style="width: 100px; margin-right: auto">
                   </form>
               </td>
           </tr>
    </c:forEach>
    </table>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>