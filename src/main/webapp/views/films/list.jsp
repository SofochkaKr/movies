<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.movies.model.Film" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Фильмы — Список</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Список фильмов</h2>
        <a href="${pageContext.request.contextPath}/films?action=add" class="btn btn-success">
            + Добавить фильм
        </a>
    </div>

    <%
        List<Film> films = (List<Film>) request.getAttribute("films");
        if (films == null || films.isEmpty()) {
    %>
    <div class="alert alert-info">Фильмы не найдены. Добавьте первый фильм!</div>
    <%
        } else {
    %>
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Название</th>
                    <th>Год выхода</th>
                    <th>Режиссёр</th>
                    <th>Жанр</th>
                    <th class="text-center">Действия</th>
                </tr>
            </thead>
            <tbody>
                <% for (Film film : films) { %>
                <tr>
                    <td><%= film.getId() %></td>
                    <td><%= film.getTitle() %></td>
                    <td><%= film.getReleaseYear() == 0 ? "—" : film.getReleaseYear() %></td>
                    <td><%= film.getDirector() != null ? film.getDirector() : "—" %></td>
                    <td><%= film.getGenre() != null ? film.getGenre() : "—" %></td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/films?action=edit&id=<%= film.getId() %>"
                           class="btn btn-sm btn-warning me-1">Редактировать</a>
                        <a href="${pageContext.request.contextPath}/films?action=delete&id=<%= film.getId() %>"
                           class="btn btn-sm btn-danger">Удалить</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
</main>

<jsp:include page="/views/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
