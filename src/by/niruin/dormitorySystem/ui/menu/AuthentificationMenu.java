package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.AuthentificationService;
import by.niruin.dormitorySystem.domain.model.dto.AuthentificationUserDto;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.user.AuthentificationFormHandler;

import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_AUTHENTIFICATION_FAIL_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_AUTHENTIFICATION_SUCCESS_LOG;

public class AuthentificationMenu implements Menu {
    private final PrintService printService;
    private final MenuFactory menuFactory;
    private final AuthentificationFormHandler formService;
    private final AuthentificationService authentificationService;
    private final Logger logger = LoggerFactory.getLogger(AuthentificationMenu.class);

    public AuthentificationMenu(PrintService printService, MenuFactory menuFactory,
                                AuthentificationFormHandler menuService, AuthentificationService authentificationService) {
        this.printService = printService;
        this.menuFactory = menuFactory;
        this.formService = menuService;
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
            return menuFactory.createMainMenu();
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_AUTHENTIFICATION_FAIL_LOG);
            logger.info(e.getMessage());
            return menuFactory.createStartMenu();
        }
    }

    private AuthentificationUserDto handleAuthorizationForm() {
        return formService
                .handleLogin()
                .handlePassword()
                .createDto();
    }
}
