package by.niruin.dormitorySystem.domain.model.dto.user;

import by.niruin.dormitorySystem.domain.model.Gender;

public record UserInfoDto(String login,
                          String fullName,
                          Gender gender,
                          String universityName,
                          Integer dormitoryNumber) {
}
