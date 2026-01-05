package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.UserMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<UUID, User> users = new HashMap<>();
    private final UserMapper mapper;
    public static final Path USERS_FILE_PATH = Paths.get("./resources/entity/user.txt");

    public InMemoryUserRepository(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users.values().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public void persistUsers() {
        String usersData = mapper.mapUsersToString(users.values());
        FileUtil.writeString(USERS_FILE_PATH, usersData);
    }

    public void fetchUsers() {
        try {
            String usersData = FileUtil.readString(USERS_FILE_PATH);
            mapper.mapStringToUsers(usersData)
                    .forEach(user -> users.put(user.getId(), user));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(UUID id) {
        users.remove(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.values().stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst();
    }
}
