package by.niruin.dormitorySystem.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T extends Identity> {
    void save(T entity);

    List<T> findAll();

    Optional<T> findById(UUID id);

    void update(T entity);

    void delete(UUID uuid);
}
