package by.niruin.dormitorySystem.infrastructure.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.mapper.DormitoryMapper;
import by.niruin.dormitorySystem.infrastructure.mapper.EntityMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryDormitoryRepository extends InMemoryRepositoryBase<Dormitory> implements DormitoryRepository {
    public static final Path DORMITORIES_FILE_PATH = Paths.get("./resources/entity/dormitory.txt");

    public InMemoryDormitoryRepository(@Qualifier(DormitoryMapper.class) EntityMapper<Dormitory> dormitoryMapper) {
        super(dormitoryMapper, DORMITORIES_FILE_PATH);
    }

    @Override
    public List<Dormitory> findByUniversityId(UUID universityId) {
        return entities.values().stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universityId))
                .toList();
    }

    @Override
    public Optional<Dormitory> findByDormitoryNumberOrUniversityId(UUID universiryId, int dormitoryNumber) {
        return entities.values().stream()
                .filter(dormitory -> dormitory.getUniversityId().equals(universiryId))
                .filter(dormitory -> dormitory.getNumber() == dormitoryNumber)
                .findFirst();
    }
}
