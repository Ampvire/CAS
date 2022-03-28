<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
 <head>
    <title>User panel</title>
     <link rel="stylesheet" type="text/css" href="/main.css">
 </head>

<body>
 <h1>User panel</h1>
<form action="client/getAllClients" method="get">
   <div style="width: 500px; display: flex">
      <label for="all">Get all my clients:</label>
       <input id="all" type="submit" value="Get" style="width: 100px; margin-left: auto">
   </div>
</form>
<form action="client/getNewClients" method="get">
  <div style="width: 500px; display: flex; margin-top: 20px">
         <label for="segment">Get all new clients by segment:</label>
         <select id="segment" name="segment" style="width: 200px; margin-left: auto">
             <c:forEach items="${segments}" var="seg">
                 <option>${seg.segment}</option>
             </c:forEach>
         </select>
         <input type="submit" value="Get" style="width: 100px; margin-left: auto">
    </div>
</form>
<form action="client/newClient" method="get">
    <div style="width: 500px; display: flex; margin-top: 20px">
       <label for="client">Create new client:</label>
        <input id="client" type="submit" value="Create" style="width: 100px; margin-left: auto">
    </div>
</form>
<form action="/logout" method="get">
    <div style="width: 300px; display: flex;margin-top: 20px">
        <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
    </div>
</form>
</body>
</html>