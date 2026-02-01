package by.niruin.dormitorySystem.ui.menu;

import by.niruin.dormitorySystem.domain.service.RegistrationService;
import by.niruin.dormitorySystem.domain.service.UserService;
import by.niruin.dormitorySystem.infrastructure.service.InputService;
import by.niruin.dormitorySystem.infrastructure.service.PrintService;
import by.niruin.dormitorySystem.logger.Logger;
import by.niruin.dormitorySystem.logger.LoggerFactory;
import by.niruin.dormitorySystem.ui.formHandler.user.DeleteUserFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.user.GetUserInfoFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.user.RegistrationFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.user.UpdateUserFormHandler;
import by.niruin.dormitorySystem.util.MenuItemUtil;

import static by.niruin.dormitorySystem.constant.LoggerMessage.*;

public class UserMenu implements Menu {
    private final PrintService printService;
    private final InputService inputService;
    private final MenuFactory menuFactory;
    private final UserService userService;
    private final RegistrationService registrationService;
    private final RegistrationFormHandler registrationFormHandler;
    private final DeleteUserFormHandler deleteUserFormHandler;
    private final UpdateUserFormHandler updateUserFormHandler;
    private final GetUserInfoFormHandler getUserInfoFormHandler;
    private final Logger logger = LoggerFactory.getLogger(UserMenu.class);

    public UserMenu(PrintService printService, InputService inputService, MenuFactory menuFactory, UserService userService,
                    RegistrationService registrationService, RegistrationFormHandler registrationFormHandler,
                    DeleteUserFormHandler deleteUserFormHandler, UpdateUserFormHandler updateUserFormHandler,
                    GetUserInfoFormHandler getUserInfoFormHandler) {
        this.printService = printService;
        this.inputService = inputService;
        this.menuFactory = menuFactory;
        this.userService = userService;
        this.registrationService = registrationService;
        this.registrationFormHandler = registrationFormHandler;
        this.deleteUserFormHandler = deleteUserFormHandler;
        this.updateUserFormHandler = updateUserFormHandler;
        this.getUserInfoFormHandler = getUserInfoFormHandler;
    }

    @Override
    public void display() {
        printService.printSelectActionMessage();
        printService.printMenu(MenuItemUtil.buildMenu(UserMenuItem.class));
    }

    @Override
    public Menu handleInput() {
        String userInput = inputService.inputLine();
        try {
            var item = MenuItemUtil.getItem(UserMenuItem.class, Integer.parseInt(userInput));
            logger.info(SELECTED_ITEM_LOG.formatted(item.name()));
            return executeMenuItem(item);
        } catch (Exception e) {
            printService.printInvalidInputMessage();
            logger.info(ENTERED_INVALID_VALUE_LOG.formatted(userInput));
            return this;
        }
    }

    private Menu executeMenuItem(UserMenuItem item) {
        Menu nextMenu = this;
        switch (item) {
            case CREATE_USER -> createUser();
            case DELETE_USER -> deleteUser();
            case UPDATE_USER -> updateUser();
            case GET_SORTED_USERS -> nextMenu = menuFactory.createSelectSortUsersOrderMenu();
            case GET_USER_INFO -> getUserInfo();
            case GO_BACK -> nextMenu = menuFactory.createMainMenu();
        }
        return nextMenu;
    }

    private void createUser() {
        var dto = registrationFormHandler
                .handleLogin()
                .handlePassword()
                .handleFirstName()
                .handleLastName()
                .handleFatherName()
                .handleGender()
                .handleUniversityNumber()
                .handleDormitoryNumber()
                .createDto();
        try {
            registrationService.signUp(dto);
            printService.printRegistrationSuccessMessage();
            logger.info(USER_CREATED_SUCCESSFUL_LOG.formatted(dto.login()));
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_CREATED_FAIL_LOG.formatted(dto.login()));
            logger.info(e.getMessage());
        }
    }

    private void deleteUser() {
        var dto = deleteUserFormHandler
                .handleLogin()
                .createDto();

        try {
            userService.deleteUser(dto);
            printService.printUserDeletedSuccessfulMessage();
            logger.info(USER_DELETED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_DELETED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void updateUser() {
        var dto = updateUserFormHandler
                .handleLogin()
                .handlePassword()
                .handleLastName()
                .handleRole()
                .createDto();
        try {
            userService.updateUser(dto);
            printService.printUniversityUpdatedSuccessfulMessage();
            logger.info(USER_UPDATED_SUCCESSFUL_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_UPDATED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

    private void getUserInfo() {
        var dto = getUserInfoFormHandler
                .handleLogin()
                .createDto();

        try {
            String userInfo = userService.getUserInfo(dto);
            printService.printUserInfo(userInfo);
            logger.info(USER_INFO_RECEIVED_SUCCESS_LOG);
        } catch (Exception e) {
            printService.printExceptionMessage(e);
            logger.info(USER_INFO_RECEIVED_FAIL_LOG);
            logger.info(e.getMessage());
        }
    }

}
