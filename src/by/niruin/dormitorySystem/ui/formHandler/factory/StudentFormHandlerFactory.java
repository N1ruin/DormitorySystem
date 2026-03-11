package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.ui.formHandler.student.*;

public interface StudentFormHandlerFactory {
    CreateStudentFormHandler getCreateStudentFormHandler();
    DeleteStudentFormHandler getDeleteStudentFormHandler();
    DistributeStudentToDormitoryFormHandler getDistributeStudentToDormitoryFormHandler();
    DistributeStudentToRoomFormHandler getDistributeStudentToRoomFormHandler();
    StudentInfoFormHandler getStudentInfoFormHandler();
    UpdateStudentFormHandler getUpdateStudentFormHandler();
}
