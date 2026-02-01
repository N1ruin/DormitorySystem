package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.UpdateDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

@Component
public class UpdateDormitoryFormHandler {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService validationService;
    private final PrintService printService;
    private final DormitoryService dormitoryService;

    private int dormitoryNumber;
    private boolean availableForLiving;

    public UpdateDormitoryFormHandler(FormHandler formHandler, DormitoryInputValidationService validationService, PrintService printService, DormitoryService roomService) {
        this.formHandler = formHandler;
        this.validationService = validationService;
        this.printService = printService;
        this.dormitoryService = roomService;
    }

    public UpdateDormitoryFormHandler handleNumber() {
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public UpdateDormitoryFormHandler handleAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                validationService::validateAvailableForLiving);
        availableForLiving = availableForLivingInput.equalsIgnoreCase("yes");
        return this;
    }

    public UpdateDormitoryDto createDto() {
        return new UpdateDormitoryDto(dormitoryNumber, availableForLiving);
    }
}
