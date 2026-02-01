package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.DormitoryInfoDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DormitoryFormatterImpl implements DormitoryFormatter {
    public static final String DORMITORY_INFORMATION_INFO = "Dormitory info with number №%d: \n";
    public static final String DORMITORY_ROOMS_COUNT_INFO = "Rooms count:%d\n";
    public static final String IS_AVAILABLE_FOR_LIVING_INFO = "Dormitory is available for living:%s\n";
    public static final String DORMITORY_NAME_WITH_NUMBER_ORDER_PATTERN = "%d. Dormitory №%s\n";

    @Override
    public String formatDormitoriesToDormitoriesInfo(DormitoryInfoDto... dtos) {
        return Arrays.stream(dtos)
                .map(dto -> String.join("",
                        DORMITORY_INFORMATION_INFO.formatted(dto.number()),
                        DORMITORY_ROOMS_COUNT_INFO.formatted(dto.roomsCount()),
                        IS_AVAILABLE_FOR_LIVING_INFO.formatted(dto.availableForLiving())))
                .collect(Collectors.joining());
    }

    @Override
    public String formatDormitoriesNumbersToNumeredNumbers(List<Integer> numbers, String title) {
        return title + IntStream.range(0, numbers.size())
                .mapToObj(i -> DORMITORY_NAME_WITH_NUMBER_ORDER_PATTERN.formatted(i + 1, numbers.get(i)))
                .collect(Collectors.joining());
    }
}
