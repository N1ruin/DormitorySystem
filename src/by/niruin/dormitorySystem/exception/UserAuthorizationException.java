package by.niruin.dormitorySystem.exception;

public class UserAuthorizationException extends RuntimeException {
    public UserAuthorizationException() {
        super("Invalid login or password");
    }
}
