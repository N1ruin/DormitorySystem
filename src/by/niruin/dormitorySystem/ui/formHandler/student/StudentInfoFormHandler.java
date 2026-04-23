package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.dto.student.StudentNumberFromListDto;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class StudentInfoFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final StudentService studentService;
    private final StudentInputValidationService studentInputValidationService;

    private int studentNumberFromList;

    public StudentInfoFormHandler(FormHandler formHandler, PrintService printService, StudentService studentService, StudentInputValidationService studentInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.studentService = studentService;
        this.studentInputValidationService = studentInputValidationService;
    }

    public StudentInfoFormHandler inputStudentNumber() {
        var studentNamesDto = studentService.getCurrentUniversityStudentNames();
        studentNumberFromList = formHandler.handleInputString(
                () -> printService.printStudentNames(studentNamesDto),
                Integer::parseInt,
                studentInputValidationService::validateNumber);
        return this;
    }

    public StudentNumberFromListDto createDto() {
        return new StudentNumberFromListDto(studentNumberFromList);
    }
}
