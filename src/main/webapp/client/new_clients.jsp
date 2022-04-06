<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/style.css">
    <title>Клиенты</title>
</head>
<body>
<div class="main-wrapper">
    <div class="sidenav">
        <form action="/user/info" method="get">
            <button class="btn">Мои клиенты</button>
        </form>
        <form action="/user/newClient" method="get">
            <button class="btn">Создать клиента</button>
        </form>
        <form action="/user/application" method="get">
            <button class="btn">Заявки на продукты Банка</button>
        </form>
        <form action="/logout" method="get">
            <button class="btn" type="submit">Выход</button>
        </form>
    </div>
    <div class="divSecondBody">
        <article class="elem-wrapper">
            <div class="client-name">
                <form action="/user/instruction" method="get">
                    <button title="Инструкция по сервису" class="btn-get-instruction">?</button>
                </form>
            </div>
            <h2>Не закрепленные клиенты сегмента "${segment}"</h2>
            <div class="table-block">
                <table class="myTable">
                    <colgroup>
                        <col span="5" style="background:white">
                    </colgroup>
                    <tr>
                        <th>Наименование</th>
                        <th>ИНН</th>
                        <th>ОГРН</th>
                        <th>Сегмент</th>
                        <th>Тип</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${list}" var="client">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.inn}</td>
                            <td>${client.ogrn}</td>
                            <td>${client.segmentId.segment}</td>
                            <td>${client.typeId.type}</td>
                            <td>
                                <c:if test="${check}">
                                    <form action="/user/addManager" method="post">
                                        <input id="inn" name="inn" value="${client.inn}" style="visibility: hidden; width: 1px">
                                        <br/>
                                        <input class="myTableButton" type="submit" value="Закрепить">
                                    </form>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </article>
    </div>
</div>
</body>
</html>