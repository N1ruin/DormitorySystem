package by.niruin.dormitorySystem.domain.service;

import by.niruin.dormitorySystem.constant.RegexPattern;

public class UserValidationService {

    public boolean validateLogin(String login) {
        return login.matches(RegexPattern.LOGIN_LENGTH_PATTERN)
                && login.matches(RegexPattern.LOGIN_MIN_ONE_LETTER_PATTERN)
                && login.matches(RegexPattern.LOGIN_SYMBOLS_PATTERN);
    }

    public boolean validatePassword(String password) {
        return password.matches(RegexPattern.PASSWORD_LENGTH_PATTERN)
                && password.matches(RegexPattern.PASSWORD_LETTER_PATTERN)
                && password.matches(RegexPattern.PASSWORD_NUMBER_PATTERN)
                && password.matches(RegexPattern.PASSWORD_SPECIAL_SYMBOL_PATTERN);
    }

    public boolean validateName(String name) {
        return name.matches(RegexPattern.NAME_PATTERN);
    }
}