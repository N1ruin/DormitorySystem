package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.University;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UniversityRepository {
    void persistUniversities();

    void fetchUniversities();

    void save(University university);

    List<University> findAll();

    void update(University university);

    void delete(UUID uuid);

    Optional<University> findByName(String name);

    List<University> findAllOrderBy(Comparator<University> comparator);

    Optional<University> findById(UUID universityId);
}
