package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.dto.DormitoryNumbersDto;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.UUID;

@Component
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    public DormitoryService(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    public DormitoryNumbersDto getDormitoriesNumber(UUID universityId) {
        var dormitoryNames = dormitoryRepository.findAll().stream()
                .filter(dorm -> dorm.getUniversityId().equals(universityId))
                .toList();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= dormitoryNames.size(); i++) {
            stringBuilder.append("%d. Dormitory №%s\n".formatted(i, dormitoryNames.get(i - 1).getNumber() + 1));
        }
        return new DormitoryNumbersDto(stringBuilder.toString());
    }
}
