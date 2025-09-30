package by.niruin.dormitorySystem.domain.repository;

import java.util.List;
import java.util.UUID;

public interface Repository<T>{
    void save(T entity);

    List<T> findAll();

    T findById(UUID uuid);

    void update(T entity);

    void delete(UUID uuid);
}
