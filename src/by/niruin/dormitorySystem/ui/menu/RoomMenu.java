package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.RoomFormHandlerFactory;
import by.niruin.dormitorySystem.ui.menu.item.RoomMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class RoomMenu implements Menu {
    private final InputService inputService;
    private final PrintService printService;
    private final RoomService roomService;
    private final StudentService studentService;
    private final MenuFactory menuFactory;
    private final MenuItemService menuItemService;
    private final RoomFormHandlerFactory roomFormHandlerFactory;
    private static final Logger logger = LoggerFactory.getLogger(RoomMenu.class);

    public RoomMenu(InputService inputService, PrintService printService, RoomService roomService,
                    MenuFactory menuFactory, StudentService studentService, MenuItemService menuItemService,
                    RoomFormHandlerFactory roomFormHandlerFactory) {
        this.inputService = inputService;
        this.printService = printService;
        this.roomService = roomService;
        this.menuFactory = menuFactory;
        this.studentService = studentService;
        this.menuItemService = menuItemService;
        this.roomFormHandlerFactory = roomFormHandlerFactory;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(menuItemService.buildMenu(RoomMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(RoomMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return executeMenuItem(item);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu executeMenuItem(RoomMenuItem menuItem) {
        Menu nextMenu = this;
        switch (menuItem) {
            case CREATE_ROOM -> createRoom();
            case DELETE_ROOM -> deleteRoom();
            case UPDATE_ROOM -> updateRoom();
            case GET_SORTED_ROOMS -> nextMenu = menuFactory.createMenu(SelectSortRoomsOrderMenu.class);
            case GET_ROOM_INFO -> getRoomInfo();
            case GET_INHABILITIES_STUDENTS -> getInhabitedStudents();
            case GO_BACK -> nextMenu = menuFactory.createMenu(MainMenu.class);
        }
        return nextMenu;
    }

    private void createRoom() {
        var dto = roomFormHandlerFactory.getCreateRoomFormHandler()
                .handleRoomNumber()
                .handleRoomCapacity()
                .handleAvailable()
                .handleGender()
                .createRoomDto();

        try {
            roomService.createRoom(dto);
            printService.printRoomCreatedSuccessfulMessage();
            logger.info(ROOM_CREATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ROOM_CREATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void deleteRoom() {
        var dto = roomFormHandlerFactory.getDeleteRoomFormHandler()
                .handleRoomNumber()
                .createDto();
        try {
            roomService.deleteRoom(dto);
            printService.printRoomDeletedSuccessfulMessage();
            logger.info(ROOM_DELETED_SUCCESSFUL_LOG.formatted(dto.numberFromList()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ROOM_DELETED_FAIL_LOG.formatted(dto.numberFromList()));
            logger.info(e.getMessage());
        }
    }

    private void updateRoom() {
        var dto = roomFormHandlerFactory.getUpdateRoomFormHandler()
                .handleNumber()
                .handleCapacity()
                .handleAvailable()
                .handleGender()
                .createDto();
        try {
            roomService.updateRoom(dto);
            printService.printRoomUpdatedSuccessfulMessage();
            logger.info(ROOM_UPDATED_SUCCESSFUL_LOG.formatted(dto.number()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ROOM_UPDATED_FAIL_LOG.formatted(dto.number()));
            logger.info(e.getMessage());
        }
    }

    private void getRoomInfo() {
        var dto = roomFormHandlerFactory.getRoomInfoFormHandler()
                .handleRoomNumber()
                .createDto();
        try {
            String roomInfo = roomService.getRoomInfo(dto);
            printService.printRoomInfo(roomInfo);
            logger.info(ROOM_INFO_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ROOM_INFO_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getInhabitedStudents() {
        var studentNames = studentService.getStudentNamesWithoutRoom();
        try {
            printService.printStudentsWithoutRoom(studentNames);
            logger.info(DORMITORY_INHABITED_STUDENTS_INFO_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(DORMITORY_INHABITED_STUDENTS_INFO_RECEIVED_FAIL_LOG);
        }
    }
}
