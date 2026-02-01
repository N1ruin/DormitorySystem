package by.niruin.dormitorySystem.ui.formHandler.university;

import by.niruin.dormitorySystem.domain.model.dto.university.CreateUniversityDto;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.function.Function;

public class CreateUniversityFormHandler {
    private final FormHandler formHandler;
    private final UniversityInputValidationService validationService;
    private final PrintService printService;
    private String universityName;
    private byte studyDuration;

    public CreateUniversityFormHandler(FormHandler formHandler, UniversityInputValidationService validationService, PrintService printService) {
        this.formHandler = formHandler;
        this.validationService = validationService;
        this.printService = printService;
    }

    public CreateUniversityFormHandler handleUniversityName() {
        universityName = formHandler.handleInputString(
                printService::printInputUniversityNumberRequestMessage,
                Function.identity(),
                validationService::validateName);
        return this;
    }

    public CreateUniversityFormHandler handleStudyDuration() {
        studyDuration = formHandler.handleInputString(
                printService::printInputStudyDurationRequestMessage,
                Byte::parseByte,
                validationService::validateNumber);
        return this;
    }

    public CreateUniversityDto createDto() {
        return new CreateUniversityDto(universityName, studyDuration);
    }
}
