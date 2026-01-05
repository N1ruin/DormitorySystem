package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.model.dto.UniversityNumbersDto;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryUniversityRepository;

import java.util.List;

@Component
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(@Qualifier(InMemoryUniversityRepository.class) UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public UniversityNumbersDto getUniversitiesNames() {
        List<String> universityNames = universityRepository.findAll().stream()
                .map(University::getName)
                .toList();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= universityNames.size(); i++) {
            stringBuilder.append("%d. %s\n".formatted(i, universityNames.get(i - 1)));
        }
        return new UniversityNumbersDto(stringBuilder.toString());
    }
}
