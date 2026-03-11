package by.niruin.dormitorySystem.constant;

public class ConsoleMessage {
    public static final String CIRCULAR_DEPENDENCY_IS_NOT_ALLOWED_ERROR_MESSAGE = "Circular dependency is not allowed";
    public static final String CLASS_HAS_NO_CONSTRUCTORS_ERROR_MESSAGE = "Class '%s' has no constructors";
    public static final String UNABLE_TO_CREATE_INSTANCE_ERROR_MESSAGE = "Unable to create instance of %s";
    public static final String IMPL_NOT_FOUND_ERROR_MESSAGE = "Implementation for class '%s' not found";
    public static final String FOUND_MANY_IMPL_ERROR_MESSAGE = "Find many implementations for class '%s'.";
    public static final String NOT_VALID_FIELDS_QUANTITY_MESSAGE = "Fields quantity in object not equals quantity parsed fields";
    public static final String CREATING_DIRECTORY_ERROR_MESSAGE = "Error creating directory with path %s";

    public static final String WELCOME_APP_MESSAGE = "Welcome dormitory application";
    public static final String SELECT_ACTION_MESSAGE = "Select action:";
    public static final String SELECT_SORTING_ORDER_MESSAGE = "Select sorting order:";
    public static final String INVALID_INPUT_MESSAGE = "Incorrect input, please try again";
    public static final String OPERATION_FAILED_MESSAGE = "Operation failed:\n";

    public static final String INPUT_LOGIN_AND_PASSWORD_REQUEST_MESSAGE = "Please, enter your login and password to log in to your account!";
    public static final String INPUT_LOGIN_REQUEST_MESSAGE = "Input login:";
    public static final String INPUT_PASSWORD_REQUEST_MESSAGE = "Input password:";
    public static final String WELCOME_USER_MESSAGE = "Welcome, %s";
    public static final String FILL_REGISTRATION_FORM_REQUEST_MESSAGE = "Please, fill out the registration form";
    public static final String REGISTRATION_SUCCESS_MESSAGE = "Registration success!";
    public static final String USER_DELETED_SUCCESSFUL_MESSAGE = "User deleted successful";
    public static final String SELECT_USER_ROLE_FROM_LIST_MESSAGE = "Select user role from list:\n";
    public static final String INCORRECT_LOGIN_AND_PASSWORD_EXCEPTION_MESSAGE = "Incorrect login or password!";
    public static final String LOGIN_EXIST_ERROR_MESSAGE = "Login %s exist";

    public static final String INPUT_FIRST_NAME_REQUEST_MESSAGE = "Input your first name:";
    public static final String INPUT_LAST_NAME_REQUEST_MESSAGE = "Input your last name:";
    public static final String INPUT_FATHER_NAME_REQUEST_MESSAGE = "Input your father name:";
    public static final String INPUT_GENDER_REQUEST_MESSAGE = "Input gender:";
    public static final String INPUT_DATE_OF_ENTERING_REQUEST_MESSAGE = "Input date of entering in the format 'dd.mm.yyyy'";
    public static final String STUDENT_CREATED_SUCCESSFUL_MESSAGE = "Student created successful";
    public static final String STUDENT_DELETED_SUCCESSFUL_MESSAGE = "Student deleted successful";
    public static final String STUDENT_UPDATED_SUCCESSFUL_MESSAGE = "Student updated successful";
    public static final String STUDENT_DISTRIBUTED_TO_DORMITORY_SUCCESS_MESSAGE = "Student distributed to dormitory success";
    public static final String STUDENT_DISTRIBUTED_TO_ROOM_SUCCESS_MESSAGE = "Student distributed to room success";
    public static final String INPUT_STUDENT_NUMBER_FROM_LIST_MESSAGE = "Input student number from list:";
    public static final String STUDENTS_NOT_FOUND_MESSAGE = "Students at the current university not found";
    public static final String CREATING_STUDENT_FAIL_MESSAGE = "Unable to create student:\n";
    public static final String GET_STUDENT_FROM_LIST_FAIL_MESSAGE = "Input out of list items";

    public static final String INPUT_UNIVERSITY_NAME_REQUEST_MESSAGE = "Input university name";
    public static final String INPUT_STUDY_DURATION_REQUEST_MESSAGE = "Input study duration";
    public static final String UNIVERSITY_CREATED_SUCCESSFUL_MESSAGE = "University created successful";
    public static final String UNIVERSITY_DELETED_SUCCESSFUL_MESSAGE = "University deleted successful";
    public static final String UNIVERSITY_UPDATED_SUCCESSFUL_MESSAGE = "University updated successful";
    public static final String CURRENT_UNIVERSITY_UPDATED_MESSAGE = "Current university updated";
    public static final String SELECT_UNIVERSITY_NUMBER_FROM_LIST_MESSAGE = "Select university number from list:";
    public static final String UNIVERSITIES_NOT_FOUND_MESSAGE = "Universities at the system not found";
    public static final String UNIVERSITY_NOT_FOUND_MESSAGE = "Universities not found in system";
    public static final String UNIVERSITY_NAME_EXIST_MESSAGE = "University with name '%s' exist";
    public static final String UNIVERSITY_WITH_NUMBER_NOT_FOUND_MESSAGE = "University with number %d not found. Available numbers: 1-%d";
    public static final String CREATING_UNIVERSITY_FAIL_MESSAGE = "Unable to create university:\n";

    public static final String INPUT_DORMITORY_NUMBER_REQUEST_MESSAGE = "Input dormitory number";
    public static final String DORMITORY_CREATED_SUCCESSFUL_MESSAGE = "Dormitory created successful";
    public static final String DORMITORY_DELETED_SUCCESSFUL_MESSAGE = "Dormitory deleted successful";
    public static final String DORMITORY_UPDATED_SUCCESSFUL_MESSAGE = "Dormitory updated successful";
    public static final String CURRENT_DORMITORY_UPDATED_MESSAGE = "Current dormitory updated";
    public static final String SELECT_DORMITORY_NUMBER_FROM_LIST_MESSAGE = "Select dormitory number from list:";
    public static final String DORMITORY_NOT_FOUND_MESSAGE = "Dormitories not found in university";
    public static final String UNIVERSITY_HAS_NO_DORMITORIES_MESSAGE = "selected university has no dormitories available";
    public static final String NEEDED_CREATE_DORMITORY_MESSAGE = "You can add dormitories in Dormitories menu";
    public static final String DORMITORY_NUMBER_EXIST_MESSAGE = "Dormitory with number %d exist";
    public static final String DORMITORY_WITH_NUMBER_NOT_EXIST_MESSAGE = "Dormitory with number %d not exist";
    public static final String DORMITORY_NUMBER_CANNOT_BE_LESS_THAN_ZERO_MESSAGE = "Dormitory number cannot be less than zero";

    public static final String INPUT_ROOM_NUMBER_REQUEST_MESSAGE = "Input room number";
    public static final String INPUT_ROOM_CAPACITY_REQUEST_MESSAGE = "Input room capacity";
    public static final String INPUT_ROOM_AVAILABLE_REQUEST_MESSAGE = "Room available for living? (yes/no)";
    public static final String INPUT_ROOM_GENDER_REQUEST_MESSAGE = "Input room gender(male/female):";
    public static final String ROOM_CREATED_SUCCESSFUL_MESSAGE = "Room created successful";
    public static final String ROOM_DELETED_SUCCESSFUL_MESSAGE = "Room deleted successful";
    public static final String ROOM_UPDATED_SUCCESSFUL_MESSAGE = "Room updated successful";
    public static final String SELECT_ROOM_NUMBER_FROM_LIST_MESSAGE = "Input room number from list:";
    public static final String ROOMS_NOT_FOUND_IN_DORMITORY_MESSAGE = "Rooms not found in dormitory";
    public static final String ROOM_NUMBER_EXIST_MESSAGE = "Room with number %d exist";
    public static final String ROOM_WITH_NUMBER_NOT_EXIST_MESSAGE = "Room with number %d not exist";
    public static final String ROOM_NUMBER_OUT_OF_BOUNDS_MESSAGE = "Number %d out of acceptable bounds";
    public static final String CREATING_ROOM_FAIL_MESSAGE = "Unable to create room:\n";
    public static final String INVALID_STUDENT_GENDER_FROM_ROOM_MESSAGE = "Room is for %s students only";
    public static final String ROOM_IS_NOT_AVAILABLE_FOR_LIVING_MESSAGE = "Room is not available for living";
    public static final String ROOM_IS_FULL_MESSAGE = "Room is full";
    public static final String ROOM_IS_IN_DIFFERENT_DORMITORY_MESSAGE = "Room is in different dormitory";

    private ConsoleMessage() {
    }
}
