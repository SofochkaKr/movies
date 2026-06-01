package com.movies.dao;

import com.movies.db.DBConnection;
import com.movies.model.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

    public List<Character> getAll() throws SQLException {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT c.id, c.character_name, c.actor_name, c.status, c.film_id, f.title AS film_title " +
                     "FROM characters c LEFT JOIN films f ON c.film_id = f.id ORDER BY c.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                characters.add(mapRow(rs));
            }
        }
        return characters;
    }

    public Character getById(int id) throws SQLException {
        String sql = "SELECT c.id, c.character_name, c.actor_name, c.status, c.film_id, f.title AS film_title " +
                     "FROM characters c LEFT JOIN films f ON c.film_id = f.id WHERE c.id = ?";
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

    public void insert(Character character) throws SQLException {
        String sql = "INSERT INTO characters (character_name, actor_name, status, film_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getActorName());
            ps.setString(3, character.getStatus());
            ps.setInt(4, character.getFilmId());
            ps.executeUpdate();
        }
    }

    public void update(Character character) throws SQLException {
        String sql = "UPDATE characters SET character_name = ?, actor_name = ?, status = ?, film_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getActorName());
            ps.setString(3, character.getStatus());
            ps.setInt(4, character.getFilmId());
            ps.setInt(5, character.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM characters WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Character mapRow(ResultSet rs) throws SQLException {
        Character character = new Character();
        character.setId(rs.getInt("id"));
        character.setCharacterName(rs.getString("character_name"));
        character.setActorName(rs.getString("actor_name"));
        character.setStatus(rs.getString("status"));
        character.setFilmId(rs.getInt("film_id"));
        character.setFilmTitle(rs.getString("film_title"));
        return character;
    }
}
