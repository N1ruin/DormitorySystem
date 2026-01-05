package by.niruin.dormitorySystem.infrastructure.mapper;

import by.niruin.dormitorySystem.domain.model.User;

import java.util.Collection;

public interface UserMapper {
    String mapUsersToString(Collection<User> students);

    Collection<User> mapStringToUsers(String usersData);
}
