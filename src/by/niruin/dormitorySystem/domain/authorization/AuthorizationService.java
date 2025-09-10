package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserAuthorizationException;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

public class AuthorizationService {
    private final UserRepository userRepository;
    private final PrintService printService;

    public AuthorizationService(UserRepository userRepository, PrintService printService) {
        this.userRepository = userRepository;
        this.printService = printService;
    }

    public void signIn(String login, String password) {
        User user = userRepository.findByLogin(login);


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
