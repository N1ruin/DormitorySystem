package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.UUID;

public interface UserRepository extends Repository<User<UUID>, UUID> {
    User findByLogin(String login);
}
