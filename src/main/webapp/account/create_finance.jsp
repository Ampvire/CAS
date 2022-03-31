<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Client panel</title>
</head>
<body>
<form action="financeInfo" method="post">
    <div style="width: 300px; display: flex">
        <label for="rev">Revenue:</label>
        <input id="rev" name="revenue"  style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="staf">Staf:</label>
        <input id="staf" name="staf" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="per">costPrice:</label>
        <input id="per" name="costPrice" style="width: 200px; margin-left: auto">
    </div>
    <div style="width: 300px; display: flex; margin-top: 20px">
        <label for="assets">assets:</label>
        <input id="assets" name="assets" style="width: 200px; margin-left: auto">
    </div>
   <div style="width: 300px; display: flex; margin-top: 20px">
       <label for="res">reserves:</label>
       <input id="res" name="reserves" style="width: 200px; margin-left: auto">
   </div>
   <div style="width: 300px; display: flex; margin-top: 20px">
       <label for="profit">profit:</label>
       <input id="profit" name="profit" style="width: 200px; margin-left: auto">
   </div>
   <div style="width: 300px; display: flex; margin-top: 20px">
       <label for="date">Year:</label>
       <select id="date" name="year" style="width: 200px; margin-left: auto">
           <c:forEach items="${years}" var="year">
               <option>${year}</option>
           </c:forEach>
       </select>
   </div>
    <div style="width: 200px; display: flex; margin-top: 20px">
       <input id="request" type="submit" value="Заполнить" style="width: 200px; margin-left: auto">
   </div>
</form>
</body>
</html>