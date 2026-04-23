package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.menu.item.SelectSortDormitoriesOrderMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import java.util.Comparator;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortDormitoriesOrderMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(SelectSortDormitoriesOrderMenuItem.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final DormitoryService dormitoryService;
    private final MenuItemService menuItemService;

    public SelectSortDormitoriesOrderMenu(PrintService printService, InputService inputService, MenuFactory menuFactory,
                                          DormitoryService dormitoryService, MenuItemService menuItemService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.dormitoryService = dormitoryService;
        this.menuItemService = menuItemService;
    }

    @Override
    public void display() {
        printService.printMenu(menuItemService.buildMenu(SelectSortDormitoriesOrderMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(SelectSortDormitoriesOrderMenuItem.class, Integer.parseInt(userInput));
            String sortedRoomsInfo = dormitoryService.getSortedDormitoriesInfo(getRoomComparator(item));
            printService.printSortedDormitoriesInfo(sortedRoomsInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.getMenu(DormitoryMenu.class);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Comparator<Dormitory> getRoomComparator(SelectSortDormitoriesOrderMenuItem item) {
        return switch (item) {
            case SORT_BY_NUMBER -> Comparator.comparingInt(Dormitory::getNumber);
            case SORT_BY_NUMBER_DESC -> Comparator.comparingInt(Dormitory::getNumber).reversed();
            case SORT_BY_ROOM_CAPACITY -> Comparator.comparingInt(Dormitory::getRoomsCount);
            case SORT_BY_ROOM_CAPACITY_DESC -> Comparator.comparingInt(Dormitory::getRoomsCount).reversed();
            case SORT_BY_AVAILABLE_FOR_LIVING -> Comparator.comparing(Dormitory::isAvailableForLiving).reversed();
            case SORT_BY_AVAILABLE_FOR_LIVING_DESC -> Comparator.comparing(Dormitory::isAvailableForLiving);
        };
    }
}
