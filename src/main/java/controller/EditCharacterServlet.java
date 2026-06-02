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

@WebServlet("/editcharacter")
public class EditCharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public EditCharacterServlet() {
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
            for (Character c : characters) {
                characterDao.findByFilmId(c.getFilmId(), films, c);
            }
            request.setAttribute("films", films);
            request.setAttribute("characters", characters);

            String strId = request.getParameter("id");
            if (strId != null) {
                Long id = Long.parseLong(strId);
                Character characterEdit = characterDao.findById(id);
                request.setAttribute("characterEdit", characterEdit);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        request.getRequestDispatcher("/views/editcharacter.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            CharacterDbDAO characterDao = new CharacterDbDAO(new DbConnectionBuilder(prop));

            Long id = Long.parseLong(request.getParameter("id"));
            String characterName = request.getParameter("characterName");
            String actorName = request.getParameter("actorName");
            String status = request.getParameter("status");
            Long filmId = Long.parseLong(request.getParameter("filmId"));

            Character characterEdit = new Character(id, characterName, actorName, status, filmId, null);
            characterDao.update(characterEdit);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/characters");
    }
}
