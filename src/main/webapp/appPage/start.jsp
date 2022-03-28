<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Вход в систему</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>

<ul class="nav justify-content-end">
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="/admin/info">Admin</a>
    </li>
</ul>


    <div class="container-fluid d-flex h-100 p-0 justify-content-center align-items-center">
        <div class="row bg-white shadow w-25 p-0 m-0">
            <div class="col border rounded p-4">
                <h3 class="text-center mb-4">Выберите вариант входа в систему</h3>
                <div class="mx-auto w-75 m-2">
                    <a href="/user/info" class="btn btn-primary  w-100 shadow-sm">Войти как сотрудник</a>
                </div>
                <div class="mx-auto w-75 m-2">
                    <a href="/user/info" class="btn btn-primary w-100 shadow-sm">Войти как клиент</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>