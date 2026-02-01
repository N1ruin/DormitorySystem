package by.niruin.dormitorySystem.domain.repository;

import by.niruin.dormitorySystem.domain.model.University;

import java.util.Optional;

public interface UniversityRepository extends Repository<University> {

    Optional<University> findByName(String name);
}
