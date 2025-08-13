package by.niruin.dormitorySystem.constant;

public class RegexPattern {
    public static final String LOGIN_LENGTH_PATTERN = ".{4, 16}";
    public static final String LOGIN_SYMBOLS_PATTERN = "^[a-zA-Z0-9]+";
    public static final String LOGIN_MIN_ONE_LETTER_PATTERN = "^.*[a-zA-Z]+.*";

    public static final String PASSWORD_LENGTH_PATTERN = ".{8,16}";
    public static final String PASSWORD_LETTER_PATTERN = "^.*[a-zA-Z]+.*";
    public static final String PASSWORD_NUMBER_PATTERN = "^.*[0-9]*.*";
    public static final String PASSWORD_SPECIAL_SYMBOL_PATTERN = "^.*[!#%^]*.*";

    public static final String NAME_PATTERN = "^[а-яА-Я]+";
}
