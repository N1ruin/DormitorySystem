package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class StartMenu implements Menu {
    private final Logger logger = LoggerFactory.getLogger(StartMenu.class);
    private final InputService inputService;
    private final PrintService printService;
    private final MenuFactory menuFactory;

    public StartMenu(InputService inputService, PrintService printService, MenuFactory menuFactory) {
        this.inputService = inputService;
        this.printService = printService;
        this.menuFactory = menuFactory;
    }

    @Override
    public void display() {
        printService.printMenu(MenuItemUtil.buildMenu(StartMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(StartMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return redirectNextMenu(item);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu redirectNextMenu(StartMenuItem item) {
        return switch (item) {
            case AUTH_MENU -> menuFactory.createAuthentificationMenu();
            case REGISTRATION_MENU -> menuFactory.createRegistrationMenu();
            case EXIT -> menuFactory.createExitMenu();
        };
    }
}
