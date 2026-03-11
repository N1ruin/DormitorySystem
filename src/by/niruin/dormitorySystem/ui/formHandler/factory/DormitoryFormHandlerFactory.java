package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.ui.formHandler.dormitory.*;

public interface DormitoryFormHandlerFactory {
    CreateDormitoryFormHandler getCreateDormitoryFormHandler();
    DeleteDormitoryFormHandler getDeleteDormitoryFormHandler();
    DormitoryInfoFormHandler getDormitoryInfoFormHandler();
    SelectCurrentDormitoryFormHandler getSelectCurrentDormitoryFormHandler();
    UpdateDormitoryFormHandler getUpdateDormitoryFormHandler();
}
