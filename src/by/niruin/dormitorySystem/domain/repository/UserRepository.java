package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByLogin(String login);
}
