package by.niruin.dormitorySystem.exception;


import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(UUID id, Class<?> clazz) {
        super("%s with UUID: %s not exist!".formatted(clazz.getSimpleName(), id));
    }

    public EntityNotFoundException(int number, Class<?> clazz) {
        super("Entity %s with number %d not exist!".formatted(clazz.getSimpleName(), number));
    }
}
