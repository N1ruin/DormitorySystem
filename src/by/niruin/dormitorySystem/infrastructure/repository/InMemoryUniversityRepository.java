package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryUniversityRepository extends InMemoryRepositoryBase<University> implements UniversityRepository {
    public static final Path UNIVERSITIES_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryUniversityRepository(EntityMapper<University> universiteMapper) {
        super(universiteMapper, UNIVERSITIES_FILE_PATH, University::getUuid);
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid == null || !entities.containsKey(uuid)) {
            throw new EntityNotFoundException(uuid);
        }
        entities.remove(uuid);
    }

    @Override
    public List<University> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public University findById(UUID uuid) {
        return Optional.ofNullable(entities.get(uuid)).orElseThrow(() -> new UserNotFoundException(uuid));

    }

    @Override
    public void save(University university) {
        if (university == null) {
            throw new EntityNotFoundException();
        }

        entities.put(university.getUuid(), university);
    }

    @Override
    public void update(University university) {
        if (university == null) {
            throw new EntityNotFoundException();
        }

        entities.put(university.getUuid(), university);
    }
}
