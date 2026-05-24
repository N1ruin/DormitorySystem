package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.mapper.UniversityMapper;
import by.niruin.dormitorySystem.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InMemoryUniversityRepository implements UniversityRepository {
    private final Map<UUID, University> universities = new HashMap<>();
    private final UniversityMapper mapper;
    public static final Path UNIVERSITIES_FILE_PATH = Paths.get("./resources/entity/university.txt");

    public InMemoryUniversityRepository(UniversityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void persistUniversities() {
        String universitiesData = mapper.mapUniversitiesToString(universities.values());
        FileUtil.writeString(UNIVERSITIES_FILE_PATH, universitiesData);
    }

    @Override
    public void fetchUniversities() {
        String universitiesData = FileUtil.readString(UNIVERSITIES_FILE_PATH);
        mapper.mapStringToUniversities(universitiesData).forEach(university -> universities.put(university.getId(), university));

    }

    @Override
    public void save(University university) {
        universities.put(university.getId(), university);
    }

    @Override
    public List<University> findAll() {
        return List.copyOf(universities.values());
    }

    @Override
    public void update(University university) {
        universities.put(university.getId(), university);
    }

    @Override
    public void delete(UUID id) {
        universities.remove(id);
    }

    @Override
    public Optional<University> findByName(String name) {
        return universities.values()
                .stream()
                .filter(university -> university.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<University> findAllOrderBy(Comparator<University> comparator) {
        return universities.values()
                .stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public Optional<University> findById(UUID universityId) {
        return universities.values()
                .stream()
                .filter(university -> university.getId().equals(universityId))
                .findFirst();
    }
}
