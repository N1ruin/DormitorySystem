package by.niruin.dormitorySystem.infrastructure.service;

import by.niruin.dormitorySystem.constant.ConsoleMessage;
import by.niruin.dormitorySystem.domain.model.User;

public class PrintService {

    public void printWelcomeUserMessage(User user) {
        String userFullName = user.getFullName().toString();
        String message = ConsoleMessage.WELCOME_MESSAGE + ", " + userFullName + "!";

        System.out.println(message);
    }

    public void printInputLoginRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_LOGIN_REQUEST_MESSAGE);
    }

    public void printIncorrectLoginMessage() {
        System.out.println(ConsoleMessage.INCORRECT_LOGIN_MESSAGE);
    }

    public void printIncorrectPasswordMessage() {
        System.out.println(ConsoleMessage.INCORRECT_PASSWORD_MESSAGE);
    }

    public void printInputPasswordRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_PASSWORD_REQUEST_MESSAGE);
    }

    public void printInputFirstNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_FIRST_NAME_REQUEST_MESSAGE);
    }

    public void printIncorrectFirstNameMessage() {
        System.out.println(ConsoleMessage.INCORRECT_FIRST_NAME_MESSAGE);
    }

    public void printInputLastNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_LAST_NAME_REQUEST_MESSAGE);
    }

    public void printIncorrectLastNameMessage() {
        System.out.println(ConsoleMessage.INCORRECT_LAST_NAME_MESSAGE);
    }

    public void printInputFatherNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_FATHER_NAME_REQUEST_MESSAGE);
    }

    public void printIncorrectFatherNameMessage() {
        System.out.println(ConsoleMessage.INCORRECT_FATHER_NAME_MESSAGE);
    }

    public void printInputGenderRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_GENDER_REQUEST_MESSAGE);
    }

    public void printIncorrectGenderMessage(){
        System.out.println(ConsoleMessage.INCORRECT_GENDER_MESSAGE);
    }
}