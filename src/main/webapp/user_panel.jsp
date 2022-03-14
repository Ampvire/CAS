<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>Get all my clients</h2>
 <form action="getAllClients" method="get">
  <br>
 <input type="submit" value="Send">
 </form>
 <br>
 <br>
  <h2>Get all clients my category</h2>
  <form action="getNewClients" method="get">
   <br>
  <input type="submit" value="Send">
  </form>
   <br>
    <h2>Create new client</h2>
    <form action="newClient" method="get">
     <br>
    <input type="submit" value="Create">
    </form>
 <br>
 <br>
  <form action="/logout" method="get">
 <input type="submit" value="logout">
 </form>
</body>
</html>