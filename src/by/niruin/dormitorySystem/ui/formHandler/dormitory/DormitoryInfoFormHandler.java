package by.niruin.dormitorySystem.ui.formHandler.dormitory;

import by.niruin.dormitorySystem.domain.model.dto.dormitory.SelectedDormitoryNumberFromList;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

@Component
public class DormitoryInfoFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private final DormitoryInputValidationService validationService;

    private int dormitoryNumber;

    public DormitoryInfoFormHandler(FormHandler formHandleService, PrintService printService, DormitoryService dormitoryService,
                                    DormitoryInputValidationService roomInputValidationService) {
        this.formHandler = formHandleService;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
        this.validationService = roomInputValidationService;
    }

    public DormitoryInfoFormHandler handleDormitoryNumber() {
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoryService.getCurrentUniversityDormitoryNumbers()),
                Integer::parseInt,
                validationService::validateNumber);
        return this;
    }

    public SelectedDormitoryNumberFromList createDto() {
        return new SelectedDormitoryNumberFromList(dormitoryNumber);
    }
}
