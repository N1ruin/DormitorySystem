package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.ui.formHandler.user.*;

public interface UserFormHandlerFactory {
    AuthenticationFormHandler getAuthenticationFormHandler();
    DeleteUserFormHandler getDeleteUserFormHandler();
    RegistrationFormHandler getRegistrationFormHandler();
    UpdateUserFormHandler getUpdateUserFormHandler();
    UserInfoFormHandler getUserInfoFormHandler();
}
