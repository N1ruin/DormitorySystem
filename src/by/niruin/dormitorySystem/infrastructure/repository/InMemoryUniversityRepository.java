package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryUniversityRepository extends InMemoryRepositoryBase<University<UUID>, UUID> {
    public static final Path UNIVERSITIES_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryUniversityRepository(EntityMapper<University<UUID>> universiteMapper) {
        super(universiteMapper, UNIVERSITIES_FILE_PATH);
    }
}
