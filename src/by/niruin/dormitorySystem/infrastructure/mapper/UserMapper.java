package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.*;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.NOT_VALID_FIELDS_QUANTITY_MESSAGE;

@Component
public class UserMapper extends AbstractEntityMapper<User> {
    @Override
    protected String entityFieldsToString(User user) {
        return String.join(
                FIELDS_DELIMITER,
                user.getId().toString(),
                user.getLogin(),
                user.getRole().toString(),
                user.getFullName().getFirstName(),
                user.getFullName().getLastName(),
                user.getFullName().getFatherName(),
                user.getGender().toString(),
                String.valueOf(user.getPasswordHash()),
                user.getUniversityId().toString(),
                user.getDormitoryId().toString());
    }

    @Override
    protected User mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);
        if (parts.length != User.class.getDeclaredFields().length + ADDITIONAL_FIELDS_FOR_FULLNAME) {
            throw new RuntimeException(NOT_VALID_FIELDS_QUANTITY_MESSAGE);
        }

        UUID id = UUID.fromString(parts[0]);
        String login = parts[1];
        Role role = Role.valueOf(parts[2].toUpperCase());
        String firstName = parts[3];
        String lastName = parts[4];
        String fatherName = parts[5];
        Gender gender = Gender.valueOf(parts[6].toUpperCase());
        int passwordHash = Integer.parseInt(parts[7]);
        UUID universityId = UUID.fromString(parts[8]);
        UUID dormitoryId = UUID.fromString(parts[9]);

        User user = new User(id, login, role, firstName, lastName, fatherName, gender, universityId, dormitoryId);
        user.setPasswordHash(passwordHash);

        return user;
    }
}
