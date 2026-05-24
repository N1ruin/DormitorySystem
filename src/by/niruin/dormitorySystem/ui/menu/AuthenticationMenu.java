package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.dto.user.AuthentificationUserDto;
import by.niruin.dormitorySystem.domain.service.AuthenticationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.UserFormHandlerFactory;

import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_AUTHENTIFICATION_FAIL_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_AUTHENTIFICATION_SUCCESS_LOG;

public class AuthenticationMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationMenu.class);
    private final PrintService printService;
    private final MenuLocator menuLocator;
    private final UserFormHandlerFactory userFormHandlerFactory;
    private final AuthenticationService authentificationService;

    public AuthenticationMenu(PrintService printService, MenuLocator menuLocator,
                              UserFormHandlerFactory userFormHandlerFactory,
                              AuthenticationService authentificationService) {
        this.printService = printService;
        this.menuLocator = menuLocator;
        this.userFormHandlerFactory = userFormHandlerFactory;
        this.authentificationService = authentificationService;
    }

    @Override
    public void display() {
        printService.printAuthentificationRequest();
    }

    @Override
    public Menu handleInput() {
        return signIn();
    }

    private Menu signIn() {
        try {
            AuthentificationUserDto dto = handleAuthorizationForm();
            authentificationService.signIn(dto);
            printService.printWelcomeUserMessage(dto.login());
            logger.info(USER_AUTHENTIFICATION_SUCCESS_LOG.formatted(dto.login()));
            return menuLocator.getMenu(MainMenu.class);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_AUTHENTIFICATION_FAIL_LOG);
            logger.info(e.getMessage());
            return menuLocator.getMenu(StartMenu.class);
        }
    }

    private AuthentificationUserDto handleAuthorizationForm() {
        return userFormHandlerFactory.getAuthenticationFormHandler()
                .inputLogin()
                .inputPassword()
                .createDto();
    }
}
