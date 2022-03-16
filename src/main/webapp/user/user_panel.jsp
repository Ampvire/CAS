<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>Get all my clients</h2>
<form action="client/getAllClients" method="get">
   <div style="width: 300px; display: flex; margin-top: 20px">
       <input type="submit" value="Get" style="width: 100px; margin-right: auto">
   </div>
</form>
  <h2>Get all new clients</h2>
<form action="client/getNewClients" method="get">
  <div style="width: 300px; display: flex; margin-top: 20px">
         <label for="segment">Segment:</label>
         <select id="segment" name="segment" style="width: 200px; margin-left: auto">
             <c:forEach items="${segments}" var="seg">
                 <option>${seg.segment}</option>
             </c:forEach>
         </select>
    </div>
   <div style="width: 300px; display: flex; margin-top: 20px">
      <input type="submit" value="Get" style="width: 100px; margin-right: auto">
   </div>
</form>
    <h2>Create new client</h2>
<form action="client/newClient" method="get">
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