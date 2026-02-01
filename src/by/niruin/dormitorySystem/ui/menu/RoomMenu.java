package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.StudentService;

import by.niruin.dormitorySystem.infrastructure.service.*;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.room.CreateRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.DeleteRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.RoomInfoFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.UpdateRoomFormHandler;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class RoomMenu implements Menu {
    private final InputService inputService;
    private final PrintService printService;
    private final RoomService roomService;
    private final StudentService studentService;
    private final MenuFactory menuFactory;
    private final CreateRoomFormHandler createRoomFormHandler;
    private final DeleteRoomFormHandler deleteRoomFormHandler;
    private final UpdateRoomFormHandler updateRoomFormHandler;
    private final RoomInfoFormHandler getRoomInfoFormHandleService;
    private final Logger logger = LoggerFactory.getLogger(RoomMenu.class);

    public RoomMenu(InputService inputService, PrintService printService, RoomService roomService,
                    MenuFactory menuFactory, StudentService studentService,
                    CreateRoomFormHandler createRoomFormHandler, UpdateRoomFormHandler updateRoomFormHandler,
                    RoomInfoFormHandler getRoomInfoFormHandler, DeleteRoomFormHandler deleteRoomFormHandler) {
        this.inputService = inputService;
        this.printService = printService;
        this.roomService = roomService;
        this.menuFactory = menuFactory;
        this.studentService = studentService;
        this.createRoomFormHandler = createRoomFormHandler;
        this.updateRoomFormHandler = updateRoomFormHandler;
        this.getRoomInfoFormHandleService = getRoomInfoFormHandler;
        this.deleteRoomFormHandler = deleteRoomFormHandler;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(MenuItemUtil.buildMenu(RoomMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(RoomMenuItem.class, Integer.parseInt(userInput));
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
            case GET_SORTED_ROOMS -> nextMenu = menuFactory.createSelectSortRoomsOrderMenu();
            case GET_ROOM_INFO -> getRoomInfo();
            case GET_INHABILITIES_STUDENTS -> getInhabitedStudents();
            case GO_BACK -> nextMenu = menuFactory.createMainMenu();
        }
        return nextMenu;
    }

    private void createRoom() {
        var dto = createRoomFormHandler.handleRoomNumber()
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
        var dto = deleteRoomFormHandler
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
        var dto = updateRoomFormHandler
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
        var dto = getRoomInfoFormHandleService
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
