<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.movies.model.Character" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Персонажи — Список</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Список персонажей</h2>
        <a href="${pageContext.request.contextPath}/characters?action=add" class="btn btn-success">
            + Добавить персонажа
        </a>
    </div>

    <%
        List<Character> characters = (List<Character>) request.getAttribute("characters");
        if (characters == null || characters.isEmpty()) {
    %>
    <div class="alert alert-info">Персонажи не найдены. Добавьте первого персонажа!</div>
    <%
        } else {
    %>
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Имя персонажа</th>
                    <th>Актёр</th>
                    <th>Статус</th>
                    <th>Фильм</th>
                    <th class="text-center">Действия</th>
                </tr>
            </thead>
            <tbody>
                <% for (Character ch : characters) { %>
                <tr>
                    <td><%= ch.getId() %></td>
                    <td><%= ch.getCharacterName() %></td>
                    <td><%= ch.getActorName() != null ? ch.getActorName() : "—" %></td>
                    <td><%= ch.getStatus() != null ? ch.getStatus() : "—" %></td>
                    <td><%= ch.getFilmTitle() != null ? ch.getFilmTitle() : "—" %></td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/characters?action=edit&id=<%= ch.getId() %>"
                           class="btn btn-sm btn-warning me-1">Редактировать</a>
                        <a href="${pageContext.request.contextPath}/characters?action=delete&id=<%= ch.getId() %>"
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
