package by.niruin.dormitorySystem.domain.service.validation;

import by.niruin.dormitorySystem.exception.InputValidationException;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.INVALID_INPUT_MESSAGE;

@Component
public class UserInputValidationService {

    public static final String LOGIN_LENGTH_PATTERN = ".{4,16}";
    public static final String LOGIN_SYMBOLS_PATTERN = "^[a-zA-Z0-9]+";
    public static final String LOGIN_MIN_ONE_LETTER_PATTERN = "^.*[a-zA-Z]+.*";

    public static final String PASSWORD_LENGTH_PATTERN = ".{8,16}";
    public static final String PASSWORD_LETTER_PATTERN = "^.*[a-zA-Z]+.*";
    public static final String PASSWORD_NUMBER_PATTERN = "^.*[0-9]*.*";
    public static final String PASSWORD_SPECIAL_SYMBOL_PATTERN = "^.*[!#%^]*.*";

    public static final String NAME_PATTERN = "^[a-zA-Z]+";
    public static final String GENDER_PATTERN = "(?i)^(male|female)$";

    public void validateLogin(String login) {
        if (!login.matches(LOGIN_LENGTH_PATTERN) || !login.matches(LOGIN_MIN_ONE_LETTER_PATTERN)
            || !login.matches(LOGIN_SYMBOLS_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateInputNotBlank(String Input) {
        if (Input.isBlank()) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validatePassword(String password) {
        if (!password.matches(PASSWORD_LENGTH_PATTERN) || !password.matches(PASSWORD_LETTER_PATTERN)
            || !password.matches(PASSWORD_NUMBER_PATTERN) || !password.matches(PASSWORD_SPECIAL_SYMBOL_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateName(String name) {
        if (!name.matches(NAME_PATTERN)) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }

    public void validateGender(String gender) {
        if (!gender.matches((GENDER_PATTERN))) {
            throw new InputValidationException(INVALID_INPUT_MESSAGE);
        }
    }
}
