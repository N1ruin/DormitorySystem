package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.dormitory.*;

@Component
public class DormitoryFormHandlerFactory {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final PrintService printService;
    private final DormitoryService dormitoryService;

    public DormitoryFormHandlerFactory(FormHandler formHandler,
                                       DormitoryInputValidationService dormitoryInputValidationService,
                                       PrintService printService, DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
    }

    public CreateDormitoryFormHandler getCreateDormitoryFormHandler() {
        return new CreateDormitoryFormHandler(formHandler, dormitoryInputValidationService, printService);
    }

    public DeleteDormitoryFormHandler getDeleteDormitoryFormHandler() {
        return new DeleteDormitoryFormHandler(printService, dormitoryService, formHandler,
                dormitoryInputValidationService);
    }

    public DormitoryInfoFormHandler getDormitoryInfoFormHandler() {
        return new DormitoryInfoFormHandler(formHandler, printService, dormitoryService,
                dormitoryInputValidationService);
    }

    public SelectCurrentDormitoryFormHandler getSelectCurrentDormitoryFormHandler() {
        return new SelectCurrentDormitoryFormHandler(formHandler, printService, dormitoryService,
                dormitoryInputValidationService);
    }

    public UpdateDormitoryFormHandler getUpdateDormitoryFormHandler() {
        return new UpdateDormitoryFormHandler(formHandler, dormitoryInputValidationService, printService,
                dormitoryService);
    }
}
