package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.student.*;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class StudentMenu implements Menu {
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final CreateStudentFormHandler createStudentFormHandler;
    private final DeleteStudentFormHandler deleteStudentFormHandler;
    private final UpdateStudentFormHandler updateStudentFormHandler;
    private final StudentInfoFormHandler studentInfoFormHandler;
    private final DistributeStudentToDormitoryFormHandler distributeStudentToDormitoryFormHandler;
    private final DistributeStudentToRoomFormHandler distributeStudentToRoomFormHandler;
    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentMenu.class);

    public StudentMenu(PrintService printService, InputService inputService, MenuFactory menuFactory, CreateStudentFormHandler createStudentFormHandler,
                       DeleteStudentFormHandler deleteStudentFormHandler, UpdateStudentFormHandler updateStudentFormHandler,
                       StudentInfoFormHandler studentInfoFormHandler, DistributeStudentToDormitoryFormHandler distributeStudentToDormitoryFormHandler,
                       DistributeStudentToRoomFormHandler distributeStudentToRoomFormHandler, StudentService studentService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.createStudentFormHandler = createStudentFormHandler;
        this.deleteStudentFormHandler = deleteStudentFormHandler;
        this.updateStudentFormHandler = updateStudentFormHandler;
        this.studentInfoFormHandler = studentInfoFormHandler;
        this.distributeStudentToDormitoryFormHandler = distributeStudentToDormitoryFormHandler;
        this.distributeStudentToRoomFormHandler = distributeStudentToRoomFormHandler;
        this.studentService = studentService;
    }

    @Override
    public void display() {
        printService.printMenu(MenuItemUtil.buildMenu(StudentMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(StudentMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return executeMenuItem(item);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu executeMenuItem(StudentMenuItem item) {
        Menu nextMenu = this;
        switch (item) {
            case CREATE_STUDENT -> createStudent();
            case DELETE_STUDENT -> deleteStudent();
            case UPDATE_STUDENT -> updateStudent();
            case GET_SORTED_STUDENTS -> nextMenu = menuFactory.createSelectSortStudentsOrderMenu();
            case GET_STUDENT_INFO -> getStudentInfo();
            case GET_STUDENTS_WITHOUT_DORMITORY -> getStudentsWithoutDormitory();
            case DISTRIBUTE_STUDENTS_TO_DORMITORIES -> distributeStudentsToDormitories();
            case DISTRIBUTE_STUDENTS_TO_ROOMS -> distributeStudentsToRooms();
            case GO_BACK -> nextMenu = menuFactory.createMainMenu();
        }
        return nextMenu;
    }

    private void createStudent() {
        var dto = createStudentFormHandler
                .handleFirstName()
                .handleLastName()
                .handleFatherName()
                .handleGender()
                .handleDateOfEntering()
                .createDto();
        try {
            studentService.createStudent(dto);
            printService.printStudentCreatedSuccessfulMessage();
            logger.info(STUDENT_CREATED_SUCCESSFUL_LOG.formatted(dto.firstName(), dto.lastName(), dto.fatherName()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_CREATED_FAIL_LOG.formatted(dto.firstName(), dto.lastName(), dto.fatherName()));
            logger.info(e.getMessage());
        }
    }

    private void deleteStudent() {
        var dto = deleteStudentFormHandler
                .handleNumber()
                .createDto();

        try {
            studentService.deleteStudent(dto);
            printService.printStudentDeletedSuccessfulMessage();
            logger.info(STUDENT_DELETED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_DELETED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void updateStudent() {
        var dto = updateStudentFormHandler
                .handleNumber()
                .handleLastName()
                .handleDormitory()
                .createDto();

        try {
            studentService.updateStudent(dto);
            printService.printStudentUpdatedSuccessfulMessage();
            logger.info(STUDENT_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getStudentInfo() {
        var dto = studentInfoFormHandler
                .handleStudentNumber()
                .createDto();

        try {
            String info = studentService.getStudentInfo(dto);
            printService.printStudentInfo(info);
            logger.info(STUDENT_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getStudentsWithoutDormitory() {
        try {
            String info = studentService.getStudentsNamesWithoutDormitory();
            printService.printStudentInfo(info);
            logger.info(STUDENT_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void distributeStudentsToDormitories() {
        var distributeStudentToDormitoryDto = distributeStudentToDormitoryFormHandler
                .handleStudentNumber()
                .handleDormitoryNumber()
                .createDto();

        try {
            studentService.distributeStudentToDormitory(distributeStudentToDormitoryDto);
            printService.printStudentDistributedToDormitorySuccessfulMessage();
            logger.info(STUDENT_DISTRIBUTED_TO_DORMITORY_SUCCESSFUL_LOG);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printService.printExceptionMessage(e);
            logger.info(STUDENT_DISTRIBUTED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void distributeStudentsToRooms() {
        var distributeStudentToRoomDto = distributeStudentToRoomFormHandler
                .handleStudentNumber()
                .handleRoomNumber()
                .createDto();

        try {
            studentService.distributeStudentToRoom(distributeStudentToRoomDto);
            printService.printStudentDistributedToRoomSuccessfulMessage();
            logger.info(STUDENT_DISTRIBUTED_TO_ROOM_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(STUDENT_DISTRIBUTED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }
}
