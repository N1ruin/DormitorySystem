package by.niruin.dormitorySystem.ui.formHandler.university;

import by.niruin.dormitorySystem.domain.model.dto.university.UpdateUniversityDto;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

public class UpdateUniversityFormHandler {
    private final FormHandler formHandler;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityInputValidationService universityInputValidationService;
    private int universityNumber;
    private byte studyDuration;

    public UpdateUniversityFormHandler(FormHandler formHandler, PrintService printService, UniversityService universityService,
                                       UniversityInputValidationService universityInputValidationService) {
        this.formHandler = formHandler;
        this.printService = printService;
        this.universityService = universityService;
        this.universityInputValidationService = universityInputValidationService;
    }

    public UpdateUniversityFormHandler handleNumber() {
        universityNumber = formHandler.handleInputString(
                () -> printService.printSelectUniversityRequestMessage(universityService.getUniversitiesNames()),
                Integer::parseInt,
                universityInputValidationService::validateNumber);
        return this;
    }

    public UpdateUniversityFormHandler handleAvailable() {
        studyDuration = formHandler.handleInputString(
                printService::printInputStudyDurationRequestMessage,
                Byte::parseByte,
                universityInputValidationService::validateNumber);
        return this;
    }

    public UpdateUniversityDto createDto() {
        return new UpdateUniversityDto(universityNumber, studyDuration);
    }
}
