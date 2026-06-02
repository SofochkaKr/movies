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

@WebServlet("/films")
public class FilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public FilmServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilmDbDAO filmDao = new FilmDbDAO(new DbConnectionBuilder(prop));
            List<Film> films = filmDao.findAll();
            request.setAttribute("films", films);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/views/film.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
