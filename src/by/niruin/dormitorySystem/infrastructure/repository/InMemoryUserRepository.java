package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.UserMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryUserRepository extends InMemoryRepositoryBase<User<UUID>, UUID> implements UserRepository {
    public static final Path USERS_FILE_PATH = Paths.get("./resources/entity/user.txt");

    public InMemoryUserRepository(@Qualifier(value = UserMapper.class) EntityMapper<User<UUID>> userMapper) {
        super(userMapper, USERS_FILE_PATH);
    }

    @Override
    public User<UUID> findByLogin(String login) {
        for (User<UUID> user : entities.values()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        throw new UserNotFoundException("User with login %s not found!".formatted(login));
    }
}
