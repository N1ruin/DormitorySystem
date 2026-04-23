package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.dto.student.DistributeStudentToDormitoryDto;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.StudentService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.StudentInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class DistributeStudentToDormitoryFormHandler {
    private final FormHandler formHandler;
    private final StudentInputValidationService studentInputValidationService;
    private final DormitoryInputValidationService dormitoryInputValidationService;
    private final PrintService printService;
    private final StudentService studentService;
    private final DormitoryService dormitoryService;

    private int studentNumberFromList;
    private int dormitoryNumber;

    public DistributeStudentToDormitoryFormHandler(FormHandler formHandler,
                                                   StudentInputValidationService studentInputValidationService,
                                                   DormitoryInputValidationService dormitoryInputValidationService,
                                                   PrintService printService, StudentService studentService,
                                                   DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.studentInputValidationService = studentInputValidationService;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
        this.printService = printService;
        this.studentService = studentService;
        this.dormitoryService = dormitoryService;
    }

    public DistributeStudentToDormitoryFormHandler inputStudentNumber() {
        var studentsNamesWithoutDormitory = studentService.getStudentsNamesWithoutDormitory();
        studentNumberFromList = formHandler.handleInputString(
                () -> printService.printStudentNames(studentsNamesWithoutDormitory),
                Integer::parseInt,
                input -> studentInputValidationService
                        .validateNumberInList(input, studentService.getStudentsWithoutDormitory()));
        return this;
    }

    public DistributeStudentToDormitoryFormHandler inputDormitoryNumber() {
        var dormitoriesNumbers = dormitoryService.getCurrentUniversityDormitoryNumbers();
        dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoryNumbers(dormitoriesNumbers),
                Integer::parseInt,
                dormitoryInputValidationService::validateNumberInList);
        return this;
    }

    public DistributeStudentToDormitoryDto createDto() {
        return new DistributeStudentToDormitoryDto(studentNumberFromList, dormitoryNumber);
    }
}
