<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Создать клиента</title>
</head>
<body>
<div class="main-wrapper">

    <div class="sidenav">
        <form action="/user/info" method="get">
            <button class="btn">Мои клиенты</button>
        </form>
        <form action="/user/getNewClients" method="get">
            <button class="btn" type="submit">Незакрепленные клиенты по сегменту:</button>
            <select class="btn-select" name="segment">
                <c:forEach items="${segments}" var="seg">
                    <option>${seg.segment}</option>
                </c:forEach>
            </select>
        </form>
        <form action="/user/application" method="get">
            <button class="btn">Заявки на продукты Банка</button>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>

    <div class="divSecondBody">
        <div class="input-form">
            <div class="client-name">
                <form action="/user/instruction" method="get">
                    <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
                </form>
            </div>
            <h2>Создание клиента</h2>
            <form action="/user/create/success" method="post">
                <div>
                    <input name="name" type="text" id="name" placeholder="Название организации"/>
                </div>
                <div>
                    <input name="inn" type="text" id="inn" placeholder="ИНН"/>
                </div>
                <div>
                    <input name="ogrn" type="text" id="ogrn" placeholder="ОГРН"/>
                </div>
                <div>
                    <select name="type" id="type"/>
                    <c:forEach items="${types}" var="t">
                        <option>${t.type}</option>
                    </c:forEach>
                    </select>
                </div>
                <div>
                    <select name="segment" id="segment"/>
                    <c:forEach items="${segments}" var="seg">
                        <option>${seg.segment}</option>
                    </c:forEach>
                    </select>
                </div>
                <div>
                    <input type="submit" value="СОЗДАТЬ">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>