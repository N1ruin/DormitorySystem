package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.Dormitory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DormitoryRepository extends Repository<Dormitory> {
    List<Dormitory> findByUniversityId(UUID universityId);

    Optional<Dormitory> findByDormitoryNumberOrUniversityId(UUID universiryId, int dormitoryNumber);
}
