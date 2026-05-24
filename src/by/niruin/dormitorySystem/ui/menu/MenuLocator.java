package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.*;
import by.niruin.dormitorySystem.domain.service.statistic.UniversityStatisticService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.*;
import by.niruin.dormitorySystem.ui.formHandler.factory.*;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class MenuLocator {
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
    private final Map<Class<? extends Menu>, Supplier<Menu>> menuRegistry = new HashMap<>();

    public MenuLocator(InputService inputService, PrintService printService, RegistrationService registrationService,
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

    public void registerMenus() {
        menuRegistry.put(RegistrationMenu.class, () -> new RegistrationMenu(printService, this,
                userFormHandlerFactory, registrationService));
        menuRegistry.put(AuthenticationMenu.class, () -> new AuthenticationMenu(printService, this,
                userFormHandlerFactory, authentificationService));
        menuRegistry.put(RoomMenu.class, () -> new RoomMenu(inputService, printService, roomService, this,
                studentService, menuItemService, roomFormHandlerFactory));
        menuRegistry.put(ExitMenu.class, () -> new ExitMenu(menuItemService));
        menuRegistry.put(MainMenu.class, () -> new MainMenu(inputService, printService, this, userService,
                menuItemService, authentificationService));
        menuRegistry.put(SelectSortRoomsOrderMenu.class, () -> new SelectSortRoomsOrderMenu(printService, inputService,
                this, roomService, menuItemService));
        menuRegistry.put(DormitoryMenu.class, () -> new DormitoryMenu(printService, inputService, this,
                dormitoryService, menuItemService, dormitoryFormHandlerFactory));
        menuRegistry.put(SelectSortDormitoriesOrderMenu.class, () -> new SelectSortDormitoriesOrderMenu(printService,
                inputService, this, dormitoryService, menuItemService));
        menuRegistry.put(UniversityMenu.class, () -> new UniversityMenu(inputService, printService, universityService,
                dormitoryService, this, menuItemService, universityFormHandlerFactory,
                dormitoryFormHandlerFactory, universityStatisticService));
        menuRegistry.put(SelectSortUniversitiesOrderMenu.class, () -> new SelectSortUniversitiesOrderMenu(printService,
                inputService, this, menuItemService, universityService));
        menuRegistry.put(UserMenu.class, () -> new UserMenu(printService, inputService, this, userService,
                menuItemService, registrationService, userFormHandlerFactory));
        menuRegistry.put(SelectSortUsersOrderMenu.class, () -> new SelectSortUsersOrderMenu(printService, inputService,
                this, userService, menuItemService));
        menuRegistry.put(StudentMenu.class, () -> new StudentMenu(printService, inputService, this,
                menuItemService, studentFormHandlerFactory, studentService));
        menuRegistry.put(SelectSortStudentsOrderMenu.class, () -> new SelectSortStudentsOrderMenu(printService,
                inputService, this, studentService, menuItemService));
    }

    public Menu getMenu(Class<? extends Menu> menuClass) {
        return menuRegistry.getOrDefault(menuClass,
                () -> new StartMenu(inputService, printService, menuItemService, this)).get();
    }
}
