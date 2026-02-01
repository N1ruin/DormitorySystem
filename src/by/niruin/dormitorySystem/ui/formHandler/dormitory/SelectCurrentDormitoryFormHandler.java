package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.SelectCurrentDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

@Component
public class SelectCurrentDormitoryFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private final DormitoryInputValidationService dormitoryInputValidationService;

    private int dormitoryNumber;

    public SelectCurrentDormitoryFormHandler(FormHandler formHandler, PrintService printService, DormitoryService dormitoryService, DormitoryInputValidationService dormitoryInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
    }

    public SelectCurrentDormitoryFormHandler handleDormitoryNumber() {
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public SelectCurrentDormitoryDto createDto() {
        return new SelectCurrentDormitoryDto(dormitoryNumber);
    }

}
