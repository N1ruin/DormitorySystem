package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.*;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.*;
import by.niruin.dormitorySystem.ui.formHandler.dormitory.*;
import by.niruin.dormitorySystem.ui.formHandler.room.*;
import by.niruin.dormitorySystem.ui.formHandler.student.*;
import by.niruin.dormitorySystem.ui.formHandler.university.*;
import by.niruin.dormitorySystem.ui.formHandler.user.*;

//мне страшно уже от количества зависимостей, надо чота делать, а что не придумал пока;D
@Component
public class MenuFactory {
    private final InputService inputService;
    private final PrintService printService;
    private final AuthenticationService authentificationService;
    private final RegistrationService registrationService;
    private final RoomService roomService;
    private final DormitoryService dormitoryService;
    private final StudentService studentService;
    private final UniversityService universityService;
    private final UserService userService;
    private final RegistrationFormHandler registrationFormHandler;
    private final AuthentificationFormHandler authentificationMenuService;
    private final CreateRoomFormHandler createRoomFormHandler;
    private final UpdateRoomFormHandler updateRoomFormHandler;
    private final RoomInfoFormHandler getRoomInfoFormHandler;
    private final DeleteRoomFormHandler deleteRoomFormHandler;
    private final CreateDormitoryFormHandler createDormitoryFormHandler;
    private final DeleteDormitoryFormHandler deleteDormitoryFormHandler;
    private final UpdateDormitoryFormHandler updateDormitoryFormHandler;
    private final SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler;
    private final DormitoryInfoFormHandler dormitoryInfoFormHandler;
    private final CreateUniversityFormHandler createUniversityFormHandler;
    private final DeleteUniversityFormHandler deleteUniversityFormHandler;
    private final UpdateUniversityFormHandler updateUniversityFormHandler;
    private final SelectCurrentUniversityFormHandler selectCurrentUniversityFormHandler;
    private final UniversityInfoFormHandler universityInfoFormHandler;
    private final DeleteUserFormHandler deleteUserFormHandler;
    private final UpdateUserFormHandler updateUserFormHandler;
    private final GetUserInfoFormHandler getUserInfoFormHandler;
    private final CreateStudentFormHandler createStudentFormHandler;
    private final DeleteStudentFormHandler deleteStudentFormHandler;
    private final UpdateStudentFormHandler updateStudentFormHandler;
    private final StudentInfoFormHandler studentInfoFormHandler;
    private final DistributeStudentToDormitoryFormHandler distributeStudentToDormitoryFormHandler;
    private final DistributeStudentToRoomFormHandler distributeStudentToRoomFormHandler;

    public MenuFactory(InputService inputService, PrintService printService,
                       AuthenticationService authentificationService, RegistrationService registrationService,
                       RoomService roomService, DormitoryService dormitoryService, StudentService studentService, UserService userService,
                       RegistrationFormHandler formHandler, AuthentificationFormHandler authentificationMenuService,
                       CreateRoomFormHandler createRoomFormHandleService, UpdateRoomFormHandler updateRoomFormHandleService,
                       RoomInfoFormHandler getRoomInfoFormHandleService, DeleteRoomFormHandler deleteRoomFormHandleService,
                       CreateDormitoryFormHandler createDormitoryFormHandler, DeleteDormitoryFormHandler deleteDormitoryFormHandler,
                       UpdateDormitoryFormHandler updateDormitoryFormHandler, SelectCurrentDormitoryFormHandler selectCurrentDormitoryFormHandler,
                       DormitoryInfoFormHandler dormitoryInfoFormHandler, CreateUniversityFormHandler createUniversityFormHandler,
                       DeleteUniversityFormHandler deleteUniversityFormHandler, UpdateUniversityFormHandler updateUniversityFormHandler,
                       SelectCurrentUniversityFormHandler selectCurrentUniversityFormHandler, UniversityInfoFormHandler universityInfoFormHandler,
                       UniversityService universityService, DeleteUserFormHandler deleteUserFormHandler, UpdateUserFormHandler updateUserFormHandler,
                       GetUserInfoFormHandler getUserInfoFormHandler, CreateStudentFormHandler createStudentFormHandler,
                       DeleteStudentFormHandler deleteStudentFormHandler, UpdateStudentFormHandler updateStudentFormHandler,
                       StudentInfoFormHandler studentInfoFormHandler, DistributeStudentToDormitoryFormHandler distributeStudentToDormitoryFormHandler, DistributeStudentToRoomFormHandler distributeStudentToRoomFormHandler) {
        this.inputService = inputService;
        this.printService = printService;
        this.authentificationService = authentificationService;
        this.registrationService = registrationService;
        this.roomService = roomService;
        this.dormitoryService = dormitoryService;
        this.studentService = studentService;
        this.userService = userService;
        this.registrationFormHandler = formHandler;
        this.authentificationMenuService = authentificationMenuService;
        this.createRoomFormHandler = createRoomFormHandleService;
        this.updateRoomFormHandler = updateRoomFormHandleService;
        this.getRoomInfoFormHandler = getRoomInfoFormHandleService;
        this.deleteRoomFormHandler = deleteRoomFormHandleService;
        this.createDormitoryFormHandler = createDormitoryFormHandler;
        this.deleteDormitoryFormHandler = deleteDormitoryFormHandler;
        this.updateDormitoryFormHandler = updateDormitoryFormHandler;
        this.selectCurrentDormitoryFormHandler = selectCurrentDormitoryFormHandler;
        this.dormitoryInfoFormHandler = dormitoryInfoFormHandler;
        this.createUniversityFormHandler = createUniversityFormHandler;
        this.deleteUniversityFormHandler = deleteUniversityFormHandler;
        this.updateUniversityFormHandler = updateUniversityFormHandler;
        this.selectCurrentUniversityFormHandler = selectCurrentUniversityFormHandler;
        this.universityInfoFormHandler = universityInfoFormHandler;
        this.universityService = universityService;
        this.deleteUserFormHandler = deleteUserFormHandler;
        this.updateUserFormHandler = updateUserFormHandler;
        this.getUserInfoFormHandler = getUserInfoFormHandler;
        this.createStudentFormHandler = createStudentFormHandler;
        this.deleteStudentFormHandler = deleteStudentFormHandler;
        this.updateStudentFormHandler = updateStudentFormHandler;
        this.studentInfoFormHandler = studentInfoFormHandler;
        this.distributeStudentToDormitoryFormHandler = distributeStudentToDormitoryFormHandler;
        this.distributeStudentToRoomFormHandler = distributeStudentToRoomFormHandler;
    }

    public Menu createStartMenu() {
        return new StartMenu(inputService, printService, this);
    }

    public Menu createRegistrationMenu() {
        return new RegistrationMenu(printService, this, registrationService, registrationFormHandler);
    }

    public Menu createAuthentificationMenu() {
        return new AuthentificationMenu(printService, this, authentificationMenuService, authentificationService);
    }

    public Menu createRoomMenu() {
        return new RoomMenu(inputService, printService, roomService, this, studentService, createRoomFormHandler,
                updateRoomFormHandler, getRoomInfoFormHandler, deleteRoomFormHandler);
    }

    public Menu createExitMenu() {
        return new ExitMenu();
    }

    public Menu createMainMenu() {
        return new MainMenu(inputService, printService, this, userService, authentificationService);
    }

    public Menu createSelectSortRoomsOrderMenu() {
        return new SelectSortRoomsOrderMenu(printService, inputService, this, roomService);
    }

    public Menu createDormitoryMenu() {
        return new DormitoryMenu(printService, inputService, this, dormitoryService, selectCurrentDormitoryFormHandler,
                createDormitoryFormHandler, deleteDormitoryFormHandler, updateDormitoryFormHandler, dormitoryInfoFormHandler);
    }

    public Menu createSelectSortDormitoriesOrderMenu() {
        return new SelectSortDormitoriesOrderMenu(printService, inputService, this, dormitoryService);
    }

    public Menu createUniversityMenu() {
        return new UniversityMenu(inputService, printService, universityService, dormitoryService, this,
                selectCurrentUniversityFormHandler, selectCurrentDormitoryFormHandler, createUniversityFormHandler,
                deleteUniversityFormHandler, updateUniversityFormHandler, universityInfoFormHandler);
    }

    public Menu createSelectSortUniversitiesOrderMenu() {
        return new SelectSortUniversitiesOrderMenu(printService, inputService, this, universityService);
    }

    public Menu createUserMenu() {
        return new UserMenu(printService, inputService, this, userService, registrationService,
                registrationFormHandler, deleteUserFormHandler, updateUserFormHandler, getUserInfoFormHandler);
    }

    public Menu createSelectSortUsersOrderMenu() {
        return new SelectSortUsersOrderMenu(printService, inputService, this, userService);
    }

    public Menu createStudentMenu() {
        return new StudentMenu(printService, inputService, this, createStudentFormHandler, deleteStudentFormHandler,
                updateStudentFormHandler, studentInfoFormHandler, distributeStudentToDormitoryFormHandler, distributeStudentToRoomFormHandler,
                studentService);
    }

    public Menu createSelectSortStudentsOrderMenu() {
        return new SelectSortStudentsOrderMenu(printService, inputService, this, studentService);
    }
}
