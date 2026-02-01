package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import java.util.Comparator;

import static by.niruin.dormitorySystem.constant.LoggerMessage.ENTERED_INVALID_VALUE_LOG;
import static by.niruin.dormitorySystem.constant.LoggerMessage.SELECTED_ITEM_LOG;

public class SelectSortRoomsOrderMenu implements Menu {
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(SelectSortRoomsOrderMenu.class);

    public SelectSortRoomsOrderMenu(PrintService printService, InputService inputService, MenuFactory menuFactory, RoomService roomService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.roomService = roomService;
    }

    @Override
    public void display() {
        printService.printSelectSortingOrder();
        printService.printMenu(MenuItemUtil.buildMenu(SelectSortRoomsOrderMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(SelectSortRoomsOrderMenuItem.class, Integer.parseInt(userInput));
            String sortedRoomsInfo = roomService.getSortedRoomsInfo(getRoomComparator(item));
            printService.printSortedDormitoriesInfo(sortedRoomsInfo);
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return menuFactory.createRoomMenu();
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Comparator<Room> getRoomComparator(SelectSortRoomsOrderMenuItem item) {
        return switch (item) {
            case SORT_BY_NUMBER -> Comparator.comparingInt(Room::getNumber);
            case SORT_BY_NUMBER_DESC -> Comparator.comparingInt(Room::getNumber).reversed();
            case SORT_BY_FREE_QUANTITY -> Comparator.comparing(room -> roomService.getFreePlaces(room.getId()));
            case SORT_BY_FREE_QUANTITY_DESC ->
                    Comparator.comparing((Room room) -> roomService.getFreePlaces(room.getId())).reversed();
            case SORT_BY_GENDER_MALE_FIRST -> Comparator.comparing(Room::isMaleOnly).reversed();
            case SORT_BY_GENDER_FEMALE_FIRST -> Comparator.comparing(Room::isMaleOnly);
            case SORT_BY_AVAILABLE_FOR_LIVING -> Comparator.comparing(Room::isAvailableForLiving).reversed();
            case SORT_BY_AVAILABLE_FOR_LIVING_DESC -> Comparator.comparing(Room::isAvailableForLiving);
        };
    }
}
