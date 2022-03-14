<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Admin panel</title>
 </head>
<body>
 <h1>Admin panel</h1>
 <h2>Create user</h2>
 <form action="update" method="post">
  <br>
  <br>
  login: <input name="login">
  <br>
  <br>
  name: <input name="firstName">
  <br>
  <br>
  second name: <input name="secondName">
  <br>
  <br>
  password: <input name="password">
  <br>
  <br>
 category: <input name="categoryId">
 <br>
 <br>
  role: <input name="roleId">
  <br>
  <br>

 <input type="submit" value="Send">
 </form>
   <br>
     <br>
       <br>
        <form action="/logout" method="get">
         <input type="submit" value="logout">
          </form>
</body>
</html>