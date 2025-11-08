package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryDormitoryRepository extends InMemoryRepositoryBase<Dormitory<UUID>, UUID> {
    public static final Path DORMITORIES_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryDormitoryRepository(EntityMapper<Dormitory<UUID>> dormitoryMapper) {
        super(dormitoryMapper, DORMITORIES_FILE_PATH);
    }
}
