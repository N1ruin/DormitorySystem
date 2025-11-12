package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserAuthorizationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.annotation.Qualifier;
import by.niruin.dormitorySystem.infrastructure.repository.InMemoryUserRepository;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

import java.util.UUID;
@Component
public class AuthorizationService {
    private final UserRepository userRepository;
    private final PrintService printService;

    public AuthorizationService(@Qualifier(value = InMemoryUserRepository.class) UserRepository userRepository,
                                PrintService printService) {
        this.userRepository = userRepository;
        this.printService = printService;
    }

    public void signIn(String login, String password) {
        User<UUID> user = userRepository.findByLogin(login);

        if (user == null || !isPasswordTrue(user.getPasswordHash(), password)) {
            throw new UserAuthorizationException();
        }

        printService.printWelcomeUserMessage(user);
        ApplicationContextHolder.getContext().setActiveUser(user);
    }

    private boolean isPasswordTrue(int passwordHash, String inputPassword) {
        return passwordHash == inputPassword.hashCode();
    }
}
