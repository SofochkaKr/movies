package controller;

import com.movies.domain.Character;
import com.movies.domain.Film;
import dao.CharacterDbDAO;
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

@WebServlet("/characters")
public class CharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public CharacterServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilmDbDAO filmDao = new FilmDbDAO(new DbConnectionBuilder(prop));
            CharacterDbDAO characterDao = new CharacterDbDAO(new DbConnectionBuilder(prop));

            List<Film> films = filmDao.findAll();
            List<Character> characters = characterDao.findAll();

            for (Character character : characters) {
                characterDao.findByFilmId(character.getFilmId(), films, character);
            }

            request.setAttribute("films", films);
            request.setAttribute("characters", characters);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/views/character.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
