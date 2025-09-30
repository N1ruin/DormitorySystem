package by.niruin.dormitorySystem.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(UUID uuid) {
  super("Entity with UUID: %s not exist!".formatted(uuid));
    }

    public EntityNotFoundException() {
        super("Entity not exist!");
    }
}
