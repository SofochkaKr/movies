<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.movies.model.Character" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Персонажи — Удаление</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <%
                Character ch = (Character) request.getAttribute("character");
                if (ch == null) {
            %>
            <div class="alert alert-danger">Персонаж не найден.</div>
            <a href="${pageContext.request.contextPath}/characters" class="btn btn-secondary">Назад</a>
            <%
                } else {
            %>
            <div class="card shadow-sm border-danger">
                <div class="card-header bg-danger text-white">
                    <h5 class="mb-0">Подтверждение удаления</h5>
                </div>
                <div class="card-body">
                    <p class="mb-1">Вы действительно хотите удалить персонажа:</p>
                    <p class="fw-bold fs-5"><%= ch.getCharacterName() %></p>
                    <p class="text-muted small mb-3">
                        Актёр: <%= ch.getActorName() != null ? ch.getActorName() : "—" %> &nbsp;|&nbsp;
                        Статус: <%= ch.getStatus() != null ? ch.getStatus() : "—" %> &nbsp;|&nbsp;
                        Фильм: <%= ch.getFilmTitle() != null ? ch.getFilmTitle() : "—" %>
                    </p>
                    <form method="post" action="${pageContext.request.contextPath}/characters">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= ch.getId() %>">
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-danger">Да, удалить</button>
                            <a href="${pageContext.request.contextPath}/characters" class="btn btn-secondary">Отмена</a>
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
