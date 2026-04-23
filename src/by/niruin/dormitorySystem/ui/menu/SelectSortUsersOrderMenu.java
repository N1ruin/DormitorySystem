package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.service.UserService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.item.SelectSortUsersOrderMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import java.util.Comparator;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortUsersOrderMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(SelectSortUsersOrderMenu.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final UserService userService;
    private final MenuItemService menuItemService;

    public SelectSortUsersOrderMenu(PrintService printService, InputService inputService, MenuFactory menuFactory,
                                    UserService userService, MenuItemService menuItemService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.userService = userService;
        this.menuItemService = menuItemService;
    }

    @Override
    public void display() {
        printService.printMenu(menuItemService.buildMenu(SelectSortUsersOrderMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(SelectSortUsersOrderMenuItem.class, Integer.parseInt(userInput));
            String sortedUniversitiesInfo = userService.getUsersInfo(getUserComparator(item));
            printService.printSortedUniversitiesInfo(sortedUniversitiesInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.getMenu(UserMenu.class);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return menuFactory.getMenu(UserMenu.class);
        }
    }

    private Comparator<User> getUserComparator(SelectSortUsersOrderMenuItem item) {
        return switch (item) {
            case SORT_BY_FULL_NAME -> Comparator.comparing(user -> user.getFullName().getShortName());
            case SORT_BY_FULL_NAME_DESC ->
                    Comparator.comparing((User user) -> user.getFullName().getShortName()).reversed();
            case SORT_BY_LOGIN -> Comparator.comparing(User::getLogin);
            case SORT_BY_LOGIN_DESC -> Comparator.comparing(User::getLogin).reversed();
            case SORT_BY_GENDER_MALE_FIRST -> Comparator.comparing(User::getGender);
            case SORT_BY_GENDER_MALE_LAST -> Comparator.comparing(User::getGender).reversed();
        };
    }
}
