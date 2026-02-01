package by.niruin.dormitorySystem.infrastructure.service;

import by.niruin.dormitorySystem.constant.ConsoleMessage;
import by.niruin.dormitorySystem.domain.model.dto.DormitoryNumbersDto;
import by.niruin.dormitorySystem.domain.model.dto.RoomNumbersDto;
import by.niruin.dormitorySystem.domain.model.dto.StudentsWithoutRoomDto;
import by.niruin.dormitorySystem.domain.model.dto.UniversityNumbersDto;
import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import static by.niruin.dormitorySystem.constant.ConsoleMessage.*;

@Component
public class PrintService {
    public void printInputLoginRegistrationRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_LOGIN_REQUEST_MESSAGE);
    }

    public void printInputPasswordRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_PASSWORD_REQUEST_MESSAGE);
    }

    public void printInputFirstNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_FIRST_NAME_REQUEST_MESSAGE);
    }

    public void printInputLastNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_LAST_NAME_REQUEST_MESSAGE);
    }

    public void printInputFatherNameRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_FATHER_NAME_REQUEST_MESSAGE);
    }

    public void printInputGenderRequestMessage() {
        System.out.println(ConsoleMessage.INPUT_GENDER_REQUEST_MESSAGE);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printSelectUniversityRequestMessage(UniversityNumbersDto dto) {
        System.out.println(SELECT_UNIVERSITY_NUMBER_FROM_LIST_MESSAGE);
        System.out.println(dto.numbers());
    }

    public void printDormitoriesNameRequestMessage(DormitoryNumbersDto dto) {
        System.out.println(SELECT_DORMITORY_NUMBER_FROM_LIST_MESSAGE);
        System.out.println(dto.numbers());
    }

    public void printStudentsWithoutRoom(StudentsWithoutRoomDto dto) {
        if (dto.studentNames().isEmpty()) {
            System.out.println(ALL_STUDENTS_HAVING_ROOMS_MESSAGE);
        } else {
            System.out.println(dto.studentNames());

        }
    }

    public void printSortedRoomsInfo(String roomsInfo) {
        System.out.println(roomsInfo);
    }

    public void printFillRegistrationFormRequest() {
        System.out.println(FILL_REGISTRATION_FORM_REQUEST_MESSAGE);
    }

    public void printRegistrationSuccessMessage() {
        System.out.println(REGISTRATION_SUCCESS_MESSAGE);
    }

    public void printWelcomeUserMessage(String login) {
        System.out.println(WELCOME_USER_MESSAGE.formatted(login));
    }

    public void printSelectActionMessage() {
        System.out.println(SELECT_ACTION_MESSAGE);
    }

    public void printSelectSortingOrder() {
        System.out.println(SELECT_SORTING_ORDER_MESSAGE);
    }

    public void printMenu(String menu) {
        System.out.println(menu);
    }

    public void printAuthentificationRequest() {
        System.out.println(INPUT_LOGIN_AND_PASSWORD_REQUEST_MESSAGE);
    }

    public void printInputRoomCapacityRequestMessage() {
        System.out.println(INPUT_ROOM_CAPACITY_REQUEST_MESSAGE);
    }

    public void printInputAvailableForLivingRequestMessage() {
        System.out.println(INPUT_ROOM_AVAILABLE_REQUEST_MESSAGE);
    }

    public void printInputGenderRoomRequestMessage() {
        System.out.println(INPUT_ROOM_GENDER_REQUEST_MESSAGE);
    }

    public void printRoomNumbers(RoomNumbersDto dto) {
        System.out.println(SELECT_ROOM_NUMBER_FROM_LIST_MESSAGE);

        if (dto.numbers().isEmpty()) {
            System.out.println(ROOMS_NOT_FOUND_STRING);
        } else {
            System.out.println(dto.numbers());
        }
    }

    public void printInputRoomNumberRequestMessage() {
        System.out.println(INPUT_ROOM_NUMBER_REQUEST_MESSAGE);
    }

    public void printWelcomeApplicationMessage() {
        System.out.println(WELCOME_APP_MESSAGE);
    }

    public void printRoomInfo(String roomInfo) {
        System.out.println(roomInfo);
    }

    public void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    public void printRoomCreatedSuccessfulMessage() {
        System.out.println(ROOM_CREATED_SUCCESSFUL_MESSAGE);
    }

    public void printRoomDeletedSuccessfulMessage() {
        System.out.println(ROOM_DELETED_SUCCESSFUL_MESSAGE);
    }
}
