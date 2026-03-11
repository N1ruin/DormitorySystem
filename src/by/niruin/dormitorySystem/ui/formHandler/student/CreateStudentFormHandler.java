package by.niruin.dormitorySystem.ui.formHandler.student;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.dto.student.CreateStudentDto;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class CreateStudentFormHandler {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final FormHandler formHandler;
    private final UserInputValidationService userInputValidationService;
    private final PrintService printService;
    private LocalDate enteringDate;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender gender;

    public CreateStudentFormHandler(FormHandler formHandler, UserInputValidationService userInputValidationService,
                                    PrintService printService) {
        this.formHandler = formHandler;
        this.userInputValidationService = userInputValidationService;
        this.printService = printService;
    }

    public CreateStudentFormHandler handleFirstName() {
        firstName = formHandler.handleInputString(
                printService::printInputFirstNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public CreateStudentFormHandler handleLastName() {
        lastName = formHandler.handleInputString(
                printService::printInputLastNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public CreateStudentFormHandler handleFatherName() {
        fatherName = formHandler.handleInputString(
                printService::printInputFatherNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public CreateStudentFormHandler handleGender() {
        String genderInput = formHandler.handleInputString(
                printService::printInputGenderRequestMessage,
                Function.identity(),
                userInputValidationService::validateGender);

        gender = genderInput.matches("male") ? Gender.MALE : Gender.FEMALE;
        return this;
    }

    public CreateStudentFormHandler handleEnteringDate() {
        String date = formHandler.handleInputString(
                printService::printEnteringDateInputMessage,
                Function.identity(),
                userInputValidationService::validateDate);

        enteringDate = LocalDate.parse(date, DATE_FORMATTER);
        return this;
    }

    public CreateStudentDto createDto() {
        return new CreateStudentDto(firstName, lastName, fatherName, gender, enteringDate);
    }

}
