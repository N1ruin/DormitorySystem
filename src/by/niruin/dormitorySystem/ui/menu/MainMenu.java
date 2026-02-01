package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.dto.user.UserLoginDto;
import by.niruin.dormitorySystem.domain.service.AuthenticationService;
import by.niruin.dormitorySystem.domain.service.UserService;
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
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private final AuthenticationService authentificationService;

    public MainMenu(InputService inputService, PrintService printService, MenuFactory menuFactory, UserService userService, AuthenticationService authentificationService) {
        this.inputService = inputService;
        this.printService = printService;
        this.menuFactory = menuFactory;
        this.userService = userService;
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
            return executeMenuItem(item);
        } catch (Exception e) {
            logger.info(e.getMessage());
            printService.printExceptionMessage(e);
            return this;
        }
    }

    private Menu executeMenuItem(MainMenuItem item) {
        return switch (item) {
            case ROOMS -> menuFactory.createRoomMenu();
            case DORMITORIES -> menuFactory.createDormitoryMenu();
            case STUDENTS -> menuFactory.createStudentMenu();
            case UNIVERSITIES -> menuFactory.createUniversityMenu();
            case USERS -> menuFactory.createUserMenu();
            case SHOW_ACCOUNT_INFO -> showAccountInfo();
            case LOG_OUT -> menuFactory.createStartMenu();
        };
    }

    private Menu showAccountInfo() {
        var currentUserLogin = ApplicationContextHolder.getContext().getActiveUser().getLogin();
        var dto = new UserLoginDto(currentUserLogin);

        var userInfo = userService.getUserInfo(dto);

        printService.printUserInfo(userInfo);

        return this;
    }
}
