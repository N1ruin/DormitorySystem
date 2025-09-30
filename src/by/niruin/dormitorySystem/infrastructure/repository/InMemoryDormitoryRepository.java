package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryDormitoryRepository extends InMemoryRepositoryBase<Dormitory> implements DormitoryRepository {
    public static final Path DORMITORIES_FILE_PATH = Paths.get("./resources/entity/entities.txt");

    public InMemoryDormitoryRepository(EntityMapper<Dormitory> dormitoryMapper) {
        super(dormitoryMapper, DORMITORIES_FILE_PATH, Dormitory::getUuid);
    }

    @Override
    public void delete(UUID uuid) {
        if (uuid == null || !entities.containsKey(uuid)) {
            throw new EntityNotFoundException(uuid);
        }
        entities.remove(uuid);
    }

    @Override
    public List<Dormitory> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Dormitory findById(UUID uuid) {
        return Optional.ofNullable(entities.get(uuid)).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public void save(Dormitory dormitory) {
        if (dormitory == null) {
            throw new EntityNotFoundException();
        }

        entities.put(dormitory.getUuid(), dormitory);
    }

    @Override
    public void update(Dormitory dormitory) {
        if (dormitory == null) {
            throw new EntityNotFoundException();
        }

        entities.put(dormitory.getUuid(), dormitory);
    }
}
