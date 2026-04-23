package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.*;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.university.*;

@Component
public class UniversityFormHandlerFactory {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityInputValidationService universityInputValidationService;

    public UniversityFormHandlerFactory(FormHandler formHandler, PrintService printService,
                                        UniversityService universityService,
                                        UniversityInputValidationService universityInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.universityService = universityService;
        this.universityInputValidationService = universityInputValidationService;
    }

    public CreateUniversityFormHandler getCreateUniversityFormHandler() {
        return new CreateUniversityFormHandler(formHandler, universityInputValidationService, printService);
    }

    public DeleteUniversityFormHandler getDeleteUniversityFormHandler() {
        return new DeleteUniversityFormHandler(printService, universityService, formHandler,
                universityInputValidationService);
    }

    public SelectCurrentUniversityFormHandler getSelectCurrentUniversityFormHandler() {
        return new SelectCurrentUniversityFormHandler(formHandler, printService, universityService,
                universityInputValidationService);
    }

    public UniversityInfoFormHandler getUniversityInfoFormHandler() {
        return new UniversityInfoFormHandler(formHandler, printService, universityService,
                universityInputValidationService);
    }

    public UpdateUniversityFormHandler getUpdateUniversityFormHandler() {
        return new UpdateUniversityFormHandler(formHandler, printService, universityService,
                universityInputValidationService);
    }
}
