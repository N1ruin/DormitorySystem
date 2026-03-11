package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.university.UniversityInfoDto;
import by.niruin.dormitorySystem.domain.model.dto.university.UniversityStatisticsDto;

import java.util.List;

public interface UniversityFormatter {
    String formatUniversitiesToUniversitiesInfo(UniversityInfoDto... dtos);

    String formatStudentNamesToNumeredNames(List<String> names, String title);

    String formatStatistics(UniversityStatisticsDto dto);
}
