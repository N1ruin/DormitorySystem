package by.niruin.dormitorySystem.exception;

import by.niruin.dormitorySystem.domain.model.User;

public class UserAccessException extends RuntimeException {
    public UserAccessException(User user) {
        super(message);
    }
}
