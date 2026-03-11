package by.niruin.dormitorySystem.ui.formHandler.university;

import by.niruin.dormitorySystem.domain.model.dto.university.DeleteUniversityDto;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class DeleteUniversityFormHandler {
    private final PrintService printService;
    private final UniversityService universityService;
    private final FormHandler formHandler;
    private final UniversityInputValidationService universityInputValidationService;
    private int universityNumber;

    public DeleteUniversityFormHandler(PrintService printService, UniversityService universityService,
                                       FormHandler formHandler,
                                       UniversityInputValidationService universityInputValidationService) {
        this.printService = printService;
        this.universityService = universityService;
        this.formHandler = formHandler;
        this.universityInputValidationService = universityInputValidationService;
    }

    public DeleteUniversityFormHandler handleUniversityNumber() {
        universityNumber = formHandler.handleInputString(
                () -> printService.printSelectUniversityRequestMessage(universityService.getUniversitiesNames()),
                Integer::parseInt,
                universityInputValidationService::validateNumber
        );
        return this;
    }

    public DeleteUniversityDto createDto() {
        return new DeleteUniversityDto(universityNumber);
    }
}
