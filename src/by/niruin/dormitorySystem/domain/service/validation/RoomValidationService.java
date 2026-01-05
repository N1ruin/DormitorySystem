package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.model.dto.CreateRoomDto;
import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.repository.RoomRepository;
import by.niruin.dormitorySystem.exception.EntityValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryRoomRepository;

import java.util.ArrayList;
import java.util.List;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;

@Component
public class RoomValidationService {
    public static final int MAX_ROOMS_IN_DORMITORY = 1000;
    private final RoomRepository roomRepository;

    public RoomValidationService(@Qualifier(InMemoryRoomRepository.class) RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void validateCreateData(CreateRoomDto dto) {
        List<String> validationErrors = new ArrayList<>();

        if (!isNumberFree(dto.number())) {
            validationErrors.add(ROOM_NUMBER_EXIST_MESSAGE.formatted(dto.number()));
        }
        if (isNumberOutOfMaximumInDormitory(dto.number())) {
            validationErrors.add(ROOM_NUMBER_OUT_OF_BOUNDS_MESSAGE.formatted(dto.number()));
        }
        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(CREATING_ROOM_FAIL_MESSAGE + errors);
        }
    }

    public void validateRoomNumber(int number) {
        List<String> validationErrors = new ArrayList<>();

        if (isNumberFree(number)) {
            validationErrors.add(ROOM_WITH_NUMBER_NOT_EXIST_MESSAGE.formatted(number));
        }
        if (isNumberOutOfMaximumInDormitory(number)) {
            validationErrors.add(ROOM_NUMBER_OUT_OF_BOUNDS_MESSAGE.formatted(number));
        }
        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(OPERATION_FAILED_MESSAGE + errors);
        }
    }

    private boolean isNumberFree(int number) {
        return roomRepository.findByCurrentDormitoryId()
                .stream()
                .map(Room::getNumber)
                .noneMatch(roomNumber -> roomNumber == number);
    }

    private boolean isNumberOutOfMaximumInDormitory(int number) {
        return number <= 0 || number > MAX_ROOMS_IN_DORMITORY;
    }
}
