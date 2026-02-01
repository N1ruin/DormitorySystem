package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.exception.InputValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;
import static by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService.AVAILABLE_FOR_LIVING_PATTERN;
import static by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService.NUMBER_PATTERN;


@Component
public class UniversityInputValidationService {
    public static final String UNIVERSITY_NAME_PATTERN = "^[А-Яа-я\\s\\-.()]+$";

    public void validateNumber(String input) {
        if (!input.matches(NUMBER_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateName(String input) {
        if (!input.matches(UNIVERSITY_NAME_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }
}
