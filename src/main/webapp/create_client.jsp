<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>Create client</h2>
 <form action="update" method="post">
  <br>
  <br>
  Наименование: <input name="name">
  <br>
  <br>
  тип клиента: <input name="type">
  <br>
  <br>
  ИНН: <input name="inn">
  <br>
  <br>
  ОГРН: <input name="ogrn">
  <br>
  <br>
 сегмент: <input name="segmentId">
 <br>
 <br>
 <input type="submit" value="Create">
 </form>
   <br>
     <br>
       <br>
        <form action="/logout" method="get">
         <input type="submit" value="logout">
          </form>
</body>
</html>