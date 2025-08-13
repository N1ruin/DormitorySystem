package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.List;

public interface UserRepository {
    User findUserByLogin(String login);
    User findUserById(long id);
    void save(User user);
    List<User> findAllUsers();
    void updateUser(User user);
    void delete(long id);
}