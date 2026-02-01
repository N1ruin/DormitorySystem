package by.niruin.dormitorySystem.infrastructure.service;

import by.niruin.dormitorySystem.constant.ConsoleMessage;
import by.niruin.dormitorySystem.domain.model.Role;
import by.niruin.dormitorySystem.domain.model.dto.room.RoomNumbersDto;
import by.niruin.dormitorySystem.domain.model.dto.university.UniversityNamesDto;
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

    public void printSelectUniversityRequestMessage(UniversityNamesDto dto) {
        System.out.println(SELECT_UNIVERSITY_NUMBER_FROM_LIST_MESSAGE);
        System.out.println(dto.names());
    }

    public void printDormitoriesNumbersRequestMessage(String numbers) {
        System.out.println(SELECT_DORMITORY_NUMBER_FROM_LIST_MESSAGE);
        System.out.println(numbers);
    }

    public void printStudentsWithoutRoom(String studentNames) {
        System.out.println(studentNames);
    }

    public void printSortedDormitoriesInfo(String roomsInfo) {
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

    public void printInputDormitoryCapacityRequestMessage() {
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
            System.out.println(ROOMS_NOT_FOUND_IN_DORMITORY_MESSAGE);
        } else {
            System.out.println(dto.numbers());
        }
    }

    public void printInputRoomNumberRequestMessage() {
        System.out.println(INPUT_ROOM_NUMBER_REQUEST_MESSAGE);
    }

    public void printInputDormitoryNumberRequestMessage() {
        System.out.println(INPUT_DORMITORY_NUMBER_REQUEST_MESSAGE);
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

    public void printDormitoryCreatedSuccessfulMessage() {
        System.out.println(DORMITORY_CREATED_SUCCESSFUL_MESSAGE);
    }

    public void printDormitoryDeletedSuccessfulMessage() {
        System.out.println(DORMITORY_DELETED_SUCCESSFUL_MESSAGE);
    }

    public void printRoomUpdatedSuccessfulMessage() {
        System.out.println(ROOM_UPDATED_SUCCESSFUL_MESSAGE);
    }

    public void printDormitoryUpdatedSuccessfulMessage() {
        System.out.println(DORMITORY_UPDATED_SUCCESSFUL_MESSAGE);
    }

    public void printDormitoryInfo(String info) {
        System.out.println(info);
    }

    public void printDormitoryNumbers(String dormitoryNumbers) {
        System.out.println(SELECT_DORMITORY_NUMBER_FROM_LIST_MESSAGE);

        if (dormitoryNumbers.isEmpty()) {
            System.out.println(DORMITORY_NOT_FOUND_MESSAGE);
        } else {
            System.out.println(dormitoryNumbers);
        }
    }

    public void printInputUniversityNumberRequestMessage() {
        System.out.println(INPUT_UNIVERSITY_NAME_REQUEST_MESSAGE);
    }

    public void printInputStudyDurationRequestMessage() {
        System.out.println(INPUT_STUDY_DURATION_REQUEST_MESSAGE);
    }

    public void printUniversityNumbers(UniversityNamesDto dto) {
        System.out.println(SELECT_UNIVERSITY_NUMBER_FROM_LIST_MESSAGE);

        if (dto.names().isEmpty()) {
            System.out.println(UNIVERSITY_NOT_FOUND_MESSAGE);
        } else {
            System.out.println(dto.names());
        }
    }

    public void printUniversityCreatedSuccessfulMessage() {
        System.out.println(UNIVERSITY_CREATED_SUCCESSFUL_MESSAGE);
    }

    public void printUniversityDeletedSuccessfulMessage() {
        System.out.println(UNIVERSITY_DELETED_SUCCESSFUL_MESSAGE);
    }

    public void printUniversityUpdatedSuccessfulMessage() {
        System.out.println(UNIVERSITY_UPDATED_SUCCESSFUL_MESSAGE);
    }

    public void printUniversityInfo(String universityInfo) {
        System.out.println(universityInfo);
    }

    public void printSortedUniversitiesInfo(String sortedUniversitiesInfo) {
        System.out.println(sortedUniversitiesInfo);
    }

    public void printInputRoleRequestMessage() {
        System.out.println(SELECT_USER_ROLE_FROM_LIST_MESSAGE);
        for (int i = 1; i <= Role.values().length; i++) {
            System.out.println("%d. %s".formatted(i, Role.values()[i - 1]));
        }
    }

    public void printUserInfo(String userInfo) {
        System.out.println(userInfo);
    }

    public void printUserDeletedSuccessfulMessage() {
        System.out.println(USER_DELETED_SUCCESSFUL_MESSAGE);
    }

    public void printCurrentDormitorySelectedMessage() {
        System.out.println(CURRENT_DORMITORY_UPDATED_MESSAGE);
    }

    public void printCurrentUniversitySelectedMessage() {
        System.out.println(CURRENT_UNIVERSITY_UPDATED_MESSAGE);
    }

    public void printUniversityHasNoDormitoriesMessage() {
        System.out.println(UNIVERSITY_HAS_NO_DORMITORIES_MESSAGE);
    }

    public void printCreateDormitoryRequestMessage() {
        System.out.println(NEEDED_CREATE_DORMITORY_MESSAGE);
    }

    public void printStudentCreatedSuccessfulMessage() {
        System.out.println(STUDENT_CREATED_SUCCESSFUL_MESSAGE);
    }

    public void printStudentDeletedSuccessfulMessage() {
        System.out.println(STUDENT_DELETED_SUCCESSFUL_MESSAGE);
    }

    public void printInputDateOfEnteringMessage() {
        System.out.println(INPUT_DATE_OF_ENTERING_REQUEST_MESSAGE);
    }

    public void printStudentUpdatedSuccessfulMessage() {
        System.out.println(STUDENT_UPDATED_SUCCESSFUL_MESSAGE);
    }

    public void printStudentNames(String studentNames) {
        System.out.println(INPUT_STUDENT_NUMBER_FROM_LIST_MESSAGE);
        System.out.println(studentNames);
    }

    public void printStudentInfo(String info) {
        System.out.println(info);
    }

    public void printSortedStudentsInfo(String sortedStudentsInfo) {
        System.out.println(sortedStudentsInfo);
    }

    public void printStudentDistributedToDormitorySuccessfulMessage() {
        System.out.println(STUDENT_DISTRIBUTED_TO_DORMITORY_SUCCESS_MESSAGE);
    }

    public void printStudentDistributedToRoomSuccessfulMessage() {

    }

    public void printStatistics(String statistics) {
        System.out.println(statistics);
    }
}
