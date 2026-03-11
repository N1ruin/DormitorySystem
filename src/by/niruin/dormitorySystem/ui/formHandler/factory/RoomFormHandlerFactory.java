package by.niruin.dormitorySystem.ui.formHandler.factory;

import by.niruin.dormitorySystem.ui.formHandler.room.CreateRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.DeleteRoomFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.RoomInfoFormHandler;
import by.niruin.dormitorySystem.ui.formHandler.room.UpdateRoomFormHandler;

public interface RoomFormHandlerFactory {
    CreateRoomFormHandler getCreateRoomFormHandler();
    DeleteRoomFormHandler getDeleteRoomFormHandler();
    RoomInfoFormHandler getRoomInfoFormHandler();
    UpdateRoomFormHandler getUpdateRoomFormHandler();
}
