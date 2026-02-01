package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.DeleteDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

@Component
public class DeleteDormitoryFormHandler {
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private final FormHandler formHandleService;
    private final DormitoryInputValidationService validationService;
    private int dormitoryNumber;

    public DeleteDormitoryFormHandler(PrintService printService, DormitoryService roomService, FormHandler formHandleService,
                                      DormitoryInputValidationService validationService) {
        this.printService = printService;
        this.dormitoryService = roomService;
        this.formHandleService = formHandleService;
        this.validationService = validationService;
    }

    public DeleteDormitoryFormHandler handleDormitoryNumber() {
        dormitoryNumber = formHandleService.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public DeleteDormitoryDto createDto() {
        return new DeleteDormitoryDto(dormitoryNumber);
    }
}
