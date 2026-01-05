package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserRegistrationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryUserRepository;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.LOGIN_EXIST_ERROR_MESSAGE;
@Component
public class UserValidationService {
    private final UserRepository userRepository;

    public UserValidationService(@Qualifier(InMemoryUserRepository.class) UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateLoginExistence(String login) {
        boolean isPresent = userRepository.findByLogin(login).isPresent();
        if (isPresent) {
            throw new UserRegistrationException(LOGIN_EXIST_ERROR_MESSAGE.formatted(login));
        }
    }
}
