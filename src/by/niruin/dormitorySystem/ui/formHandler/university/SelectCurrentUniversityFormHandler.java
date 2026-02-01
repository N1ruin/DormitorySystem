package by.niruin.dormitorySystem.ui.formHandler.university;

import by.niruin.dormitorySystem.domain.model.dto.university.SelectedCurrentUniversityNumberFromListDto;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class SelectCurrentUniversityFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityInputValidationService dormitoryInputValidationService;
    private int universityNumber;

    public SelectCurrentUniversityFormHandler(FormHandler formHandler, PrintService printService, UniversityService universityService, UniversityInputValidationService dormitoryInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.universityService = universityService;
        this.dormitoryInputValidationService = dormitoryInputValidationService;
    }

    public SelectCurrentUniversityFormHandler handleUniversityName() {
        universityNumber = formHandler.handleInputString(
                () -> printService.printUniversityNumbers(universityService.getUniversitiesNames()),
                Integer::parseInt,
                dormitoryInputValidationService::validateNumber);
        return this;
    }

    public SelectedCurrentUniversityNumberFromListDto createDto() {
        return new SelectedCurrentUniversityNumberFromListDto(universityNumber);
    }
}
