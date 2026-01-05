package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.dto.UserRegistrationDto;
import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.DormitoryInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryDormitoryRepository;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryUniversityRepository;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.UUID;
import java.util.function.Function;

@Component
public class RegistrationFormHandler {
    private final FormHandler formHandler;
    private final UserInputValidationService userInputValidationService;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UniversityInputValidationService universityValidationService;
    private final DormitoryInputValidationService dormitoryValidationService;
    private final DormitoryService dormitoryService;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender gender;
    private UUID universityId;
    private UUID dormitoryId;

    public RegistrationFormHandler(FormHandler formHandler, UserInputValidationService userDataValidationService,
                                   PrintService printService, UniversityService universityService,
                                   @Qualifier(InMemoryUniversityRepository.class) UniversityRepository universityRepository,
                                   @Qualifier(InMemoryDormitoryRepository.class) DormitoryRepository dormitoryRepository, UniversityInputValidationService universityValidationService, DormitoryInputValidationService dormitoryValidationService, DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.userInputValidationService = userDataValidationService;
        this.printService = printService;
        this.universityService = universityService;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.universityValidationService = universityValidationService;
        this.dormitoryValidationService = dormitoryValidationService;
        this.dormitoryService = dormitoryService;
    }

    public RegistrationFormHandler handleLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateLogin);
        return this;
    }

    public RegistrationFormHandler handlePassword() {
        password = formHandler.handleInputString(
                printService::printInputPasswordRequestMessage,
                Function.identity(),
                userInputValidationService::validatePassword);
        return this;
    }

    public RegistrationFormHandler handleFirstName() {
        firstName = formHandler.handleInputString(
                printService::printInputFirstNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler processLastName() {
        lastName = formHandler.handleInputString(
                printService::printInputLastNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler handleFatherName() {
        fatherName = formHandler.handleInputString(
                printService::printInputFatherNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler handleGender() {
        String genderInput = formHandler.handleInputString(
                printService::printInputGenderRequestMessage,
                Function.identity(),
                userInputValidationService::validateGender
        );

        gender = genderInput.matches("m") ? Gender.MALE : Gender.FEMALE;
        return this;
    }

    public RegistrationFormHandler handleUniversityNumber() {
        int universityNumber = formHandler.handleInputString(
                () -> printService.printSelectUniversityRequestMessage(universityService.getUniversitiesNames()),
                Integer::parseInt,
                universityValidationService::validateNumber
        );

        var university = universityRepository.findAll().get(universityNumber - 1);
        universityId = university.getId();
        return this;
    }

    public RegistrationFormHandler handleDormitoryNumber() {
        int dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoriesNameRequestMessage(dormitoryService.getDormitoriesNumber(universityId)),
                Integer::parseInt,
                dormitoryValidationService::validateNumber);

        var dormitory = dormitoryRepository.findByDormitoryNumberOrUniversityId(universityId, dormitoryNumber)
                .orElseThrow(() -> new EntityNotFoundException(dormitoryNumber, Dormitory.class));

        dormitoryId = dormitory.getId();
        return this;
    }

    public UserRegistrationDto createDto() {
        return new UserRegistrationDto(login, password, firstName, lastName, fatherName, gender, universityId, dormitoryId);
    }
}
