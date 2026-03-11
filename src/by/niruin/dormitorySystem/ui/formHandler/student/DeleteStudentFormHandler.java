package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.dto.student.DeleteStudentDto;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class DeleteStudentFormHandler {
    private final FormHandler formHandler;
    private final StudentInputValidationService studentInputValidationService;
    private final PrintService printService;
    private final StudentService studentService;
    private int numberFromList;

    public DeleteStudentFormHandler(FormHandler formHandler, StudentInputValidationService studentInputValidationService,
                                    PrintService printService, StudentService studentService) {
        this.formHandler = formHandler;
        this.studentInputValidationService = studentInputValidationService;
        this.printService = printService;
        this.studentService = studentService;
    }

    public DeleteStudentFormHandler handleNumber() {
        var studentNamesDto = studentService.getCurrentUniversityStudentNames();
        numberFromList = formHandler.handleInputString(
                () -> printService.printStudentNames(studentNamesDto),
                Integer::parseInt,
                studentInputValidationService::validateNumber);
        return this;
    }

    public DeleteStudentDto createDto() {
        return new DeleteStudentDto(numberFromList);
    }
}
