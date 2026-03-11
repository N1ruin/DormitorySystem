package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.dto.student.UpdateStudentDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class UpdateStudentFormHandler {
    private final FormHandler formHandler;
    private final StudentInputValidationService studentInputValidationService;
    private final PrintService printService;
    private final DormitoryService dormitoryService;
    private final StudentService studentService;

    private int studentNameNumberFromList;
    private String lastName;
    private int dormitoryNumberFromList;

    public UpdateStudentFormHandler(FormHandler formHandler, StudentInputValidationService studentInputValidationService, PrintService printService, DormitoryService dormitoryService, StudentService studentService) {
        this.formHandler = formHandler;
        this.studentInputValidationService = studentInputValidationService;
        this.printService = printService;
        this.dormitoryService = dormitoryService;
        this.studentService = studentService;
    }

    public UpdateStudentFormHandler handleNumber() {
       var studentNamesDto = studentService.getCurrentUniversityStudentNames();
        studentNameNumberFromList = formHandler.handleInputString(
                () -> printService.printStudentNames(studentNamesDto),
                Integer::parseInt,
                studentInputValidationService::validateNumber);
        return this;
    }

    public UpdateStudentFormHandler handleLastName() {
        lastName = formHandler.handleInputString(
                printService::printInputLastNameRequestMessage,
                Function.identity(),
                studentInputValidationService::validateLastName);
        return this;
    }

    public UpdateStudentFormHandler handleDormitory() {
        var currentDormitoryNumbersDto = dormitoryService.getCurrentUniversityDormitoryNumbers();
        dormitoryNumberFromList = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(currentDormitoryNumbersDto),
                Integer::parseInt,
                studentInputValidationService::validateNumber);
        return this;
    }

    public UpdateStudentDto createDto() {
        return new UpdateStudentDto(studentNameNumberFromList, dormitoryNumberFromList, lastName);
    }
}
