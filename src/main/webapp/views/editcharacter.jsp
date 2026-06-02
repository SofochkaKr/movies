<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактировать персонажа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/header.jsp"/>

<main class="container my-4 flex-grow-1">
    <div class="row g-4">

        <div class="col-lg-7">
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
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-lg-5">
            <div class="card shadow-sm">
                <div class="card-header bg-warning text-dark">
                    <h5 class="mb-0">Редактировать персонажа</h5>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/editcharacter">
                        <div class="mb-3">
                            <label class="form-label">Код</label>
                            <input type="text" name="id" class="form-control"
                                   value="${characterEdit.id}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Имя персонажа</label>
                            <input type="text" name="characterName" class="form-control"
                                   value="${characterEdit.characterName}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Имя актёра</label>
                            <input type="text" name="actorName" class="form-control"
                                   value="${characterEdit.actorName}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Статус</label>
                            <input type="text" name="status" class="form-control"
                                   value="${characterEdit.status}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Фильм</label>
                            <select name="filmId" class="form-select">
                                <c:forEach var="film" items="${films}">
                                    <option value="${film.id}"
                                        <c:if test="${film.id == characterEdit.filmId}">selected</c:if>>
                                        ${film.title}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-warning flex-fill">Редактировать</button>
                            <a href="${pageContext.request.contextPath}/characters"
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
