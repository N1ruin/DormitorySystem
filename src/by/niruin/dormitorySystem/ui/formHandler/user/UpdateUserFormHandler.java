package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.dto.user.UpdateUserDto;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

@Component
public class UpdateUserFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UserInputValidationService userInputValidationService;

    private String login;
    private String password;
    private Role role;
    private String lastName;

    public UpdateUserFormHandler(FormHandler formHandler, PrintService printService, UserInputValidationService userInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.userInputValidationService = userInputValidationService;
    }

    public UpdateUserFormHandler handleLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateLogin);
        return this;
    }

    public UpdateUserFormHandler handlePassword() {
        password = formHandler.handleInputString(
                printService::printInputPasswordRequestMessage,
                Function.identity(),
                userInputValidationService::validatePassword);
        return this;
    }

    public UpdateUserFormHandler handleLastName() {
        lastName = formHandler.handleInputString(
                printService::printInputLastNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public UpdateUserFormHandler handleRole() {
        int roleInput = formHandler.handleInputString(
                printService::printInputRoleRequestMessage,
                Integer::parseInt,
                userInputValidationService::validateRole
        );

        for (int i = 1; i <= Role.values().length; i++) {
            if (roleInput == i) {
                role = Role.values()[i - 1];
            }
        }

        return this;
    }

    public UpdateUserDto createDto() {
        return new UpdateUserDto(login, password, lastName, role);
    }
}
