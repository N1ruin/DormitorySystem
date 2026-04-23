package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.ui.formHandler.student.*;

@Component
public class StudentFormHandlerFactory {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UserInputValidationService userInputValidationService;
    private final StudentInputValidationService studentInputValidationService;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final RoomInputValidationService roomInputValidationService;
    private final StudentService studentService;
    private final DormitoryService dormitoryService;
    private final RoomService roomService;

    public StudentFormHandlerFactory(FormHandler formHandler, PrintService printService,
                                     UserInputValidationService userInputValidationService,
                                     StudentInputValidationService studentInputValidationService,
                                     DormitoryInputValidationService dormitoryInputValidationService,
                                     RoomInputValidationService roomInputValidationService,
                                     StudentService studentService, DormitoryService dormitoryService,
                                     RoomService roomService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.userInputValidationService = userInputValidationService;
        this.studentInputValidationService = studentInputValidationService;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.roomInputValidationService = roomInputValidationService;
        this.studentService = studentService;
        this.dormitoryService = dormitoryService;
        this.roomService = roomService;
    }

    public CreateStudentFormHandler getCreateStudentFormHandler() {
        return new CreateStudentFormHandler(formHandler, userInputValidationService, printService);
    }

    public DeleteStudentFormHandler getDeleteStudentFormHandler() {
        return new DeleteStudentFormHandler(formHandler, studentInputValidationService, printService, studentService);
    }

    public DistributeStudentToDormitoryFormHandler getDistributeStudentToDormitoryFormHandler() {
        return new DistributeStudentToDormitoryFormHandler(formHandler, studentInputValidationService,
                dormitoryInputValidationService, printService, studentService, dormitoryService);
    }

    public DistributeStudentToRoomFormHandler getDistributeStudentToRoomFormHandler() {
        return new DistributeStudentToRoomFormHandler(formHandler, studentInputValidationService,
                roomInputValidationService, printService, studentService, roomService);
    }

    public StudentInfoFormHandler getStudentInfoFormHandler() {
        return new StudentInfoFormHandler(formHandler, printService, studentService, studentInputValidationService);
    }

    public UpdateStudentFormHandler getUpdateStudentFormHandler() {
        return new UpdateStudentFormHandler(formHandler, studentInputValidationService, printService, dormitoryService,
                studentService);
    }
}
