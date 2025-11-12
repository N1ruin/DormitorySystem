package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.UniversityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryUniversityRepository extends InMemoryRepositoryBase<University<UUID>, UUID> {
    public static final Path UNIVERSITIES_FILE_PATH = Paths.get("./resources/entity/university.txt");

    public InMemoryUniversityRepository(@Qualifier(value = UniversityMapper.class) EntityMapper<University<UUID>> universiteMapper) {
        super(universiteMapper, UNIVERSITIES_FILE_PATH);
    }
}
