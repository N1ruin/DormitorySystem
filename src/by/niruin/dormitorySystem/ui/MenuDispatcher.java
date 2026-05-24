package by.niruin.dormitorySystem.ui;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.ExitMenu;
import by.niruin.dormitorySystem.ui.menu.Menu;
import by.niruin.dormitorySystem.ui.menu.MenuLocator;
import by.niruin.dormitorySystem.ui.menu.StartMenu;

import static by.niruin.dormitorySystem.constant.LoggerMessage.STARTED_MENU_CREATED_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.USER_NAVIGATE_TO_MENU_LOG;

@Component
public class MenuDispatcher {
    private final MenuLocator menuLocator;
    private final PrintService printService;
    private final Logger logger = LoggerFactory.getLogger(MenuDispatcher.class);

    public MenuDispatcher(MenuLocator menuLocator, PrintService printService) {
        this.menuLocator = menuLocator;
        this.printService = printService;
    }

    public void dispatch() {
        menuLocator.registerMenus();

        printService.printWelcomeApplicationMessage();
        Menu currentMenu = menuLocator.getMenu(StartMenu.class);
        logger.info(STARTED_MENU_CREATED_LOG);
        while (!(currentMenu instanceof ExitMenu)) {
            currentMenu.display();
            currentMenu = currentMenu.handleInput();
            logger.info(USER_NAVIGATE_TO_MENU_LOG.formatted(currentMenu.getClass().getSimpleName()));
        }
        currentMenu.display();
    }
}
