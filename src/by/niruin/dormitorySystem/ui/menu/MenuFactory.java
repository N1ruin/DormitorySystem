package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.*;
import by.niruin.dormitorySystem.domain.service.statistic.UniversityStatisticService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.*;
import by.niruin.dormitorySystem.ui.formHandler.factory.*;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

@Component
public class MenuFactory {
    private final InputService inputService;
    private final PrintService printService;
    private final RegistrationService registrationService;
    private final AuthenticationService authentificationService;
    private final DormitoryFormHandlerFactory dormitoryFormHandlerFactory;
    private final RoomFormHandlerFactory roomFormHandlerFactory;
    private final StudentFormHandlerFactory studentFormHandlerFactory;
    private final UniversityFormHandlerFactory universityFormHandlerFactory;
    private final UserFormHandlerFactory userFormHandlerFactory;
    private final RoomService roomService;
    private final StudentService studentService;
    private final UserService userService;
    private final DormitoryService dormitoryService;
    private final UniversityService universityService;
    private final UniversityStatisticService universityStatisticService;
    private final MenuItemService menuItemService;

    public MenuFactory(InputService inputService, PrintService printService, RegistrationService registrationService,
                       AuthenticationService authentificationService,
                       DormitoryFormHandlerFactory dormitoryFormHandlerFactory,
                       RoomFormHandlerFactory roomFormHandlerFactory, StudentFormHandlerFactory studentFormHandlerFactory,
                       UniversityFormHandlerFactory universityFormHandlerFactory,
                       UserFormHandlerFactory userFormHandlerFactory, RoomService roomService,
                       StudentService studentService, UserService userService, DormitoryService dormitoryService,
                       UniversityService universityService, UniversityStatisticService universityStatisticService,
                       MenuItemService menuItemService) {
        this.inputService = inputService;
        this.printService = printService;
        this.registrationService = registrationService;
        this.authentificationService = authentificationService;
        this.dormitoryFormHandlerFactory = dormitoryFormHandlerFactory;
        this.roomFormHandlerFactory = roomFormHandlerFactory;
        this.studentFormHandlerFactory = studentFormHandlerFactory;
        this.universityFormHandlerFactory = universityFormHandlerFactory;
        this.userFormHandlerFactory = userFormHandlerFactory;
        this.roomService = roomService;
        this.studentService = studentService;
        this.userService = userService;
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
        this.universityStatisticService = universityStatisticService;
        this.menuItemService = menuItemService;
    }

    public Menu createMenu(Class<? extends Menu> menuClass) {
        Menu menu;
        if (menuClass == RegistrationMenu.class) {
            menu = new RegistrationMenu(printService, this, userFormHandlerFactory, registrationService);
        } else if (menuClass == AuthenticationMenu.class) {
            menu = new AuthenticationMenu(printService, this, userFormHandlerFactory,
                    authentificationService);
        } else if (menuClass == RoomMenu.class) {
            menu = new RoomMenu(inputService, printService, roomService, this, studentService,
                    menuItemService, roomFormHandlerFactory);
        } else if (menuClass == ExitMenu.class) {
            menu = new ExitMenu(menuItemService);
        } else if (menuClass == MainMenu.class) {
            menu = new MainMenu(inputService, printService, this, userService, menuItemService,
                    authentificationService);
        } else if (menuClass == SelectSortRoomsOrderMenu.class) {
            menu = new SelectSortRoomsOrderMenu(printService, inputService, this, roomService,
                    menuItemService);
        } else if (menuClass == DormitoryMenu.class) {
            menu = new DormitoryMenu(printService, inputService, this, dormitoryService, menuItemService,
                    dormitoryFormHandlerFactory);
        } else if (menuClass == SelectSortDormitoriesOrderMenu.class) {
            menu = new SelectSortDormitoriesOrderMenu(printService, inputService, this, dormitoryService,
                    menuItemService);
        } else if (menuClass == UniversityMenu.class) {
            menu = new UniversityMenu(inputService, printService, universityService, dormitoryService, this,
                    menuItemService, universityFormHandlerFactory, dormitoryFormHandlerFactory,
                    universityStatisticService);
        } else if (menuClass == SelectSortUniversitiesOrderMenu.class) {
            menu = new SelectSortUniversitiesOrderMenu(printService, inputService, this, menuItemService,
                    universityService);
        } else if (menuClass == UserMenu.class) {
            menu = new UserMenu(printService, inputService, this, userService, menuItemService,
                    registrationService, userFormHandlerFactory);
        } else if (menuClass == SelectSortUsersOrderMenu.class) {
            menu = new SelectSortUsersOrderMenu(printService, inputService, this, userService,
                    menuItemService);
        } else if (menuClass == StudentMenu.class) {
            menu = new StudentMenu(printService, inputService, this, menuItemService,
                    studentFormHandlerFactory, studentService);
        } else if (menuClass == SelectSortStudentsOrderMenu.class) {
            menu = new SelectSortStudentsOrderMenu(printService, inputService, this, studentService,
                    menuItemService);
        } else {
            menu = new StartMenu(inputService, printService, menuItemService, this);
        }

        return menu;
    }
}
