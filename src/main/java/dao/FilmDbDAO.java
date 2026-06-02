package dao;

import com.movies.domain.Film;
import exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDbDAO implements RepositoryDAO<Film> {

    private final ConnectionBuilder connectionBuilder;

    public FilmDbDAO(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public List<Film> findAll() throws DAOException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, release_year, director, genre FROM films ORDER BY title ASC";
        try (Connection conn = connectionBuilder.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                films.add(fillFilm(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return films;
    }

    @Override
    public Film findById(Long id) throws DAOException {
        String sql = "SELECT id, title, release_year, director, genre FROM films WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return fillFilm(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public Long insert(Film film) throws DAOException {
        String sql = "INSERT INTO films(title, release_year, director, genre) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, film.getTitle());
            ps.setObject(2, film.getReleaseYear());
            ps.setString(3, film.getDirector());
            ps.setString(4, film.getGenre());
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
    public void update(Film film) throws DAOException {
        String sql = "UPDATE films SET title = ?, release_year = ?, director = ?, genre = ? WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, film.getTitle());
            ps.setObject(2, film.getReleaseYear());
            ps.setString(3, film.getDirector());
            ps.setString(4, film.getGenre());
            ps.setLong(5, film.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (Connection conn = connectionBuilder.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Film fillFilm(ResultSet rs) throws SQLException {
        return new Film(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getObject("release_year", Integer.class),
            rs.getString("director"),
            rs.getString("genre")
        );
    }
}
