package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.student.StudentInfoDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StudentFormatterImpl implements StudentFormatter {
    public static final String STUDENT_INFORMATION = "%s information:\n";
    public static final String UNIVERSITY_NAME_INFO = "University name: %s\n";
    public static final String GENDER_INFO = "Gender: %s\n";
    public static final String DORMITORY_NUMBER_INFO = "Dormitory number: %d\n";
    public static final String DATE_OF_ROOM_CHECK_IN_INFO = "Date of room check in: %s\n";
    public static final String DATE_OF_ROOM_CHECK_OUT_INFO = "Date of room check out: %s\n";
    public static final String UNKNOWN_DATE_OF_ROOM_CHECK_OUT_INFO = "Date of room check out: unknown\n";
    public static final String ROOM_NUMBER_INFO = "Room number: %d\n";
    public static final String DATE_OF_START_EDUCATION_INFO = "Date of start education: %s\n";
    public static final String DATE_OF_END_EDUCATION_INFO = "Date of ending education: %s\n";
    public static final String STUDENT_NAME_WITH_NUMBER_ORDER_PATTERN = "%d. %s\n";

    @Override
    public String formatStudentsToStudentsInfo(StudentInfoDto... dtos) {
        return Arrays.stream(dtos)
                .map(this::formatStudentInfo)
                .collect(Collectors.joining());
    }

    @Override
    public String formatStudentNamesToNumeredNames(List<String> names, String title) {
        return title + IntStream.range(0, names.size())
                .mapToObj(i -> STUDENT_NAME_WITH_NUMBER_ORDER_PATTERN.formatted(i + 1, names.get(i)))
                .collect(Collectors.joining());
    }

    private String formatStudentInfo(StudentInfoDto dto) {
        var stringBuilder = new StringBuilder();

        stringBuilder.append(STUDENT_INFORMATION.formatted(dto.fullName()))
                .append(GENDER_INFO.formatted(dto.gender()));

        if (dto.universityName() != null) {
            stringBuilder.append(UNIVERSITY_NAME_INFO.formatted(dto.universityName()))
                    .append(DATE_OF_START_EDUCATION_INFO.formatted(dto.startEducationDate()))
                    .append(DATE_OF_END_EDUCATION_INFO.formatted(dto.endEducationDate()));
        }

        if (dto.dormitoryNumber() != null) {
            stringBuilder.append(DORMITORY_NUMBER_INFO.formatted(dto.dormitoryNumber()));
        }

        if (dto.roomNumber() != null) {
            stringBuilder.append(ROOM_NUMBER_INFO.formatted(dto.roomNumber()))
                    .append(DATE_OF_ROOM_CHECK_IN_INFO.formatted(dto.roomCheckInDate()))
                    .append(dto.roomCheckOutDate() == null
                            ? UNKNOWN_DATE_OF_ROOM_CHECK_OUT_INFO
                            : DATE_OF_ROOM_CHECK_OUT_INFO.formatted(dto.roomCheckOutDate()));
        }

        return stringBuilder.toString();
    }
}
