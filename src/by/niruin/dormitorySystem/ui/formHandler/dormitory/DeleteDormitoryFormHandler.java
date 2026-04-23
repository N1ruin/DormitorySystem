package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.DeleteDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class DeleteDormitoryFormHandler {
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private final FormHandler formHandler;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private int dormitoryNumber;

    public DeleteDormitoryFormHandler(PrintService printService, DormitoryService dormitoryService,
                                      FormHandler formHandler,
                                      DormitoryInputValidationService dormitoryInputValidationService) {
        this.printService = printService;
        this.dormitoryService = dormitoryService;
        this.formHandler = formHandler;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
    }

    public DeleteDormitoryFormHandler inputDormitoryNumber() {
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public DeleteDormitoryDto createDto() {
        return new DeleteDormitoryDto(dormitoryNumber);
    }
}
