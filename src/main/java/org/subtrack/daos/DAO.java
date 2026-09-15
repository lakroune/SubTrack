package org.subtrack.daos;

import java.util.List;

public interface DAO<T> {
    T save(T t);

    T delete(T t);

    T update(T t);

    T insert(T t);

    T get(String id);

    List<T> getAll();
}
