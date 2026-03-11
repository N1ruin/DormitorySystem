package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.ui.formHandler.university.*;

public interface UniversityFormHandlerFactory {
    CreateUniversityFormHandler getCreateUniversityFormHandler();
    DeleteUniversityFormHandler getDeleteUniversityFormHandler();
    SelectCurrentUniversityFormHandler getSelectCurrentUniversityFormHandler();
    UniversityInfoFormHandler getUniversityInfoFormHandler();
    UpdateUniversityFormHandler getUpdateUniversityFormHandler();
}
