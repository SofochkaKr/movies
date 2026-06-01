package com.movies.dao;

import com.movies.db.DBConnection;
import com.movies.model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {

    public List<Film> getAll() throws SQLException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, release_year, director, genre FROM films ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                films.add(mapRow(rs));
            }
        }
        return films;
    }

    public Film getById(int id) throws SQLException {
        String sql = "SELECT id, title, release_year, director, genre FROM films WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public void insert(Film film) throws SQLException {
        String sql = "INSERT INTO films (title, release_year, director, genre) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, film.getTitle());
            ps.setInt(2, film.getReleaseYear());
            ps.setString(3, film.getDirector());
            ps.setString(4, film.getGenre());
            ps.executeUpdate();
        }
    }

    public void update(Film film) throws SQLException {
        String sql = "UPDATE films SET title = ?, release_year = ?, director = ?, genre = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, film.getTitle());
            ps.setInt(2, film.getReleaseYear());
            ps.setString(3, film.getDirector());
            ps.setString(4, film.getGenre());
            ps.setInt(5, film.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Film mapRow(ResultSet rs) throws SQLException {
        Film film = new Film();
        film.setId(rs.getInt("id"));
        film.setTitle(rs.getString("title"));
        film.setReleaseYear(rs.getInt("release_year"));
        film.setDirector(rs.getString("director"));
        film.setGenre(rs.getString("genre"));
        return film;
    }
}
