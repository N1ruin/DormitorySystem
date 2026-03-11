package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.University;

import java.util.Collection;

public interface UniversityMapper {
    String mapUniversitiesToString(Collection<University> universities);

    Collection<University> mapStringToUniversities(String universitiesData);
}
