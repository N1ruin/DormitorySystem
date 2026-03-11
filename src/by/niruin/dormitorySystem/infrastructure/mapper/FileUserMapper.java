package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.*;
import java.util.stream.Collectors;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.FIELDS_DELIMITER;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileDormitoryMapper.NEW_LINE_SYMBOL;
import static by.niruin.dormitorySystem.infrastructure.mapper.FileStudentMapper.ADDITIONAL_FIELDS_FOR_FULLNAME;

@Component
public class FileUserMapper implements UserMapper {

    @Override
    public String mapUsersToString(Collection<User> users) {
        return users.stream()
                .map(this::userFieldsToString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    @Override
    public Collection<User> mapStringToUsers(String usersData) {
        if (usersData == null || usersData.trim().isEmpty()) {
            return List.of();
        }

        String[] usersFields = usersData.split(NEW_LINE_SYMBOL);

        return Arrays.stream(usersFields)
                .map(this::mapStringToUser)
                .toList();
    }


    private String userFieldsToString(User user) {
        return String.join(
                FIELDS_DELIMITER,
                user.getId().toString(),
                user.getLogin(),
                user.getRole().toString(),
                user.getFullName().getFirstName(),
                user.getFullName().getFatherName(),
                user.getFullName().getLastName(),
                user.getGender().toString(),
                String.valueOf(user.getPasswordHash()),
                user.getUniversityId().toString(),
                user.getDormitoryId().toString());
    }

    private User mapStringToUser(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);
        if (parts.length != User.class.getDeclaredFields().length + ADDITIONAL_FIELDS_FOR_FULLNAME) {
            throw new RuntimeException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        String login = parts[1];
        Role role = Role.valueOf(parts[2].toUpperCase());
        String firstName = parts[3];
        String fatherName = parts[4];
        String lastName = parts[5];
        Gender gender = Gender.valueOf(parts[6].toUpperCase());
        int passwordHash = Integer.parseInt(parts[7]);
        UUID universityId = UUID.fromString(parts[8]);
        UUID dormitoryId = UUID.fromString(parts[9]);

        User user = new User(id, login, role, firstName, lastName, fatherName, gender, universityId, dormitoryId);
        user.setPasswordHash(passwordHash);

        return user;
    }
}
