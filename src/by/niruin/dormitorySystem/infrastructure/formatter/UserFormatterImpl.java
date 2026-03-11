package by.niruin.dormitorySystem.infrastructure.formatter;

import by.niruin.dormitorySystem.domain.model.dto.user.UserInfoDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class UserFormatterImpl implements UserFormatter {
    public static final String USER_INFORMATION = "User '%s' info:\n";
    public static final String USER_FULL_NAME = "Full name: %s\n";
    public static final String USER_GENDER = "Gender: %s\n";
    public static final String USER_UNIVERSITY_NAME = "University name: %s\n";
    public static final String USER_DORMITORY_NUMBER = "Dormitory number: %d\n";

    @Override
    public String formatStudentsToStudentsInfo(UserInfoDto... dtos) {
        return Arrays.stream(dtos)
                .map(dto -> String.join("",
                        USER_INFORMATION.formatted(dto.login()),
                        USER_FULL_NAME.formatted(dto.fullName()),
                        USER_GENDER.formatted(dto.gender()),
                        USER_UNIVERSITY_NAME.formatted(dto.universityName()),
                        USER_DORMITORY_NUMBER.formatted(dto.dormitoryNumber()
                        )))
                .collect(Collectors.joining());
    }
}
