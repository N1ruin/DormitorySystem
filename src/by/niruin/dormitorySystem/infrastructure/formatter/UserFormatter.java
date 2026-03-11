package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.user.UserInfoDto;

public interface UserFormatter {
    String formatStudentsToStudentsInfo(UserInfoDto... dtos);
}
