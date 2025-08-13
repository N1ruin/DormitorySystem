package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.idGenerator.IdGenerator;
import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

public class UserService {
    private final UserRepository userRepository;
    private final InputService inputService;
    private final PrintService printService;
    private final UserValidationService userValidationService;
    private final IdGenerator idGenerator;

    public UserService(UserRepository userRepository, InputService inputService,
                       PrintService printService, UserValidationService userValidationService, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.inputService = inputService;
        this.printService = printService;
        this.userValidationService = userValidationService;
        this.idGenerator = idGenerator;
    }

    public void registerUser() {
        String login = processLogin();
        String password = processPassword();
        String firstName = processFirstName();
        String lastName = processLastName();
        String fatherName = processFatherName();
        Gender gender = processGender();

        long id = idGenerator.getNextId();

        User user = new User(id, login, password, Role.DORMITORY_ADMIN, firstName, lastName, fatherName, gender);
        userRepository.save(user);
    }

    private String processLogin() {
        printService.printInputLoginRequestMessage();

        String login;
        boolean isLoginValid;
        do {
            login = inputService.inputLine();
            isLoginValid = userValidationService.validateLogin(login);

            if (!isLoginValid) {
                printService.printIncorrectLoginMessage();
            }

        } while (!isLoginValid);

        return login;
    }

    private String processPassword() {
        printService.printInputPasswordRequestMessage();

        String password;
        boolean isPasswordValid;
        do {
            password = inputService.inputLine();
            isPasswordValid = userValidationService.validatePassword(password);

            if (!isPasswordValid) {
                printService.printIncorrectPasswordMessage();
            }

        } while (!isPasswordValid);

        return password;
    }

    private String processFirstName() {
        printService.printInputFirstNameRequestMessage();

        String firstName;
        boolean isFirstNameValid;
        do {
            firstName = inputService.inputLine();
            isFirstNameValid = userValidationService.validateName(firstName);

            if (!isFirstNameValid) {
                printService.printIncorrectFirstNameMessage();
            }

        } while (!isFirstNameValid);

        return firstName;
    }

    private String processLastName() {
        printService.printInputLastNameRequestMessage();

        String lastName;
        boolean isLastNameValid;
        do {
            lastName = inputService.inputLine();
            isLastNameValid = userValidationService.validateName(lastName);

            if (!isLastNameValid) {
                printService.printIncorrectLastNameMessage();
            }

        } while (!isLastNameValid);

        return lastName;
    }


    private String processFatherName() {
        printService.printInputFatherNameRequestMessage();

        String fatherName;
        boolean isFatherNameValid;
        do {
            fatherName = inputService.inputLine();
            isFatherNameValid = userValidationService.validateName(fatherName);

            if (!isFatherNameValid) {
                printService.printIncorrectFatherNameMessage();
            }

        } while (!isFatherNameValid);

        return fatherName;
    }

    private Gender processGender() {
        printService.printInputGenderRequestMessage();

        Gender gender = null;
        boolean isGenderValid = false;
        do {
            String result = inputService.inputLine();

            if (result.equals("1")) {
                gender = Gender.MALE;
                isGenderValid = true;
            } else if (result.equals("2")) {
                gender = Gender.FEMALE;
                isGenderValid = true;
            }

            if (!isGenderValid) {
                printService.printIncorrectGenderMessage();
            }

        } while (!isGenderValid);

        return gender;
    }
}