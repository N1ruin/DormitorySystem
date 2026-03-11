package by.niruin.dormitorySystem.domain.model.dto.user;

import by.niruin.dormitorySystem.domain.model.Gender;

import java.util.UUID;

public record UserRegistrationDto(
        String login,
        String password,
        String firstName,
        String lastName,
        String fatherName,
        Gender gender,
        UUID universityId,
        UUID dormitoryId) {
}
