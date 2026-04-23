package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.university.UniversityInfoDto;
import by.niruin.dormitorySystem.domain.model.dto.university.UniversityStatisticsDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UniversityFormatterImpl implements UniversityFormatter {
    public static final String UNIVERSITY_INFORMATION_INFO = "%s info:\n";
    public static final String UNIVERSITY_STUDY_DURATION_INFO = "University study duration - %dy\n";
    public static final String UNIVERSITY_DORMITORIES_INFO = "University dormitories list:\n";
    public static final String DORMITORY_NAME_WITH_NUMBER_ORDER_PATTERN = "%d. Dormitory №%d\n";
    public static final String UNIVERSITY_NAME_WITH_NUMBER_ORDER_PATTERN = "%d. %s\n";
    public static final String UNIVERSITY_STATS = "%s statistics:\n";
    public static final String DORMITORIES_COUNT_MESSAGE = "Dormitories count: %d\n";
    public static final String AVAILABLE_DORMITORIES_COUNT_MESSAGE = "Available dormitoriesCount: %d\n";
    public static final String NOT_AVAILABLE_DORMITORIES_COUNT = "Not available dormitories count: %d\n";
    public static final String ROOMS_COUNT_MESSAGE = "Rooms count: %d\n";
    public static final String AVAILABLE_ROOMS_COUNT_MESSAGE = "Available rooms count: %d\n";
    public static final String AVAILABLE_MALE_ROOMS_COUNT_MESSAGE = "Available male rooms count: %d\n";
    public static final String AVAILABLE_FEMALE_ROOMS_COUNT_MESSAGE = "Available female rooms count: %d\n";
    public static final String STUDENTS_COUNT_MESSAGE = "Students count: %d\n";
    public static final String MALE_STUDENTS_COUNT_MESSAGE = "Male students count: %d\n";
    public static final String FEMALE_STUDENTS_COUNT_MESSAGE = "Female students count: %d\n";
    public static final String STUDENTS_IN_DORMITORIES_COUNT_MESSAGE = "Students in dormitories count: %d\n";
    public static final String STUDENTS_WITHOUT_DORMITORY_COUNT_MESSAGE = "Students without dormitoriy count: %d\n";

    @Override
    public String formatUniversitiesToUniversitiesInfo(UniversityInfoDto... dtos) {
        return Arrays.stream(dtos)
                .map(this::formatUniversityInfo)
                .collect(Collectors.joining());
    }

    @Override
    public String formatStudentNamesToNumeredNames(List<String> names, String title) {
        return title + IntStream.range(0, names.size())
                .mapToObj(i -> UNIVERSITY_NAME_WITH_NUMBER_ORDER_PATTERN.formatted(i + 1, names.get(i)))
                .collect(Collectors.joining());
    }

    @Override
    public String formatStatistics(UniversityStatisticsDto dto) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append(UNIVERSITY_STATS.formatted(dto.universityName()));
        stringBuilder.append(DORMITORIES_COUNT_MESSAGE.formatted(dto.dormitoriesCount()));
        stringBuilder.append(AVAILABLE_DORMITORIES_COUNT_MESSAGE.formatted(dto.availableDormitoriesCount()));
        stringBuilder.append(NOT_AVAILABLE_DORMITORIES_COUNT.formatted(dto.notAvailableDormitoriesCount()));
        stringBuilder.append(ROOMS_COUNT_MESSAGE.formatted(dto.roomsCountFromAllDormitories()));
        stringBuilder.append(AVAILABLE_ROOMS_COUNT_MESSAGE.formatted(dto.availableMaleRoomsCount() + dto.availableFemaleRoomsCount()));
        stringBuilder.append(AVAILABLE_MALE_ROOMS_COUNT_MESSAGE.formatted(dto.availableMaleRoomsCount()));
        stringBuilder.append(AVAILABLE_FEMALE_ROOMS_COUNT_MESSAGE.formatted(dto.availableFemaleRoomsCount()));
        stringBuilder.append(STUDENTS_COUNT_MESSAGE.formatted(dto.studentsCount()));
        stringBuilder.append(MALE_STUDENTS_COUNT_MESSAGE.formatted(dto.maleStudents()));
        stringBuilder.append(FEMALE_STUDENTS_COUNT_MESSAGE.formatted(dto.femaleStudents()));
        stringBuilder.append(STUDENTS_IN_DORMITORIES_COUNT_MESSAGE.formatted(dto.studentsInDormitories()));
        stringBuilder.append(STUDENTS_WITHOUT_DORMITORY_COUNT_MESSAGE.formatted(dto.studentsWithoutDormitory()));

        return stringBuilder.toString();
    }

    private String formatUniversityInfo(UniversityInfoDto dto) {
        var sb = new StringBuilder();

        sb.append(UNIVERSITY_INFORMATION_INFO.formatted(dto.universityName()));
        sb.append(UNIVERSITY_STUDY_DURATION_INFO.formatted(dto.studyDuration()));
        sb.append(UNIVERSITY_DORMITORIES_INFO);
        sb.append(formatDormitoriesList(dto.universityDormitoriesNumbers()));

        return sb.toString();
    }

    private String formatDormitoriesList(List<Integer> dormitoryNumberList) {
        return IntStream.range(0, dormitoryNumberList.size())
                .mapToObj(i -> DORMITORY_NAME_WITH_NUMBER_ORDER_PATTERN.formatted(i + 1, dormitoryNumberList.get(i)))
                .collect(Collectors.joining());
    }
}
