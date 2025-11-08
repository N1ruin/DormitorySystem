package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryUserRepository extends InMemoryRepositoryBase<User<UUID>, UUID> {
    public static final Path USERS_FILE_PATH = Paths.get("./resources/entity/users.txt");

    public InMemoryUserRepository(EntityMapper<User<UUID>> userMapper) {
        super(userMapper, USERS_FILE_PATH);
    }
}
