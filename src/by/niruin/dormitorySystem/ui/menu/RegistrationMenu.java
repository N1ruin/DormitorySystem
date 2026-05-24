package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RegistrationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.UserFormHandlerFactory;

import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_REGISTRATION_FAIL_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_REGISTRATION_SUCCESS_LOG;

public class RegistrationMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationMenu.class);
    private final PrintService printService;
    private final MenuLocator menuLocator;
    private final UserFormHandlerFactory userFormHandlerFactory;
    private final RegistrationService registrationService;

    public RegistrationMenu(PrintService printService, MenuLocator menuLocator,
                            UserFormHandlerFactory userFormHandlerFactory, RegistrationService registrationService) {
        this.printService = printService;
        this.menuLocator = menuLocator;
        this.userFormHandlerFactory = userFormHandlerFactory;
        this.registrationService = registrationService;
    }

    @Override
    public void display() {
        printService.printFillRegistrationFormRequest();
    }

    @Override
    public Menu handleInput() {
        signUp();
        return menuLocator.getMenu(StartMenu.class);
    }

    private void signUp() {
        var dto = userFormHandlerFactory.getRegistrationFormHandler()
                .inputLogin()
                .inputPassword()
                .inputFirstName()
                .inputLastName()
                .inputFatherName()
                .inputGender()
                .inputUniversityNumber()
                .inputDormitoryNumber()
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
