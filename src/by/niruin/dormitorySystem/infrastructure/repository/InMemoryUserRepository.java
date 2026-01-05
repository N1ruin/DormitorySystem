package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.UserMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class InMemoryUserRepository extends InMemoryRepositoryBase<User> implements UserRepository {
    public static final Path USERS_FILE_PATH = Paths.get("./resources/entity/user.txt");

    public InMemoryUserRepository(@Qualifier(UserMapper.class) EntityMapper<User> userMapper) {
        super(userMapper, USERS_FILE_PATH);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return entities.values().stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst();
    }
}
