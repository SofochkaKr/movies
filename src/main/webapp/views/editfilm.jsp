<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактировать фильм</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="row g-4">

        <div class="col-lg-7">
            <h2 class="mb-3">Список фильмов</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Код</th>
                            <th>Название</th>
                            <th>Год выхода</th>
                            <th>Режиссёр</th>
                            <th>Жанр</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="film" items="${films}">
                            <tr>
                                <td>${film.id}</td>
                                <td>${film.title}</td>
                                <td>${film.releaseYear}</td>
                                <td>${film.director}</td>
                                <td>${film.genre}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-lg-5">
            <div class="card shadow-sm">
                <div class="card-header bg-warning text-dark">
                    <h5 class="mb-0">Редактировать фильм</h5>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/editfilm">
                        <div class="mb-3">
                            <label class="form-label">Код</label>
                            <input type="text" name="id" class="form-control"
                                   value="${filmEdit.id}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Название</label>
                            <input type="text" name="title" class="form-control"
                                   value="${filmEdit.title}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Год выхода</label>
                            <input type="text" name="releaseYear" class="form-control"
                                   value="${filmEdit.releaseYear}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Режиссёр</label>
                            <input type="text" name="director" class="form-control"
                                   value="${filmEdit.director}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Жанр</label>
                            <input type="text" name="genre" class="form-control"
                                   value="${filmEdit.genre}">
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-warning flex-fill">Редактировать</button>
                            <a href="${pageContext.request.contextPath}/films"
                               class="btn btn-secondary flex-fill">Отменить</a>
                        </div>
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
