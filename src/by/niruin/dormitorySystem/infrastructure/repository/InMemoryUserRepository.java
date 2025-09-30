package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryUserRepository extends InMemoryRepositoryBase<User> implements UserRepository {
    public static final Path USERS_FILE_PATH = Paths.get("./resources/entity/users.txt");

    public InMemoryUserRepository(EntityMapper<User> userMapper) {
        super(userMapper, USERS_FILE_PATH, User::getUuid);
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid == null || !entities.containsKey(uuid)) {
            throw new UserNotFoundException(uuid);
        } else {
            entities.remove(uuid);
        }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public User findById(UUID uuid) {
        return Optional
                .ofNullable(entities.get(uuid))
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public User findByLogin(String login) {
        Optional<User> result = entities
                .values()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();

        return result.orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }

        entities.put(user.getUuid(), user);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }

        entities.put(user.getUuid(), user);
    }
}
