package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.exception.InputValidationException;

import java.util.List;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;
import static by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService.NUMBER_PATTERN;

public class StudentInputValidationService {
    private final UserInputValidationService userInputValidationService;

    public StudentInputValidationService(UserInputValidationService userInputValidationService) {
        this.userInputValidationService = userInputValidationService;
    }

    public void validateNumber(String input) {
        if (!input.matches(NUMBER_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateNumberInList(String input, List<Student> students) {
        validateNumber(input);

        int intInput = Integer.parseInt(input);

        if (intInput <= 0 || intInput > students.size()) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateLastName(String input) {
        userInputValidationService.validateName(input);
    }
}
