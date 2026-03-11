package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.domain.model.dto.UserRegistrationDto;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.domain.repository.UserRepository;
import by.niruin.dormitorySystem.domain.service.validation.UserValidationService;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.UUID;

@Component
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserValidationService validationService;

    public RegistrationService(UserRepository userRepository, UserValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public void signUp(UserRegistrationDto dto) {
        validationService.validateLoginExistence(dto.login());

        UUID id = UUID.randomUUID();
        var user = new User(id, dto.login(), dto.password(), Role.GUEST, dto.firstName(),
                dto.lastName(), dto.fatherName(), dto.gender(), dto.universityId(), dto.dormitoryId());

        userRepository.save(user);
    }
}
