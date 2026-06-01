<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.movies.model.Film" %>
<%@ page import="com.movies.model.Character" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Персонажи — Редактирование</title>
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
            <div class="card shadow-sm">
                <div class="card-header bg-warning">
                    <h5 class="mb-0">Редактировать персонажа</h5>
                </div>
                <div class="card-body">
                    <% String error = (String) request.getAttribute("error");
                       if (error != null) { %>
                    <div class="alert alert-danger"><%= error %></div>
                    <% } %>
                    <form method="post" action="${pageContext.request.contextPath}/characters" novalidate>
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="<%= ch.getId() %>">
                        <div class="mb-3">
                            <label for="character_name" class="form-label">
                                Имя персонажа <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="character_name" name="character_name"
                                   value="<%= ch.getCharacterName() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="actor_name" class="form-label">Имя актёра</label>
                            <input type="text" class="form-control" id="actor_name" name="actor_name"
                                   value="<%= ch.getActorName() != null ? ch.getActorName() : "" %>">
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Статус</label>
                            <input type="text" class="form-control" id="status" name="status"
                                   value="<%= ch.getStatus() != null ? ch.getStatus() : "" %>">
                        </div>
                        <div class="mb-3">
                            <label for="film_id" class="form-label">Фильм</label>
                            <select class="form-select" id="film_id" name="film_id">
                                <option value="">— Выберите фильм —</option>
                                <%
                                    List<Film> films = (List<Film>) request.getAttribute("films");
                                    if (films != null) {
                                        for (Film film : films) {
                                            String selected = film.getId() == ch.getFilmId() ? "selected" : "";
                                %>
                                <option value="<%= film.getId() %>" <%= selected %>><%= film.getTitle() %></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-warning">Сохранить изменения</button>
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
