package dao;

import exception.DAOException;
import java.util.List;

public interface RepositoryDAO<T> {
    Long insert(T o) throws DAOException;
    void update(T o) throws DAOException;
    void delete(Long id) throws DAOException;
    T findById(Long id) throws DAOException;
    List<T> findAll() throws DAOException;
}
