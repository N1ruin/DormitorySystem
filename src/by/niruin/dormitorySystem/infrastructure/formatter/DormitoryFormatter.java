package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.DormitoryInfoDto;

import java.util.List;

public interface DormitoryFormatter {
    String formatDormitoriesToDormitoriesInfo(DormitoryInfoDto... dtos);

    String formatDormitoriesNumbersToNumeredNumbers(List<Integer> numbers, String title);
}
