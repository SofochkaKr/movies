package controller;

import com.movies.domain.Film;
import dao.ConnectionProperty;
import dao.DbConnectionBuilder;
import dao.FilmDbDAO;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editfilm")
public class EditFilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public EditFilmServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilmDbDAO filmDao = new FilmDbDAO(new DbConnectionBuilder(prop));
            List<Film> films = filmDao.findAll();
            request.setAttribute("films", films);

            String strId = request.getParameter("id");
            if (strId != null) {
                Long id = Long.parseLong(strId);
                Film filmEdit = filmDao.findById(id);
                request.setAttribute("filmEdit", filmEdit);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/views/editfilm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            FilmDbDAO filmDao = new FilmDbDAO(new DbConnectionBuilder(prop));

            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String releaseYearStr = request.getParameter("releaseYear");
            String director = request.getParameter("director");
            String genre = request.getParameter("genre");

            Integer releaseYear = null;
            if (releaseYearStr != null && !releaseYearStr.trim().isEmpty()) {
                try { releaseYear = Integer.parseInt(releaseYearStr.trim()); }
                catch (NumberFormatException ignored) {}
            }

            Film filmEdit = new Film(id, title, releaseYear, director, genre);
            filmDao.update(filmEdit);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/films");
    }
}
