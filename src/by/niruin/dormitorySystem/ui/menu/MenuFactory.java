package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.AuthentificationService;
import by.niruin.dormitorySystem.domain.service.RegistrationService;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.*;
import by.niruin.dormitorySystem.ui.formHandler.room.*;
import by.niruin.dormitorySystem.ui.formHandler.user.AuthentificationFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.user.RegistrationFormHandler;

@Component
public class MenuFactory {
    private final InputService inputService;
    private final PrintService printService;
    private final AuthentificationService authentificationService;
    private final RegistrationService registrationService;
    private final RoomService roomService;
    private final StudentService studentService;
    private final RegistrationFormHandler formHandler;
    private final AuthentificationFormHandler authentificationMenuService;
    private final CreateRoomFormHandler createRoomFormHandleService;
    private final UpdateRoomFormHandler updateRoomFormHandleService;
    private final RoomInfoFormHandler getRoomInfoFormHandleService;
    private final DeleteRoomFormHandler deleteRoomFormHandleService;

    public MenuFactory(InputService inputService, PrintService printService,
                       AuthentificationService authentificationService, RegistrationService registrationService,
                       RoomService roomService, StudentService studentService,
                       RegistrationFormHandler formHandler, AuthentificationFormHandler authentificationMenuService,
                       CreateRoomFormHandler createRoomFormHandleService, UpdateRoomFormHandler updateRoomFormHandleService,
                       RoomInfoFormHandler getRoomInfoFormHandleService, DeleteRoomFormHandler deleteRoomFormHandleService) {
        this.inputService = inputService;
        this.printService = printService;
        this.authentificationService = authentificationService;
        this.registrationService = registrationService;
        this.roomService = roomService;
        this.studentService = studentService;
        this.formHandler = formHandler;
        this.authentificationMenuService = authentificationMenuService;
        this.createRoomFormHandleService = createRoomFormHandleService;
        this.updateRoomFormHandleService = updateRoomFormHandleService;
        this.getRoomInfoFormHandleService = getRoomInfoFormHandleService;
        this.deleteRoomFormHandleService = deleteRoomFormHandleService;
    }

    public Menu createStartMenu() {
        return new StartMenu(inputService, printService, this);
    }

    public Menu createRegistrationMenu() {
        return new RegistrationMenu(printService, this, registrationService, formHandler);
    }

    public Menu createAuthentificationMenu() {
        return new AuthentificationMenu(printService, this, authentificationMenuService, authentificationService);
    }

    public Menu createRoomMenu() {
        return new RoomMenu(inputService, printService, roomService, this, studentService, createRoomFormHandleService,
                updateRoomFormHandleService, getRoomInfoFormHandleService, deleteRoomFormHandleService);
    }

    public Menu createExitMenu() {
        return new ExitMenu();
    }

    public Menu createMainMenu() {
        return new MainMenu(inputService, printService, this, authentificationService);
    }

    public Menu createSelectOrderMenu() {
        return new SelectSortOrderMenu(printService, inputService, this, roomService);
    }
}
