package dao;

import com.movies.domain.Character;
import com.movies.domain.Film;
import exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDbDAO implements RepositoryDAO<Character> {

    private final ConnectionBuilder connectionBuilder;

    public CharacterDbDAO(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public List<Character> findAll() throws DAOException {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT id, character_name, actor_name, status, film_id FROM characters ORDER BY character_name ASC";
        try (Connection conn = connectionBuilder.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                characters.add(fillCharacter(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return characters;
    }

    @Override
    public Character findById(Long id) throws DAOException {
        String sql = "SELECT id, character_name, actor_name, status, film_id FROM characters WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return fillCharacter(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public Long insert(Character character) throws DAOException {
        String sql = "INSERT INTO characters(character_name, actor_name, status, film_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getActorName());
            ps.setString(3, character.getStatus());
            ps.setLong(4, character.getFilmId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Character character) throws DAOException {
        String sql = "UPDATE characters SET character_name = ?, actor_name = ?, status = ?, film_id = ? WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, character.getCharacterName());
            ps.setString(2, character.getActorName());
            ps.setString(3, character.getStatus());
            ps.setLong(4, character.getFilmId());
            ps.setLong(5, character.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM characters WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void findByFilmId(Long filmId, List<Film> films, Character character) {
        for (Film film : films) {
            if (film.getId().equals(filmId)) {
                character.setFilm(film);
                return;
            }
        }
    }

    private Character fillCharacter(ResultSet rs) throws SQLException {
        return new Character(
            rs.getLong("id"),
            rs.getString("character_name"),
            rs.getString("actor_name"),
            rs.getString("status"),
            rs.getLong("film_id"),
            null
        );
    }
}
