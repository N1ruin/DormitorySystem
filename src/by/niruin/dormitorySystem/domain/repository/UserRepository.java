package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void persistUsers();

    void fetchUsers();

    void save(User user);

    List<User> findAll();

    Optional<User> findById(UUID id);

    void update(User user);

    void delete(UUID uuid);

    Optional<User> findByLogin(String login);

    List<User> findAllOrderBy(Comparator<User> comparator);
}
