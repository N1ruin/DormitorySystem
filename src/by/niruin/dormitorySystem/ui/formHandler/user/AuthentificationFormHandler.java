package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.dto.user.AuthentificationUserDto;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

@Component
public class AuthentificationFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UserInputValidationService userInputValidationService;
    private String login;
    private String password;

    public AuthentificationFormHandler(FormHandler formHandler, PrintService printService,
                                       UserInputValidationService validationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.userInputValidationService = validationService;
    }

    public AuthentificationFormHandler handleLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateInputNotBlank);
        return this;
    }

    public AuthentificationFormHandler handlePassword() {
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
