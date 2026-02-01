package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DormitoryRepository {
    void persistDormitories();

    void fetchDormitories();

    void save(Dormitory dormitory);

    List<Dormitory> findAll();

    void update(Dormitory dormitory);

    void delete(UUID uuid);

    List<Dormitory> findByUniversityId(UUID universityId);

    Optional<Dormitory> findByDormitoryNumberOrUniversityId(UUID universityId, int dormitoryNumber);

    List<Dormitory> findAllByUniversityId(UUID universityId);

    Optional<Dormitory> findByUniversityIdAndNumber(UUID universityId, int number);

    List<Dormitory> findAllByUniversityIdOrderBy(UUID universityId, Comparator<Dormitory> dormitoryComparator);

    Optional<Dormitory> findById(UUID id);
}
