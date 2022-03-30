<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>Success</title>
 </head>
<body>
    <div style="width: 300px; display: flex">
        <p style="width: 200px; margin-left: auto">${message}</p>
    </div>
 <form action="/logout" method="get">
     <div style="width: 300px; display: flex;margin-top: 20px">
         <input type="submit" value="Logout" style="width: 100px; margin-right: auto">
     </div>
 </form>
</body>
</html>