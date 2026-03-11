package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.dto.user.DeleteUserDto;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class DeleteUserFormHandler {
    private final PrintService printService;
    private final FormHandler formHandler;
    private final UserInputValidationService userInputValidationService;
    private String login;

    public DeleteUserFormHandler(PrintService printService, FormHandler formHandler, UserInputValidationService userInputValidationService) {
        this.printService = printService;
        this.formHandler = formHandler;
        this.userInputValidationService = userInputValidationService;
    }

    public DeleteUserFormHandler handleLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateLogin);
        return this;
    }

    public DeleteUserDto createDto() {
        return new DeleteUserDto(login);
    }
}
