<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Фильмы — Главная</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-5 flex-grow-1">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <h1 class="mb-4 text-center">Добро пожаловать в систему «Фильмы»</h1>
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">Функции системы</h5>
                </div>
                <div class="card-body">
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/films"
                           class="list-group-item list-group-item-action d-flex align-items-center gap-3 py-3">
                            <span class="fs-4">&#127916;</span>
                            <div>
                                <div class="fw-bold">Фильмы</div>
                                <small class="text-muted">Просмотр, добавление, редактирование и удаление фильмов</small>
                            </div>
                        </a>
                        <a href="${pageContext.request.contextPath}/characters"
                           class="list-group-item list-group-item-action d-flex align-items-center gap-3 py-3">
                            <span class="fs-4">&#127381;</span>
                            <div>
                                <div class="fw-bold">Персонажи</div>
                                <small class="text-muted">Управление персонажами и их привязкой к фильмам</small>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/views/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
