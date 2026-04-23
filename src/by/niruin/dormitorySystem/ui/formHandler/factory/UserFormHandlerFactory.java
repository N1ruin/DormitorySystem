package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.*;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.user.*;

@Component
public class UserFormHandlerFactory {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UserInputValidationService userInputValidationService;
    private final RoomInputValidationService roomInputValidationService;
    private final UniversityInputValidationService universityInputValidationService;
    private final DormitoryService dormitoryService;
    private final UniversityService universityService;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;

    public UserFormHandlerFactory(FormHandler formHandler, PrintService printService,
                                  UserInputValidationService userInputValidationService,
                                  RoomInputValidationService roomInputValidationService,
                                  UniversityInputValidationService universityInputValidationService,
                                  DormitoryService dormitoryService, UniversityService universityService,
                                  UniversityRepository universityRepository, DormitoryRepository dormitoryRepository) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.userInputValidationService = userInputValidationService;
        this.roomInputValidationService = roomInputValidationService;
        this.universityInputValidationService = universityInputValidationService;
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    public AuthenticationFormHandler getAuthenticationFormHandler() {
        return new AuthenticationFormHandler(formHandler, printService, userInputValidationService);
    }

    public DeleteUserFormHandler getDeleteUserFormHandler() {
        return new DeleteUserFormHandler(printService, formHandler, userInputValidationService);
    }

    public RegistrationFormHandler getRegistrationFormHandler() {
        return new RegistrationFormHandler(formHandler, userInputValidationService, printService, universityService,
                universityRepository, dormitoryRepository, universityInputValidationService, roomInputValidationService,
                dormitoryService);
    }

    public UpdateUserFormHandler getUpdateUserFormHandler() {
        return new UpdateUserFormHandler(formHandler, printService, userInputValidationService);
    }

    public UserInfoFormHandler getUserInfoFormHandler() {
        return new UserInfoFormHandler(printService, formHandler, userInputValidationService);
    }
}
