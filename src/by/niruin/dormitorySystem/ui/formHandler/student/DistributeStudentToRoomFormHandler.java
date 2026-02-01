package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.Room;
import by.niruin.dormitorySystem.domain.model.Student;
import by.niruin.dormitorySystem.domain.model.dto.student.DistributeStudentToRoomDto;
import by.niruin.dormitorySystem.domain.service.RoomService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;
import by.niruin.dormitorySystem.util.ApplicationContextUtil;

import java.util.Comparator;
import java.util.List;

@Component
public class DistributeStudentToRoomFormHandler {
    private final FormHandler formHandler;
    private final StudentInputValidationService studentInputValidationService;
    private final RoomInputValidationService roomInputValidationService;
    private final PrintService printService;
    private final StudentService studentService;
    private final RoomService roomService;

    private int studentNumberFromList;
    private int roomNumber;

    public DistributeStudentToRoomFormHandler(FormHandler formHandler, StudentInputValidationService studentInputValidationService,
                                              RoomInputValidationService roomInputValidationService, PrintService printService,
                                              StudentService studentService, RoomService roomService) {
        this.formHandler = formHandler;
        this.studentInputValidationService = studentInputValidationService;
        this.roomInputValidationService = roomInputValidationService;
        this.printService = printService;
        this.studentService = studentService;
        this.roomService = roomService;
    }

    public DistributeStudentToRoomFormHandler handleStudentNumber() {
        var studentsNamesWithoutRoom = studentService.getStudentNamesWithoutRoom();
        List<Student> studentsWithoutRoom = studentService.getStudentsWithoutRoom();

        studentNumberFromList = formHandler.handleInputString(
                () -> printService.printStudentNames(studentsNamesWithoutRoom),
                Integer::parseInt,
                input -> studentInputValidationService.validateNumberInList(input, studentsWithoutRoom));

        return this;
    }

    public DistributeStudentToRoomFormHandler handleRoomNumber() {
        var studentName = getStudentNameWithoutRoomByListNumber(studentNumberFromList);
        var student = studentService.getStudentByName(studentName);

        var freeRoomsDto = roomService.getFreeRoomsNumbers(student.getGender());
        List<Room> freeRooms = roomService.getFreeRooms(student.getGender(),
                ApplicationContextUtil.getCurrentDormitoryId());

        List<Integer> roomNumbers = freeRooms.stream()
                .map(Room::getNumber)
                .sorted()
                .toList();

        roomNumber = formHandler.handleInputString(
                () -> printService.printRoomNumbers(freeRoomsDto),
                Integer::parseInt,
                input -> roomInputValidationService.validateNumberInList(input, roomNumbers));

        return this;
    }

    private String getStudentNameWithoutRoomByListNumber(int numberFromList) {
        List<Student> studentsWithoutRoom = studentService.getStudentsWithoutRoom();

        if (numberFromList <= 0 || numberFromList > studentsWithoutRoom.size()) {
            throw new IllegalArgumentException("Invalid student number");
        }

        return studentsWithoutRoom.stream()
                .sorted(Comparator.comparing(s -> s.getFullName().getFullNameString()))
                .skip(numberFromList - 1)
                .findFirst()
                .map(student -> student.getFullName().getFullNameString())
                .orElseThrow();
    }

    public DistributeStudentToRoomDto createDto() {
        return new DistributeStudentToRoomDto(studentNumberFromList, roomNumber);
    }
}
