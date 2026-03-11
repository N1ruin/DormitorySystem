package by.niruin.dormitorySystem.ui.formHandler.university;

import by.niruin.dormitorySystem.domain.model.dto.university.UniversityNumberDto;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class UniversityInfoFormHandler {
    private final FormHandler formHandleService;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityInputValidationService universityInputValidationService;
    private int universityNumber;

    public UniversityInfoFormHandler(FormHandler formHandler, PrintService printService,
                                     UniversityService universityService,
                                     UniversityInputValidationService universityInputValidationService) {
        this.formHandleService = formHandler;
        this.printService = printService;
        this.universityService = universityService;
        this.universityInputValidationService = universityInputValidationService;
    }

    public UniversityInfoFormHandler handleUniversityNumber() {
        universityNumber = formHandleService.handleInputString(
                () -> printService.printUniversityNumbers(universityService.getUniversitiesNames()),
                Integer::parseInt,
                universityInputValidationService::validateNumber);
        return this;
    }

    public UniversityNumberDto createDto() {
        return new UniversityNumberDto(universityNumber);
    }
}
