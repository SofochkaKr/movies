package com.movies.servlet;

import com.movies.dao.FilmDAO;
import com.movies.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/films")
public class FilmsServlet extends HttpServlet {

    private final FilmDAO filmDAO = new FilmDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    req.getRequestDispatcher("/views/films/add.jsp").forward(req, resp);
                    break;
                case "edit":
                    int editId = Integer.parseInt(req.getParameter("id"));
                    Film film = filmDAO.getById(editId);
                    req.setAttribute("film", film);
                    req.getRequestDispatcher("/views/films/edit.jsp").forward(req, resp);
                    break;
                case "delete":
                    int delId = Integer.parseInt(req.getParameter("id"));
                    Film toDelete = filmDAO.getById(delId);
                    req.setAttribute("film", toDelete);
                    req.getRequestDispatcher("/views/films/delete.jsp").forward(req, resp);
                    break;
                default:
                    List<Film> films = filmDAO.getAll();
                    req.setAttribute("films", films);
                    req.getRequestDispatcher("/views/films/list.jsp").forward(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add": {
                    String title = req.getParameter("title");
                    if (title == null || title.trim().isEmpty()) {
                        req.setAttribute("error", "Название обязательно для заполнения.");
                        req.getRequestDispatcher("/views/films/add.jsp").forward(req, resp);
                        return;
                    }
                    Film film = new Film();
                    film.setTitle(title.trim());
                    film.setReleaseYear(parseIntOrZero(req.getParameter("release_year")));
                    film.setDirector(req.getParameter("director"));
                    film.setGenre(req.getParameter("genre"));
                    filmDAO.insert(film);
                    resp.sendRedirect(req.getContextPath() + "/films");
                    break;
                }
                case "edit": {
                    String title = req.getParameter("title");
                    if (title == null || title.trim().isEmpty()) {
                        Film film = filmDAO.getById(Integer.parseInt(req.getParameter("id")));
                        req.setAttribute("film", film);
                        req.setAttribute("error", "Название обязательно для заполнения.");
                        req.getRequestDispatcher("/views/films/edit.jsp").forward(req, resp);
                        return;
                    }
                    Film film = new Film();
                    film.setId(Integer.parseInt(req.getParameter("id")));
                    film.setTitle(title.trim());
                    film.setReleaseYear(parseIntOrZero(req.getParameter("release_year")));
                    film.setDirector(req.getParameter("director"));
                    film.setGenre(req.getParameter("genre"));
                    filmDAO.update(film);
                    resp.sendRedirect(req.getContextPath() + "/films");
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    filmDAO.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/films");
                    break;
                }
                default:
                    resp.sendRedirect(req.getContextPath() + "/films");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private int parseIntOrZero(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
