package by.niruin.dormitorySystem.domain.service;

public class UserValidationService {
    public static final String LOGIN_LENGTH_PATTERN = ".{4,16}";
    public static final String LOGIN_SYMBOLS_PATTERN = "^[a-zA-Z0-9]+";
    public static final String LOGIN_MIN_ONE_LETTER_PATTERN = "^.*[a-zA-Z]+.*";

    public static final String PASSWORD_LENGTH_PATTERN = ".{8,16}";
    public static final String PASSWORD_LETTER_PATTERN = "^.*[a-zA-Z]+.*";
    public static final String PASSWORD_NUMBER_PATTERN = "^.*[0-9]*.*";
    public static final String PASSWORD_SPECIAL_SYMBOL_PATTERN = "^.*[!#%^]*.*";

    public static final String NAME_PATTERN = "^[а-яА-Я]+";

    public boolean isLoginValid(String login) {
        return login.matches(LOGIN_LENGTH_PATTERN)
                && login.matches(LOGIN_MIN_ONE_LETTER_PATTERN)
                && login.matches(LOGIN_SYMBOLS_PATTERN);
    }

    public boolean isPasswordValid(String password) {
        return password.matches(PASSWORD_LENGTH_PATTERN)
                && password.matches(PASSWORD_LETTER_PATTERN)
                && password.matches(PASSWORD_NUMBER_PATTERN)
                && password.matches(PASSWORD_SPECIAL_SYMBOL_PATTERN);
    }

    public boolean isNameValid(String name) {
        return name.matches(NAME_PATTERN);
    }
}
