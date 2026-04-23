package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.factory.StudentFormHandlerFactory;
import by.niruin.dormitorySystem.ui.menu.item.StudentMenuItem;
import by.niruin.dormitorySystem.ui.menu.service.MenuItemService;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class StudentMenu implements Menu {
    private static final Logger logger = LoggerFactory.getLogger(StudentMenu.class);
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final MenuItemService menuItemService;
    private final StudentFormHandlerFactory studentFormHandlerFactory;
    private final StudentService studentService;

    public StudentMenu(PrintService printService, InputService inputService, MenuFactory menuFactory,
                       MenuItemService menuItemService, StudentFormHandlerFactory studentFormHandlerFactory,
                       StudentService studentService) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.menuItemService = menuItemService;
        this.studentFormHandlerFactory = studentFormHandlerFactory;
        this.studentService = studentService;
    }

    @Override
    public void display() {
        printService.printMenu(menuItemService.buildMenu(StudentMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = menuItemService.getItem(StudentMenuItem.class, Integer.parseInt(userInput));
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
            case GET_SORTED_STUDENTS -> nextMenu = menuFactory.getMenu(SelectSortStudentsOrderMenu.class);
            case GET_STUDENT_INFO -> getStudentInfo();
            case GET_STUDENTS_WITHOUT_DORMITORY -> getStudentsWithoutDormitory();
            case DISTRIBUTE_STUDENTS_TO_DORMITORIES -> distributeStudentsToDormitories();
            case DISTRIBUTE_STUDENTS_TO_ROOMS -> distributeStudentsToRooms();
            case GO_BACK -> nextMenu = menuFactory.getMenu(MainMenu.class);
        }
        return nextMenu;
    }

    private void createStudent() {
        var dto = studentFormHandlerFactory.getCreateStudentFormHandler()
                .inputFirstName()
                .inputLastName()
                .inputFatherName()
                .inputGender()
                .inputEnteringDate()
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
        var dto = studentFormHandlerFactory.getDeleteStudentFormHandler()
                .inputNumber()
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
        var dto = studentFormHandlerFactory.getUpdateStudentFormHandler()
                .inputNumber()
                .inputLastName()
                .inputDormitory()
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
        var dto = studentFormHandlerFactory.getStudentInfoFormHandler()
                .inputStudentNumber()
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
        var distributeStudentToDormitoryDto = studentFormHandlerFactory.getDistributeStudentToDormitoryFormHandler()
                .inputStudentNumber()
                .inputDormitoryNumber()
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
        var distributeStudentToRoomDto = studentFormHandlerFactory.getDistributeStudentToRoomFormHandler()
                .inputStudentNumber()
                .inputRoomNumber()
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
