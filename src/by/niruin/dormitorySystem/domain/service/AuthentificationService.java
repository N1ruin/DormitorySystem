package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.context.ApplicationContextHolder;
import by.niruin.dormitorySystem.domain.model.dto.AuthentificationUserDto;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserAuthentificationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryUserRepository;

import java.util.Optional;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INCORRECT_LOGIN_AND_PASSWORD_EXCEPTION_MESSAGE;

@Component
public class AuthentificationService {
    private final UserRepository userRepository;

    public AuthentificationService(@Qualifier(InMemoryUserRepository.class) UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signIn(AuthentificationUserDto dto) {
        Optional<User> user = userRepository.findByLogin(dto.login());

        if (user.isEmpty() || !isPasswordTrue(user.get().getPasswordHash(), dto.password())) {
            throw new UserAuthentificationException(INCORRECT_LOGIN_AND_PASSWORD_EXCEPTION_MESSAGE);
        }

        ApplicationContextHolder.getContext().setActiveUser(user.get());
    }

    public void logOut() {
        ApplicationContextHolder.getContext().setActiveUser(null);
    }

    private boolean isPasswordTrue(int passwordHash, String inputPassword) {
        return passwordHash == inputPassword.hashCode();
    }
}
