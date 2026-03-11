package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.dto.user.AuthentificationUserDto;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class AuthenticationFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UserInputValidationService userInputValidationService;
    private String login;
    private String password;

    public AuthenticationFormHandler(FormHandler formHandler, PrintService printService,
                                     UserInputValidationService userInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.userInputValidationService = userInputValidationService;
    }

    public AuthenticationFormHandler handleLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateInputNotBlank);
        return this;
    }

    public AuthenticationFormHandler handlePassword() {
        password = formHandler.handleInputString(
                printService::printInputPasswordRequestMessage,
                Function.identity(),
                userInputValidationService::validateInputNotBlank);
        return this;
    }

    public AuthentificationUserDto createDto() {
        return new AuthentificationUserDto(login, password);
    }
}
