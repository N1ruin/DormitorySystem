package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.AuthentificationService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class MainMenu implements Menu {
    private final InputService inputService;
    private final PrintService printService;
    private final MenuFactory menuFactory;
    private final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private final AuthentificationService authentificationService;

    public MainMenu(InputService inputService, PrintService printService, MenuFactory menuFactory, AuthentificationService authentificationService) {
        this.inputService = inputService;
        this.printService = printService;
        this.menuFactory = menuFactory;
        this.authentificationService = authentificationService;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(MenuItemUtil.buildMenu(MainMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(MainMenuItem.class, Integer.parseInt(userInput));
            if (item.equals(MainMenuItem.LOG_OUT)) {
                authentificationService.logOut();
            }
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return redirectNextMenu(item);
        } catch (Exception e) {
            logger.info(e.getMessage());
            printService.printExceptionMessage(e);
            return this;
        }
    }

    private Menu redirectNextMenu(MainMenuItem item) {
        return switch (item) {
            case SYSTEM_ADMIN_MENU -> menuFactory.createStartMenu();
            case SELECT_CURRENT_UNIVERSITY_MENU -> menuFactory.createStartMenu();
            case SELECT_CURRENT_DORMITORY_MENU -> menuFactory.createStartMenu();
            case ROOMS -> menuFactory.createRoomMenu();
            case DORMITORIES -> menuFactory.createStartMenu();
            case STUDENTS -> menuFactory.createStartMenu();
            case UNIVERSITIES -> menuFactory.createStartMenu();
            case LOG_OUT -> menuFactory.createStartMenu();
        };
    }
}
