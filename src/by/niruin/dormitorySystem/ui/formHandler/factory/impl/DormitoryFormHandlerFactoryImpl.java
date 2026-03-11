package by.niruin.dormitorySystem.ui.formHandler.factory.impl;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.factory.DormitoryFormHandlerFactory;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.dormitory.*;

@Component
public class DormitoryFormHandlerFactoryImpl implements DormitoryFormHandlerFactory {
    private final FormHandler formHandler;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final PrintService printService;
    private final DormitoryService dormitoryService;

    public DormitoryFormHandlerFactoryImpl(FormHandler formHandler,
                                           DormitoryInputValidationService dormitoryInputValidationService,
                                           PrintService printService, DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
    }

    @Override
    public CreateDormitoryFormHandler getCreateDormitoryFormHandler() {
        return new CreateDormitoryFormHandler(formHandler, dormitoryInputValidationService, printService);
    }

    @Override
    public DeleteDormitoryFormHandler getDeleteDormitoryFormHandler() {
        return new DeleteDormitoryFormHandler(printService, dormitoryService, formHandler,
                dormitoryInputValidationService);
    }

    @Override
    public DormitoryInfoFormHandler getDormitoryInfoFormHandler() {
        return new DormitoryInfoFormHandler(formHandler, printService, dormitoryService,
                dormitoryInputValidationService);
    }

    @Override
    public SelectCurrentDormitoryFormHandler getSelectCurrentDormitoryFormHandler() {
        return new SelectCurrentDormitoryFormHandler(formHandler, printService, dormitoryService,
                dormitoryInputValidationService);
    }

    @Override
    public UpdateDormitoryFormHandler getUpdateDormitoryFormHandler() {
        return new UpdateDormitoryFormHandler(formHandler, dormitoryInputValidationService, printService,
                dormitoryService);
    }
}
