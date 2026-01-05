package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortOrderMenu implements Menu {
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(SelectSortOrderMenu.class);

    public SelectSortOrderMenu(PrintService printService, InputService inputService, MenuFactory menuFactory, RoomService roomService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.roomService = roomService;
    }

    @Override
    public void display() {
        printService.printSelectSortingOrder();
        printService.printMenu(MenuItemUtil.buildMenu(SelectOrderRoomMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(SelectOrderRoomMenuItem.class, Integer.parseInt(userInput));
            String sortedRoomsInfo = roomService.getSortedRoomsInfo(item);
            printService.printSortedRoomsInfo(sortedRoomsInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.createRoomMenu();
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }
}
