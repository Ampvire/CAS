<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="/main.css">
</head>
<body>
<div id="autorisation">
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <div class="login-wr">
                    <h2>Войти</h2>
                    <form class="form" method="post" action="/login"/>
                        <label>
                            <input name="username" type="text" placeholder="Логин" >
                        </label>
                        <label>
                            <input name="password" type="password" placeholder="Пароль" required>
                        </label>
                        <button class="button" type="submit"> Авторизация </button>
                    </form>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>