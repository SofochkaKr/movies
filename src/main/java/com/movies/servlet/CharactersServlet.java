package com.movies.servlet;

import com.movies.dao.CharacterDAO;
import com.movies.dao.FilmDAO;
import com.movies.model.Character;
import com.movies.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CharactersServlet extends HttpServlet {

    private final CharacterDAO characterDAO = new CharacterDAO();
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
                    req.setAttribute("films", filmDAO.getAll());
                    req.getRequestDispatcher("/views/characters/add.jsp").forward(req, resp);
                    break;
                case "edit":
                    int editId = Integer.parseInt(req.getParameter("id"));
                    Character character = characterDAO.getById(editId);
                    req.setAttribute("character", character);
                    req.setAttribute("films", filmDAO.getAll());
                    req.getRequestDispatcher("/views/characters/edit.jsp").forward(req, resp);
                    break;
                case "delete":
                    int delId = Integer.parseInt(req.getParameter("id"));
                    Character toDelete = characterDAO.getById(delId);
                    req.setAttribute("character", toDelete);
                    req.getRequestDispatcher("/views/characters/delete.jsp").forward(req, resp);
                    break;
                default:
                    List<Character> characters = characterDAO.getAll();
                    req.setAttribute("characters", characters);
                    req.getRequestDispatcher("/views/characters/list.jsp").forward(req, resp);
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
                    String characterName = req.getParameter("character_name");
                    if (characterName == null || characterName.trim().isEmpty()) {
                        req.setAttribute("films", filmDAO.getAll());
                        req.setAttribute("error", "Имя персонажа обязательно для заполнения.");
                        req.getRequestDispatcher("/views/characters/add.jsp").forward(req, resp);
                        return;
                    }
                    Character character = new Character();
                    character.setCharacterName(characterName.trim());
                    character.setActorName(req.getParameter("actor_name"));
                    character.setStatus(req.getParameter("status"));
                    character.setFilmId(parseIntOrZero(req.getParameter("film_id")));
                    characterDAO.insert(character);
                    resp.sendRedirect(req.getContextPath() + "/characters");
                    break;
                }
                case "edit": {
                    String characterName = req.getParameter("character_name");
                    int id = Integer.parseInt(req.getParameter("id"));
                    if (characterName == null || characterName.trim().isEmpty()) {
                        req.setAttribute("character", characterDAO.getById(id));
                        req.setAttribute("films", filmDAO.getAll());
                        req.setAttribute("error", "Имя персонажа обязательно для заполнения.");
                        req.getRequestDispatcher("/views/characters/edit.jsp").forward(req, resp);
                        return;
                    }
                    Character character = new Character();
                    character.setId(id);
                    character.setCharacterName(characterName.trim());
                    character.setActorName(req.getParameter("actor_name"));
                    character.setStatus(req.getParameter("status"));
                    character.setFilmId(parseIntOrZero(req.getParameter("film_id")));
                    characterDAO.update(character);
                    resp.sendRedirect(req.getContextPath() + "/characters");
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    characterDAO.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/characters");
                    break;
                }
                default:
                    resp.sendRedirect(req.getContextPath() + "/characters");
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
