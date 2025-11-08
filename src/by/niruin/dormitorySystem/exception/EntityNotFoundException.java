package by.niruin.dormitorySystem.exception;


public class EntityNotFoundException extends RuntimeException {
    public <ID> EntityNotFoundException(ID id) {
        super("Entity with UUID: %s not exist!".formatted(id));
    }

    public EntityNotFoundException() {
        super("Entity not exist!");
    }
}
