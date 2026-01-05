package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.University;
import by.niruin.dormitorySystem.domain.model.dto.UniversityNumbersDto;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;

import java.util.List;

public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
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
