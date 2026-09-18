package org.subtrack.daos;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T save(T t) throws SQLException;

    T delete(T t) throws SQLException;

    T update(T t) throws SQLException;

    T get(String id) throws SQLException;

    List<T> getAll() throws SQLException;
}
