package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.room.RoomInfoDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RoomFormatterImpl implements RoomFormatter {
    public static final String ROOM_INFO = "Room with number №%d information: \n";
    public static final String GENDER_INFO = "Gender: %s\n";
    public static final String ROOM_CAPACITY_INFO = "Seats count:%d\n";
    public static final String FREE_CAPACITY_INFO = "Free capacity: %d\n";
    public static final String STUDENTS_IN_ROOM_LIST_INFO = "Students in room:\n";
    public static final String IS_AVAILABLE_FOR_LIVING_INFO = "Room is available for living:%s\n";
    public static final String ROOM_EMPTY_INFO = "Room is empty!";
    public static final String STUDENT_NAME_WITH_NUMBER_ORDER_PATTERN = "%d. %s\n";
    public static final String NEW_LINE_SYMBOL = "\n";

    @Override
    public String formatRoomsToRoomsInfo(RoomInfoDto... dtos) {
        return Arrays.stream(dtos)
                .map(dto -> {
                    int freeCapacity = dto.roomCapacity() - dto.inhabitantsCount();

                    String studentsNames = IntStream.range(0, dto.studentsNames().size())
                            .mapToObj(i -> STUDENT_NAME_WITH_NUMBER_ORDER_PATTERN.formatted(
                                    i + 1,
                                    dto.studentsNames().get(i)
                            ))
                            .collect(Collectors.joining(NEW_LINE_SYMBOL));

                    return String.join("",
                            ROOM_INFO.formatted(dto.number()),
                            ROOM_CAPACITY_INFO.formatted(dto.roomCapacity()),
                            GENDER_INFO.formatted(dto.gender()),
                            FREE_CAPACITY_INFO.formatted(freeCapacity),
                            dto.studentsNames().isEmpty()
                                    ? ROOM_EMPTY_INFO
                                    : STUDENTS_IN_ROOM_LIST_INFO + NEW_LINE_SYMBOL + studentsNames,
                            IS_AVAILABLE_FOR_LIVING_INFO.formatted(dto.availableForLiving())
                    );
                })
                .collect(Collectors.joining());
    }
}
