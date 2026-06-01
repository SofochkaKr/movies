<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.movies.model.Film" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Фильмы — Редактирование</title>
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
            <div class="card shadow-sm">
                <div class="card-header bg-warning">
                    <h5 class="mb-0">Редактировать фильм</h5>
                </div>
                <div class="card-body">
                    <% String error = (String) request.getAttribute("error");
                       if (error != null) { %>
                    <div class="alert alert-danger"><%= error %></div>
                    <% } %>
                    <form method="post" action="${pageContext.request.contextPath}/films" novalidate>
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="<%= film.getId() %>">
                        <div class="mb-3">
                            <label for="title" class="form-label">Название <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="title" name="title"
                                   value="<%= film.getTitle() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="release_year" class="form-label">Год выхода</label>
                            <input type="number" class="form-control" id="release_year" name="release_year"
                                   value="<%= film.getReleaseYear() == 0 ? "" : film.getReleaseYear() %>"
                                   min="1888" max="2100">
                        </div>
                        <div class="mb-3">
                            <label for="director" class="form-label">Режиссёр</label>
                            <input type="text" class="form-control" id="director" name="director"
                                   value="<%= film.getDirector() != null ? film.getDirector() : "" %>">
                        </div>
                        <div class="mb-3">
                            <label for="genre" class="form-label">Жанр</label>
                            <input type="text" class="form-control" id="genre" name="genre"
                                   value="<%= film.getGenre() != null ? film.getGenre() : "" %>">
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-warning">Сохранить изменения</button>
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
