package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.DormitoryMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryDormitoryRepository implements DormitoryRepository {
    private final Map<UUID, Dormitory> dormitories = new HashMap<>();
    private final DormitoryMapper mapper;
    public static final Path DORMITORIES_FILE_PATH = Paths.get("./resources/entity/dormitory.txt");

    public InMemoryDormitoryRepository(DormitoryMapper mapper) {
        this.mapper = mapper;
    }

    public void persistDormitories() {
        String dormitoriesData = mapper.mapDormitoriesToString(dormitories.values());
        FileUtil.writeString(DORMITORIES_FILE_PATH, dormitoriesData);
    }

    public void fetchDormitories() {
        try {
            String dormitoriesData = FileUtil.readString(DORMITORIES_FILE_PATH);
            mapper.mapStringToDormitories(dormitoriesData)
                    .forEach(dormitory -> dormitories.put(dormitory.getId(), dormitory));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void save(Dormitory dormitory) {
        Objects.requireNonNull(dormitory);
        dormitories.put(dormitory.getId(), dormitory);
    }

    @Override
    public List<Dormitory> findAll() {
        return List.copyOf(dormitories.values());
    }

    @Override
    public void update(Dormitory dormitory) {
        Objects.requireNonNull(dormitory);
        dormitories.put(dormitory.getId(), dormitory);
    }

    @Override
    public void delete(UUID id) {
        if (id == null || dormitories.remove(id) == null) {
            throw new EntityNotFoundException(id, Dormitory.class);
        }

        dormitories.remove(id);
    }

    @Override
    public List<Dormitory> findByUniversityId(UUID universityId) {
        return dormitories.values().stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .toList();
    }

    @Override
    public Optional<Dormitory> findByDormitoryNumberOrUniversityId(UUID universityId, int dormitoryNumber) {
        return dormitories.values().stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .filter(dormitory -> dormitory.getNumber() == dormitoryNumber)
                .findFirst();
    }
}
