package by.niruin.dormitorySystem.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID userUUID) {
        super("User with UUID %s not found".formatted(userUUID.toString()));
    }
}
