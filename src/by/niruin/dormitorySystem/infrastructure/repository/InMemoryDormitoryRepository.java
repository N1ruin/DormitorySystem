package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.DormitoryMapper;
import by.niruin.dormitorySystem.util.FileUtil;

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

    @Override
    public void persistDormitories() {
        String dormitoriesData = mapper.mapDormitoriesToString(dormitories.values());
        FileUtil.writeString(DORMITORIES_FILE_PATH, dormitoriesData);
    }

    @Override
    public void fetchDormitories() {
        String dormitoriesData = FileUtil.readString(DORMITORIES_FILE_PATH);
        mapper.mapStringToDormitories(dormitoriesData)
                .forEach(dormitory -> dormitories.put(dormitory.getId(), dormitory));
    }

    @Override
    public void save(Dormitory dormitory) {
        dormitories.put(dormitory.getId(), dormitory);
    }

    @Override
    public List<Dormitory> findAll() {
        return List.copyOf(dormitories.values());
    }

    @Override
    public void update(Dormitory dormitory) {
        dormitories.put(dormitory.getId(), dormitory);
    }

    @Override
    public void delete(UUID id) {
        dormitories.remove(id);
    }

    @Override
    public List<Dormitory> findByUniversityId(UUID universityId) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .toList();
    }

    @Override
    public Optional<Dormitory> findByDormitoryNumberOrUniversityId(UUID universityId, int dormitoryNumber) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .filter(dormitory -> dormitory.getNumber() == dormitoryNumber)
                .findFirst();
    }

    @Override
    public List<Dormitory> findAllByUniversityId(UUID universityId) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .toList();
    }

    @Override
    public Optional<Dormitory> findByUniversityIdAndNumber(UUID universityId, int number) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .filter(dormitory -> dormitory.getNumber() == number)
                .findFirst();
    }

    @Override
    public List<Dormitory> findAllByUniversityIdOrderBy(UUID universityId, Comparator<Dormitory> dormitoryComparator) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .sorted(dormitoryComparator)
                .toList();
    }

    @Override
    public Optional<Dormitory> findById(UUID id) {
        return dormitories.values()
                .stream()
                .filter(dormitory -> dormitory.getId().equals(id))
                .findFirst();
    }
}
