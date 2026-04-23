package by.niruin.dormitorySystem.ui.formHandler.user;

import by.niruin.dormitorySystem.domain.model.dto.user.UserRegistrationDto;
import by.niruin.dormitorySystem.domain.model.Dormitory;
import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.repository.DormitoryRepository;
import by.niruin.dormitorySystem.domain.repository.UniversityRepository;
import by.niruin.dormitorySystem.domain.service.DormitoryService;
import by.niruin.dormitorySystem.domain.service.UniversityService;
import by.niruin.dormitorySystem.domain.service.validation.RoomInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.UniversityInputValidationService;
import by.niruin.dormitorySystem.domain.service.validation.UserInputValidationService;
import by.niruin.dormitorySystem.exception.EntityNotFoundException;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.ui.formHandler.FormHandler;

import java.util.UUID;
import java.util.function.Function;

public class RegistrationFormHandler {
    private final FormHandler formHandler;
    private final UserInputValidationService userInputValidationService;
    private final PrintService printService;
    private final UniversityService universityService;
    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final UniversityInputValidationService universityInputValidationService;
    private final RoomInputValidationService roomInputValidationService;
    private final DormitoryService dormitoryService;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender gender;
    private UUID universityId;
    private UUID dormitoryId;

    public RegistrationFormHandler(FormHandler formHandler, UserInputValidationService userInputValidationService,
                                   PrintService printService, UniversityService universityService,
                                   UniversityRepository universityRepository, DormitoryRepository dormitoryRepository,
                                   UniversityInputValidationService universityInputValidationService,
                                   RoomInputValidationService roomInputValidationService,
                                   DormitoryService dormitoryService) {
        this.formHandler = formHandler;
        this.userInputValidationService = userInputValidationService;
        this.printService = printService;
        this.universityService = universityService;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.universityInputValidationService = universityInputValidationService;
        this.roomInputValidationService = roomInputValidationService;
        this.dormitoryService = dormitoryService;
    }

    public RegistrationFormHandler inputLogin() {
        login = formHandler.handleInputString(
                printService::printInputLoginRegistrationRequestMessage,
                Function.identity(),
                userInputValidationService::validateLogin);
        return this;
    }

    public RegistrationFormHandler inputPassword() {
        password = formHandler.handleInputString(
                printService::printInputPasswordRequestMessage,
                Function.identity(),
                userInputValidationService::validatePassword);
        return this;
    }

    public RegistrationFormHandler inputFirstName() {
        firstName = formHandler.handleInputString(
                printService::printInputFirstNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler inputLastName() {
        lastName = formHandler.handleInputString(
                printService::printInputLastNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler inputFatherName() {
        fatherName = formHandler.handleInputString(
                printService::printInputFatherNameRequestMessage,
                Function.identity(),
                userInputValidationService::validateName);
        return this;
    }

    public RegistrationFormHandler inputGender() {
        String genderInput = formHandler.handleInputString(
                printService::printInputGenderRequestMessage,
                Function.identity(),
                userInputValidationService::validateGender
        );

        gender = genderInput.equalsIgnoreCase("male") ? Gender.MALE : Gender.FEMALE;
        return this;
    }

    public RegistrationFormHandler inputUniversityNumber() {
        int universityNumber = formHandler.handleInputString(
                () -> printService.printSelectUniversityRequestMessage(universityService.getUniversitiesNames()),
                Integer::parseInt,
                universityInputValidationService::validateNumber
        );

        var university = universityRepository.findAll().get(universityNumber - 1);
        universityId = university.getId();
        return this;
    }

    public RegistrationFormHandler inputDormitoryNumber() {
        int dormitoryNumber = formHandler.handleInputString(
                () -> printService.printDormitoriesNumbersRequestMessage(dormitoryService.getDormitoryNumbers(universityId)),
                Integer::parseInt,
                roomInputValidationService::validateNumber);

        var dormitory = dormitoryRepository.findByDormitoryNumberOrUniversityId(universityId, dormitoryNumber)
                .orElseThrow(() -> new EntityNotFoundException(dormitoryNumber, Dormitory.class));

        dormitoryId = dormitory.getId();
        return this;
    }

    public UserRegistrationDto createDto() {
        return new UserRegistrationDto(login,
                password,
                firstName,
                lastName,
                fatherName,
                gender,
                universityId,
                dormitoryId);
    }
}
