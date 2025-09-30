package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.Gender;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.User;

import java.util.*;

public class UserMapper extends AbstractEntityMapper<User> {
    @Override
    protected int getFieldsCount() {
        return 6;
    }

    @Override
    protected String entityFieldsToString(User user) {
        return String.join(
                FIELDS_DELIMITER,
                user.getUuid().toString(),
                user.getFullName().getFirstName(),
                user.getFullName().getLastName(),
                user.getFullName().getFatherName(),
                user.getGender().toString(),
                user.getLogin(),
                user.getRole().toString(),
                String.valueOf(user.getPasswordHash()));
    }

    @Override
    protected User mapStringToEntity(String fields) {
        String[] parts = fields.split(FIELDS_DELIMITER);

        if (parts.length != getFieldsCount()) {
            throw new RuntimeException("");
        }

        UUID uuid = UUID.fromString(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String fatherName = parts[3];
        Gender gender = Gender.valueOf(parts[4]);
        String login = parts[5];
        Role role = Role.valueOf(parts[6]);
        int passwordHash = Integer.parseInt(parts[7]);

        User user = new User(uuid, login, null, role, firstName, lastName, fatherName, gender);
        user.setPasswordHash(passwordHash);

        return user;
    }
}
