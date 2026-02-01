package by.niruin.dormitorySystem.domain.model.dto.user;

import by.niruin.dormitorySystem.domain.model.Role;

public record UpdateUserDto(String login,
                            String password,
                            String lastName,
                            Role role) {
}
