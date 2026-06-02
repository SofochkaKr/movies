<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Персонажи</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="row g-4">

        <div class="col-lg-8">
            <h2 class="mb-3">Список персонажей</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Код</th>
                            <th>Имя персонажа</th>
                            <th>Имя актёра</th>
                            <th>Статус</th>
                            <th>Фильм</th>
                            <th>Редактировать</th>
                            <th>Удалить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="character" items="${characters}">
                            <tr>
                                <td>${character.id}</td>
                                <td>${character.characterName}</td>
                                <td>${character.actorName}</td>
                                <td>${character.status}</td>
                                <td>${character.filmTitle}</td>
                                <td class="text-center">
                                    <a href="#" class="btn btn-sm btn-outline-primary">Изменить</a>
                                </td>
                                <td class="text-center">
                                    <a href="#" class="btn btn-sm btn-outline-danger">Удалить</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">Новый персонаж</h5>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/characters">
                        <div class="mb-3">
                            <label class="form-label">Имя персонажа</label>
                            <input type="text" name="characterName" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Имя актёра</label>
                            <input type="text" name="actorName" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Статус</label>
                            <input type="text" name="status" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Фильм</label>
                            <select name="filmId" class="form-select">
                                <c:forEach var="film" items="${films}">
                                    <option value="${film.id}">${film.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Добавить</button>
                    </form>
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
