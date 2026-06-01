<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.movies.model.Film" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Фильмы — Удаление</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <%
                Film film = (Film) request.getAttribute("film");
                if (film == null) {
            %>
            <div class="alert alert-danger">Фильм не найден.</div>
            <a href="${pageContext.request.contextPath}/films" class="btn btn-secondary">Назад</a>
            <%
                } else {
            %>
            <div class="card shadow-sm border-danger">
                <div class="card-header bg-danger text-white">
                    <h5 class="mb-0">Подтверждение удаления</h5>
                </div>
                <div class="card-body">
                    <p class="mb-1">Вы действительно хотите удалить фильм:</p>
                    <p class="fw-bold fs-5"><%= film.getTitle() %></p>
                    <p class="text-muted small mb-3">
                        Год: <%= film.getReleaseYear() == 0 ? "—" : film.getReleaseYear() %> &nbsp;|&nbsp;
                        Режиссёр: <%= film.getDirector() != null ? film.getDirector() : "—" %> &nbsp;|&nbsp;
                        Жанр: <%= film.getGenre() != null ? film.getGenre() : "—" %>
                    </p>
                    <div class="alert alert-warning">
                        Все персонажи этого фильма также будут удалены.
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/films">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= film.getId() %>">
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-danger">Да, удалить</button>
                            <a href="${pageContext.request.contextPath}/films" class="btn btn-secondary">Отмена</a>
                        </div>
                    </form>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</main>

<jsp:include page="/views/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
