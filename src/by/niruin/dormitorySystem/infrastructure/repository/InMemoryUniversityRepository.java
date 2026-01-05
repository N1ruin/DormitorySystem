package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.UniversityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class InMemoryUniversityRepository extends InMemoryRepositoryBase<University> implements UniversityRepository {
    public static final Path UNIVERSITIES_FILE_PATH = Paths.get("./resources/entity/university.txt");

    public InMemoryUniversityRepository(@Qualifier(UniversityMapper.class) EntityMapper<University> universiteMapper) {
        super(universiteMapper, UNIVERSITIES_FILE_PATH);
    }

    @Override
    public Optional<University> findByName(String name) {
        return entities.values().stream()
                .filter(university -> university.getName().equals(name))
                .findFirst();
    }
}
