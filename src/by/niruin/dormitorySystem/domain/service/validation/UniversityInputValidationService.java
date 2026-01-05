package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.exception.InputValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;


@Component
public class UniversityInputValidationService {
    public static final String NUMBER_PATTERN = "^\\d+$";

    public void validateNumber(String input) {
        if (!input.matches(NUMBER_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }
}
