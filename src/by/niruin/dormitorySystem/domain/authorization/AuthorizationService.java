package by.niruin.dormitorySystem.domain.authorization;

import by.niruin.dormitorySystem.domain.model.Role;
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


        if (user == null || !checkPassword(user.getPasswordHash(), password)) {
            throw new UserAuthorizationException();
        }

        printService.printWelcomeUserMessage(user);
        ApplicationContext.setActiveUser(user);
    }

    private boolean checkPassword(int passwordHash, String inputPassword) {
        return passwordHash == inputPassword.hashCode();
    }

    public boolean checkAccess(User user, Role necessaryAccessRole) {
        return user.getRole() == necessaryAccessRole;
    }
}
