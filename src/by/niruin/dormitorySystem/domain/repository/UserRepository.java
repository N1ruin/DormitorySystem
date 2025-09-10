package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User findByLogin(String login);
    User findById(UUID uuid);
    void save(User user);
    List<User> findAll();
    void update(User user);
    void delete(long id);
}
