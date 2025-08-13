package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.exception.UserAuthorizationException;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

public class AuthorizationService {
    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;
    private final PrintService printService;

    public AuthorizationService(ApplicationContext applicationContext, UserRepository userRepository, PrintService printService) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
        this.printService = printService;
    }

    public void authorizeUser(String login, String password) {
        User user = userRepository.findUserByLogin(login);

        if (user == null || !checkPassword(user, password)) {
            throw new UserAuthorizationException();
        }

        printService.printWelcomeUserMessage(user);
        applicationContext.setActiveUser(user);
    }

    private boolean checkPassword(User user, String password) {
        return user.getPasswordHash() == password.hashCode();
    }

    public boolean checkAccess(User user, Role necessaryAccessRole) {
        return user.getRole() == necessaryAccessRole;
    }
}