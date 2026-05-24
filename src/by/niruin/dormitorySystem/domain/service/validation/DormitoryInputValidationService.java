package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.exception.InputValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;
import static by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService.AVAILABLE_FOR_LIVING_PATTERN;
import static by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService.NUMBER_PATTERN;

@Component
public class DormitoryInputValidationService {
    private final DormitoryService dormitoryService;

    public DormitoryInputValidationService(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }


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

    public void validateNumberInList(String input) {
        validateNumber(input);

        int intInput = Integer.parseInt(input);
        var dormitoriesFromCurrentUniversity = dormitoryService.getAllFromCurrentUniversity();

        if (intInput <= 0 || intInput > dormitoriesFromCurrentUniversity.size()) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);

        }
    }
}
