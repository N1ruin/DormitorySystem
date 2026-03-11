package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.UpdateDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class UpdateDormitoryFormHandler {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private int dormitoryNumber;
    private boolean isAvailableForLiving;

    public UpdateDormitoryFormHandler(FormHandler formHandler,
                                      DormitoryInputValidationService dormitoryInputValidationService,
                                      PrintService printService, DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
    }

    public UpdateDormitoryFormHandler handleNumber() {
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public UpdateDormitoryFormHandler handleAvailable() {
        String availableForLivingInput = formHandler.handleInputString(
                printService::printInputAvailableForLivingRequestMessage,
                Function.identity(),
                dormitoryInputValidationService::validateAvailableForLiving);
        isAvailableForLiving = availableForLivingInput.equalsIgnoreCase("yes");
        return this;
    }

    public UpdateDormitoryDto createDto() {
        return new UpdateDormitoryDto(dormitoryNumber, isAvailableForLiving);
    }
}
