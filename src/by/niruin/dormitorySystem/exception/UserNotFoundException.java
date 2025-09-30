package by.niruin.dormitorySystem.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID userUUID) {
        super("User with UUID: %s not exist!".formatted(userUUID));
    }

    public UserNotFoundException(String login) {
        super("User with login: %s not exist".formatted(login));
    }

    public UserNotFoundException() {
        super("User not exist");
    }
}
