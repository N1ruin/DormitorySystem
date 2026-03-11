package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.dto.dormitory.CreateDormitoryDto;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.exception.EntityValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;
import static by.niruin.dormitorySystem.constant.ConsoleMessage.OPERATION_FAILED_MESSAGE;

@Component
public class DormitoryValidationService {
    private final DormitoryRepository dormitoryRepository;
    private final RoomService roomService;

    public DormitoryValidationService(DormitoryRepository dormitoryRepository, RoomService roomService) {
        this.dormitoryRepository = dormitoryRepository;
        this.roomService = roomService;
    }

    public void validateCreateData(CreateDormitoryDto dto) {
        List<String> validationErrors = new ArrayList<>();

        if (!isNumberFree(dto.number())) {
            validationErrors.add(DORMITORY_NUMBER_EXIST_MESSAGE.formatted(dto.number()));
        }
        if (isNumberLessThanZero(dto.number())) {
            validationErrors.add(DORMITORY_NUMBER_CANNOT_BE_LESS_THAN_ZERO_MESSAGE);
        }
        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(CREATING_ROOM_FAIL_MESSAGE + errors);
        }
    }

    public void validateDormitoryNumber(int number) {
        List<String> validationErrors = new ArrayList<>();

        if (isNumberFree(number)) {
            validationErrors.add(DORMITORY_WITH_NUMBER_NOT_EXIST_MESSAGE.formatted(number));
        }
        if (isNumberLessThanZero(number)) {
            validationErrors.add(DORMITORY_NUMBER_CANNOT_BE_LESS_THAN_ZERO_MESSAGE);
        }
        if (!validationErrors.isEmpty()) {
            String errors = String.join("\n", validationErrors);
            throw new EntityValidationException(OPERATION_FAILED_MESSAGE + errors);
        }
    }

    public void validateDormitoriesExist(List<Dormitory> dormitories) {
        if (dormitories.isEmpty()) {
            throw new EntityValidationException("Dormitories at the current university not found");
        }
    }

    private boolean isNumberFree(int number) {
        return dormitoryRepository.findAll().stream()
                .noneMatch(dormitory -> dormitory.getNumber() == number);
    }

    private boolean isNumberLessThanZero(int number) {
        return number <= 0;
    }

    public void validateDormitoryHaveFreeRooms(Gender gender, UUID dormitoryId) {
        if (roomService.getFreeRooms(gender, dormitoryId).isEmpty()) {
            throw new EntityValidationException("Dormitory don't have free rooms");
        }
    }
}
