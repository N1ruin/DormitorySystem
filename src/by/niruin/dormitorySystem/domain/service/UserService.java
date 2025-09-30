package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserNotFoundException;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

import java.util.UUID;
import java.util.function.Predicate;

public class UserService {
    private final UserRepository userRepository;
    private final InputService inputService;
    private final PrintService printService;
    private final UserValidationService userValidationService;

    public UserService(UserRepository userRepository, InputService inputService,
                       PrintService printService, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.inputService = inputService;
        this.printService = printService;
        this.userValidationService = userValidationService;
    }

    public void signUp() {
        UUID id = UUID.randomUUID();

        String login = processUserField(
                printService::printInputLoginRequestMessage,
                printService::printIncorrectLoginMessage,
                userValidationService::isLoginValid);

        String password = processUserField(
                printService::printInputPasswordRequestMessage,
                printService::printIncorrectPasswordMessage,
                userValidationService::isPasswordValid);

        String firstName = processUserField(
                printService::printInputFirstNameRequestMessage,
                printService::printIncorrectFirstNameMessage,
                userValidationService::isNameValid
        );

        String lastName = processUserField(
                printService::printInputLastNameRequestMessage,
                printService::printIncorrectLastNameMessage,
                userValidationService::isNameValid
        );

        String fatherName = processUserField(
                printService::printInputFatherNameRequestMessage,
                printService::printIncorrectFatherNameMessage,
                userValidationService::isNameValid
        );

        String stringGender = processUserField(
                printService::printInputGenderRequestMessage,
                printService::printIncorrectGenderMessage,
                gender -> gender.equalsIgnoreCase("male")
                        || gender.equalsIgnoreCase("female")
        );

        Gender gender = Gender.valueOf(stringGender);

        User user = new User(id, login, password, Role.GUEST, firstName, lastName, fatherName, gender);
        userRepository.save(user);
    }

    private String processUserField(Runnable inputRequestMessagePrinter, Runnable errorMessagePrinter,
                                    Predicate<String> fieldValidator) {

        inputRequestMessagePrinter.run();
        String field;

        do {
            field = inputService.inputLine();

            if (Predicate.not(fieldValidator).test(field)) {
                errorMessagePrinter.run();
            }

        } while (Predicate.not(fieldValidator).test(field));

        return field;
    }

    public boolean setRole(UUID userUUID, Role newRole) {
        boolean result = false;

        try {
            User user = userRepository.findById(userUUID);
            user.setRole(newRole);
            userRepository.update(user);
            result = true;
        } catch (UserNotFoundException e) {
            printService.printExceptionMessage(e);
        }

        return result;
    }
}
