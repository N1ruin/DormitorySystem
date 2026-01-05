package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.exception.InputValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;

@Component
public class RoomInputValidationService {
    public static final String AVAILABLE_FOR_LIVING_PATTERN = "(?i)^(yes|no)$";
    public static final String GENDER_PATTERN = "(?i)^(male|female)$";
    public static final String NUMBER_PATTERN = "^\\d+$";

    public void validateNumber(String input) {
        if (!input.matches(NUMBER_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateAvailableForLiving(String input) {
        if (!input.matches(AVAILABLE_FOR_LIVING_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateGender(String input) {
        if (!input.matches(GENDER_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }
}
