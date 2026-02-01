package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RegistrationService;
import by.niruin.dormitorySystem.domain.model.dto.user.UserRegistrationDto;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.user.RegistrationFormHandler;

import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_REGISTRATION_FAIL_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_REGISTRATION_SUCCESS_LOG;

public class RegistrationMenu implements Menu {
    private final PrintService printService;
    private final MenuFactory menuFactory;
    private final RegistrationService registrationService;
    private final RegistrationFormHandler registrationFormHandler;
    private final Logger logger = LoggerFactory.getLogger(RegistrationMenu.class);

    public RegistrationMenu(PrintService printService,
                            MenuFactory menuFactory, RegistrationService registrationService, RegistrationFormHandler registrationFormHandler) {
        this.printService = printService;
        this.menuFactory = menuFactory;
        this.registrationService = registrationService;

        this.registrationFormHandler = registrationFormHandler;
    }

    @Override
    public void display() {
        printService.printFillRegistrationFormRequest();
    }

    @Override
    public Menu handleInput() {
        signUp();
        return menuFactory.createStartMenu();
    }

    private void signUp() {
        var dto = registrationFormHandler
                .handleLogin()
                .handlePassword()
                .handleFirstName()
                .handleLastName()
                .handleFatherName()
                .handleGender()
                .handleUniversityNumber()
                .handleDormitoryNumber()
                .createDto();
        try {
            registrationService.signUp(dto);
            printService.printRegistrationSuccessMessage();
            logger.info(USER_REGISTRATION_SUCCESS_LOG.formatted(dto.login()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_REGISTRATION_FAIL_LOG.formatted(dto.login()));
            logger.info(e.getMessage());
        }
    }
}
