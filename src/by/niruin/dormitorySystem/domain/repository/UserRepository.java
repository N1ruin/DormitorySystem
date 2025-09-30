package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.User;

public interface UserRepository extends Repository<User>{
    User findByLogin(String login);
}
