package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final PrintService printService;

    public UserService(UserRepository userRepository,
                       PrintService printService) {
        this.userRepository = userRepository;
        this.printService = printService;
    }

    public boolean setRole(UUID id, Role newRole) {
        try {
            Optional<User> userOptional = userRepository.findById(id);

            userOptional.ifPresent(user -> {
                user.setRole(newRole);
                userRepository.update(user);
            });
            return true;
        } catch (Exception e) {
            printService.printExceptionMessage(e);
        }
        return false;
    }
}
