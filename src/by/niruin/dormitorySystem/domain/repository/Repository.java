package by.niruin.dormitorySystem.domain.repository;

import java.util.List;

public interface Repository<T extends Identity<ID>, ID> {
    void save(T entity);

    List<T> findAll();

    T findById(ID id);

    void update(T entity);

    void delete(ID uuid);
}
